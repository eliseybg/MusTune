package com.breaktime.gp_core.gtp;

import com.breaktime.gp_core.core.io.base.TGFileFormatDetector;
import com.breaktime.gp_core.core.io.base.TGSongReader;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public class GP3InputStreamPlugin extends GTPInputStreamPlugin{

	public GP3InputStreamPlugin() {
		super();
	}
	
	protected TGSongReader createInputStream(TGContext context) throws TGPluginException {
		return new GP3InputStream(GTPSettingsManager.getInstance(context).getSettings());
	}
	
	public TGFileFormatDetector createFileFormatDetector(TGContext context) throws TGPluginException {
		return new GTPFileFormatDetector(GP3InputStream.SUPPORTED_VERSIONS);
	}
}
