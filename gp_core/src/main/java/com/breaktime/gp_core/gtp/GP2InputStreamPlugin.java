package com.breaktime.gp_core.gtp;

import com.breaktime.gp_core.core.io.base.TGFileFormatDetector;
import com.breaktime.gp_core.core.io.base.TGSongReader;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public class GP2InputStreamPlugin extends GTPInputStreamPlugin {

	public GP2InputStreamPlugin() {
		super();
	}
	
	protected TGSongReader createInputStream(TGContext context) throws TGPluginException {
		return new GP2InputStream(GTPSettingsManager.getInstance(context).getSettings());
	}
	
	public TGFileFormatDetector createFileFormatDetector(TGContext context) throws TGPluginException {
		return new GTPFileFormatDetector(GP2InputStream.SUPPORTED_VERSIONS);
	}
}
