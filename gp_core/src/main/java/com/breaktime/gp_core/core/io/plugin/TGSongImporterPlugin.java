package com.breaktime.gp_core.core.io.plugin;

import com.breaktime.gp_core.core.io.base.TGFileFormatManager;
import com.breaktime.gp_core.core.io.base.TGSongImporter;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPlugin;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public abstract class TGSongImporterPlugin implements TGPlugin {
	
	private TGSongImporter importer;
	
	protected abstract TGSongImporter createImporter(TGContext context) throws TGPluginException;
	
	public void connect(TGContext context) throws TGPluginException {
		try {
			if( this.importer == null ) {
				this.importer = createImporter(context);
				
				TGFileFormatManager.getInstance(context).addImporter(this.importer);
			}
		} catch (Throwable throwable) {
			throw new TGPluginException(throwable.getMessage(),throwable);
		}
	}
	
	public void disconnect(TGContext context) throws TGPluginException {
		try {
			if( this.importer != null ) {
				TGFileFormatManager.getInstance(context).removeImporter(this.importer);
				
				this.importer = null;
			}
		} catch (Throwable throwable) {
			throw new TGPluginException(throwable.getMessage(),throwable);
		}
	}
}
