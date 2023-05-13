package com.breaktime.gp_core.core.io.base;

public interface TGSongWriter extends TGSongPersistenceHandler {
	
	void write(TGSongWriterHandle handle) throws TGFileFormatException;
}
