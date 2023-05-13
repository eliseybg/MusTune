package com.breaktime.gp_core.gtp;

import com.breaktime.gp_core.core.io.base.TGSongWriter;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public class GP3OutputStreamPlugin extends GTPOutputStreamPlugin{

	public GP3OutputStreamPlugin() {
		super();
	}
	
	protected TGSongWriter createOutputStream(TGContext context) throws TGPluginException {
		return new GP3OutputStream(GTPSettingsManager.getInstance(context).getSettings());
	}
}
