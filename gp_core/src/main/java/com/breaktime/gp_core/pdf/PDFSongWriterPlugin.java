package com.breaktime.gp_core.pdf;

import com.breaktime.gp_core.core.io.base.TGSongWriter;
import com.breaktime.gp_core.core.io.plugin.TGSongWriterPlugin;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public class PDFSongWriterPlugin extends TGSongWriterPlugin {
	
	public static final String MODULE_ID = "tuxguitar-pdf";
	
	public PDFSongWriterPlugin() {
		super(false);
	}
	
	public String getModuleId(){
		return MODULE_ID;
	}
	
	protected TGSongWriter createOutputStream(TGContext context) throws TGPluginException {
		return new PDFSongWriter(context);
	}
}
