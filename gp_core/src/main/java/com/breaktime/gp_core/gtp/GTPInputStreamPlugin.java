package com.breaktime.gp_core.gtp;

import com.breaktime.gp_core.core.io.plugin.TGSongReaderPlugin;

public abstract class GTPInputStreamPlugin extends TGSongReaderPlugin {
	
	public GTPInputStreamPlugin(){
		super(true);
	}
	
	public String getModuleId(){
		return GTPPlugin.MODULE_ID;
	}
}
