package com.breaktime.gp_core.gpx.v7;

import com.breaktime.gp_core.gpx.GPXPlugin;
import com.breaktime.gp_core.core.io.base.TGFileFormatDetector;
import com.breaktime.gp_core.core.io.base.TGSongReader;
import com.breaktime.gp_core.core.io.plugin.TGSongReaderPlugin;
import com.breaktime.gp_core.core.util.TGContext;

public class GPXInputStreamPlugin extends TGSongReaderPlugin {
	
	public GPXInputStreamPlugin() {
		super(true);
	}
	
	protected TGSongReader createInputStream(TGContext context) {
		return new GPXInputStream();
	}
	
	protected TGFileFormatDetector createFileFormatDetector(TGContext context) {
		return new GPXFileFormatDetector();
	}
	
	public String getModuleId() {
		return GPXPlugin.MODULE_ID;
	}
}
