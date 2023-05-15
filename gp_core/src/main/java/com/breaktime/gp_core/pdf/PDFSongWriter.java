package com.breaktime.gp_core.pdf;

import com.breaktime.gp_core.core.graphics.control.TGController;
import com.breaktime.gp_core.core.graphics.control.TGFactoryImpl;
import com.breaktime.gp_core.core.graphics.control.TGLayout;
import com.breaktime.gp_core.core.graphics.control.TGLayoutStyles;
import com.breaktime.gp_core.core.graphics.control.print.TGPrintController;
import com.breaktime.gp_core.core.graphics.control.print.TGPrintLayout;
import com.breaktime.gp_core.core.graphics.control.print.TGPrintSettings;
import com.breaktime.gp_core.core.io.base.TGFileFormat;
import com.breaktime.gp_core.core.io.base.TGFileFormatException;
import com.breaktime.gp_core.core.io.base.TGSongWriter;
import com.breaktime.gp_core.core.io.base.TGSongWriterHandle;
import com.breaktime.gp_core.core.managers.TGSongManager;
import com.breaktime.gp_core.core.models.TGSong;
import com.breaktime.gp_core.core.ui.resource.UIInset;
import com.breaktime.gp_core.core.ui.resource.UIResourceFactory;
import com.breaktime.gp_core.core.ui.resource.UISize;
import com.breaktime.gp_core.core.util.TGContext;

public class PDFSongWriter implements TGSongWriter {
	
	public static final TGFileFormat FILE_FORMAT = new TGFileFormat("PDF", "application/pdf", new String[]{"pdf"});
	
	private static final int PAGE_WIDTH = 550;
	private static final int PAGE_HEIGHT = 800;
	private static final int MARGIN_TOP = 20;
	private static final int MARGIN_BOTTOM = 20;
	private static final int MARGIN_LEFT = 20;
	private static final int MARGIN_RIGHT = 20;
	
	private TGContext context;
	
	public PDFSongWriter(TGContext context) {
		this.context = context;
	}
	
	public TGFileFormat getFileFormat() {
		return FILE_FORMAT;
	}
	
	public TGPrintSettings getDefaultStyles(TGSong song){
		TGPrintSettings styles = new TGPrintSettings();
		styles.setStyle(TGLayout.DISPLAY_TABLATURE | TGLayout.DISPLAY_SCORE | TGLayout.DISPLAY_CHORD_DIAGRAM | TGLayout.DISPLAY_CHORD_NAME | TGLayout.DISPLAY_COMPACT | TGLayout.DISPLAY_MODE_BLACK_WHITE);
		styles.setFromMeasure(1);
		styles.setToMeasure(song.countMeasureHeaders());
		styles.setTrackNumber(TGPrintSettings.ALL_TRACKS);
		return styles;
	}
	
	public void write(TGSongWriterHandle handle) throws TGFileFormatException {
		try{
			TGPrintSettings settings = handle.getContext().getAttribute(TGPrintSettings.class.getName());
			if( settings == null ) {
				settings = getDefaultStyles(handle.getSong());
			}
			
			TGLayoutStyles styles = handle.getContext().getAttribute(TGLayoutStyles.class.getName());
			if( styles == null ) {
				styles = new PDFLayoutStyles();
			}
			
			TGSongManager manager = new TGSongManager(new TGFactoryImpl());
			TGSong clonedSong = handle.getSong().clone(manager.getFactory());
			
			UIResourceFactory factory = new PDFResourceFactory();
			TGController controller = new TGPrintController(clonedSong, manager, factory, styles);
			
			UISize pageSize = new UISize(PAGE_WIDTH, PAGE_HEIGHT);
			UIInset pageMargins = new UIInset(MARGIN_TOP, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_BOTTOM);
			
			TGPrintLayout layout = new TGPrintLayout(controller, settings);
			
			layout.loadStyles(1f);
			layout.updateSong();
			layout.makeDocument(new PDFDocument(this.context, pageSize, pageMargins, handle.getOutputStream()));
			
			controller.getResourceBuffer().disposeAllResources();
		}catch(Throwable throwable){
			throw new TGFileFormatException(throwable);
		}
	}
}