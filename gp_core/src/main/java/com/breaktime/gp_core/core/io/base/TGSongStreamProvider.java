package com.breaktime.gp_core.core.io.base;

public interface TGSongStreamProvider {
	
	String getProviderId();
	
	TGSongStream openStream(TGSongStreamContext context);
}
