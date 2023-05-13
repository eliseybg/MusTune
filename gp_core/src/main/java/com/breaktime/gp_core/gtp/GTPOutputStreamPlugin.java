package com.breaktime.gp_core.gtp;

import com.breaktime.gp_core.core.io.plugin.TGSongWriterPlugin;

public abstract class GTPOutputStreamPlugin extends TGSongWriterPlugin{
	
	public GTPOutputStreamPlugin(){
		super(true);
	}
	
	public String getModuleId(){
		return GTPPlugin.MODULE_ID;
	}
}
