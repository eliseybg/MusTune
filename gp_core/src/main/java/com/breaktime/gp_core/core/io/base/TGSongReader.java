package com.breaktime.gp_core.core.io.base;

public interface TGSongReader extends TGSongPersistenceHandler {
	
	void read(TGSongReaderHandle handle) throws TGFileFormatException;
}
