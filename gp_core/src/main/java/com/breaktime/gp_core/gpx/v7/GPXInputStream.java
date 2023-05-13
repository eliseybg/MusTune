package com.breaktime.gp_core.gpx.v7;

import com.breaktime.gp_core.gpx.GPXDocumentParser;
import com.breaktime.gp_core.gpx.GPXDocumentReader;
import com.breaktime.gp_core.core.io.base.TGFileFormat;
import com.breaktime.gp_core.core.io.base.TGFileFormatException;
import com.breaktime.gp_core.core.io.base.TGSongReader;
import com.breaktime.gp_core.core.io.base.TGSongReaderHandle;

public class GPXInputStream implements TGSongReader {
	
	public static final TGFileFormat FILE_FORMAT = new TGFileFormat("Guitar Pro 7", "audio/x-gtp", new String[]{"gp"});
	
	public TGFileFormat getFileFormat() {
		return FILE_FORMAT;
	}
	
	public void read(TGSongReaderHandle handle) throws TGFileFormatException {
		try {
			GPXFileSystem gpxFileSystem = new GPXFileSystem();
			gpxFileSystem.load(handle.getInputStream());
			
			GPXDocumentReader gpxReader = new GPXDocumentReader(gpxFileSystem.getFileContentsAsStream(GPXFileSystem.RESOURCE_SCORE), GPXDocumentReader.GP7);
			GPXDocumentParser gpxParser = new GPXDocumentParser(handle.getFactory(), gpxReader.read());
			
			handle.setSong(gpxParser.parse());
		} catch (Throwable throwable) {
			throw new TGFileFormatException( throwable );
		}
	}
}
