package com.breaktime.gp_core.core.io.plugin;

import com.breaktime.gp_core.core.io.base.TGFileFormatManager;
import com.breaktime.gp_core.core.io.base.TGSongExporter;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPlugin;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public abstract class TGSongExporterPlugin implements TGPlugin {
	
	private TGSongExporter exporter;
	
	protected abstract TGSongExporter createExporter(TGContext context) throws TGPluginException;
	
	public void connect(TGContext context) throws TGPluginException {
		try {
			if( this.exporter == null ) {
				this.exporter = createExporter(context);
				
				TGFileFormatManager.getInstance(context).addExporter(this.exporter);
			}
		} catch (Throwable throwable) {
			throw new TGPluginException(throwable.getMessage(),throwable);
		}
	}
	
	public void disconnect(TGContext context) throws TGPluginException {
		try {
			if( this.exporter != null ) {
				TGFileFormatManager.getInstance(context).removeExporter(this.exporter);
				
				this.exporter = null;
			}
		} catch (Throwable throwable) {
			throw new TGPluginException(throwable.getMessage(),throwable);
		}
	}
}
