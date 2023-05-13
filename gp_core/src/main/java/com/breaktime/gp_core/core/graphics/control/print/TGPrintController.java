package com.breaktime.gp_core.core.graphics.control.print;

import com.breaktime.gp_core.core.graphics.control.TGController;
import com.breaktime.gp_core.core.graphics.control.TGLayoutStyles;
import com.breaktime.gp_core.core.graphics.control.TGResourceBuffer;
import com.breaktime.gp_core.core.managers.TGSongManager;
import com.breaktime.gp_core.core.models.TGBeat;
import com.breaktime.gp_core.core.models.TGMeasure;
import com.breaktime.gp_core.core.models.TGMeasureHeader;
import com.breaktime.gp_core.core.models.TGSong;
import com.breaktime.gp_core.core.ui.resource.UIResourceFactory;

public class TGPrintController implements TGController {
	
	private TGSong song;
	private TGSongManager songManager;
	private UIResourceFactory resourceFactory;
	private TGResourceBuffer resourceBuffer;
	private TGLayoutStyles styles;
	
	public TGPrintController(TGSong song, TGSongManager songManager, UIResourceFactory resourceFactory, TGLayoutStyles styles){
		this.song = song;
		this.songManager = songManager;
		this.styles = styles;
		this.resourceFactory = resourceFactory;
		this.resourceBuffer = new TGResourceBuffer();
	}
	
	public TGSong getSong() {
		return song;
	}

	public TGSongManager getSongManager() {
		return this.songManager;
	}
	
	public UIResourceFactory getResourceFactory(){
		return this.resourceFactory;
	}
	
	public TGResourceBuffer getResourceBuffer() {
		return this.resourceBuffer;
	}
	
	public TGLayoutStyles getStyles(){
		return this.styles;
	}
	
	public int getTrackSelection() {
		return -1;
	}
	
	public boolean isRunning(TGBeat beat) {
		return false;
	}
	
	public boolean isRunning(TGMeasure measure) {
		return false;
	}
	
	public boolean isLoopSHeader(TGMeasureHeader measureHeader) {
		return false;
	}
	
	public boolean isLoopEHeader(TGMeasureHeader measureHeader) {
		return false;
	}
}
