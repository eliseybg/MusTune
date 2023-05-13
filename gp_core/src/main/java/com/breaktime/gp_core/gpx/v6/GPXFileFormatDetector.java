package com.breaktime.gp_core.gpx.v6;

import com.breaktime.gp_core.core.io.base.TGFileFormat;
import com.breaktime.gp_core.core.io.base.TGFileFormatDetector;

import java.io.InputStream;

public class GPXFileFormatDetector implements TGFileFormatDetector {
	
	public GPXFileFormatDetector() {
		super();
	}
	
	public TGFileFormat getFileFormat(InputStream is) {
		try {
			GPXFileSystem gpxFileSystem = new GPXFileSystem();
			if( gpxFileSystem.isSupportedHeader(gpxFileSystem.getHeader(is))) {
				return GPXInputStream.FILE_FORMAT;
			}
			return null;
		} catch (Throwable throwable) {
			return null;
		}
	}
}
