package com.breaktime.gp_core.core.util.plugin;

import com.breaktime.gp_core.core.util.TGContext;

public interface TGPlugin {
	
	String getModuleId();
	
	void connect(TGContext context) throws TGPluginException;
	
	void disconnect(TGContext context) throws TGPluginException;
}