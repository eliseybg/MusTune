package com.breaktime.gp_core.gtp;

import com.breaktime.gp_core.core.io.base.TGSongWriter;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public class GP4OutputStreamPlugin extends GTPOutputStreamPlugin{

	public GP4OutputStreamPlugin() {
		super();
	}
	
	protected TGSongWriter createOutputStream(TGContext context) throws TGPluginException {
		return new GP4OutputStream(GTPSettingsManager.getInstance(context).getSettings());
	}
}
