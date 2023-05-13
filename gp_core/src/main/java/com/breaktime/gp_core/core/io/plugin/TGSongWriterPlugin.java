package com.breaktime.gp_core.core.io.plugin;

import com.breaktime.gp_core.core.io.base.TGFileFormatManager;
import com.breaktime.gp_core.core.io.base.TGSongWriter;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPlugin;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public abstract class TGSongWriterPlugin implements TGPlugin {
	
	private boolean commonFileFormat;
	private TGSongWriter stream;
	
	public TGSongWriterPlugin(boolean commonFileFormat) {
		this.commonFileFormat = commonFileFormat;
	}
	
	protected abstract TGSongWriter createOutputStream(TGContext context) throws TGPluginException;
	
	public void connect(TGContext context) throws TGPluginException {
		try {
			TGFileFormatManager fileFormatManager = TGFileFormatManager.getInstance(context);
			
			if( this.stream == null ) {
				this.stream = createOutputStream(context);
				
				fileFormatManager.addWriter(this.stream);
				
				if( this.commonFileFormat ) {
					fileFormatManager.addCommonWriteFileFormat(this.stream.getFileFormat());
				}
			}
		} catch (Throwable throwable) {
			throw new TGPluginException(throwable.getMessage(),throwable);
		}
	}
	
	public void disconnect(TGContext context) throws TGPluginException {
		try {
			TGFileFormatManager fileFormatManager = TGFileFormatManager.getInstance(context);
			
			if( this.stream != null ) {
				if( this.commonFileFormat ) {
					fileFormatManager.removeCommonWriteFileFormat(this.stream.getFileFormat());
				}
				
				fileFormatManager.removeWriter(this.stream);
				
				this.stream = null;
			}
		} catch (Throwable throwable) {
			throw new TGPluginException(throwable.getMessage(),throwable);
		}
	}
}
