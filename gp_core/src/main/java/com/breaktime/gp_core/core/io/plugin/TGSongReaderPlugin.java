package com.breaktime.gp_core.core.io.plugin;

import com.breaktime.gp_core.core.io.base.TGFileFormatDetector;
import com.breaktime.gp_core.core.io.base.TGFileFormatManager;
import com.breaktime.gp_core.core.io.base.TGSongReader;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPlugin;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public abstract class TGSongReaderPlugin implements TGPlugin {
	
	private boolean commonFileFormat;
	private TGSongReader stream;
	private TGFileFormatDetector detector;
	
	public TGSongReaderPlugin(boolean commonFileFormat) {
		this.commonFileFormat = commonFileFormat;
	}
	
	protected abstract TGSongReader createInputStream(TGContext context) throws TGPluginException;
	
	protected abstract TGFileFormatDetector createFileFormatDetector(TGContext context) throws TGPluginException;
	
	public void connect(TGContext context) throws TGPluginException {
		try {
			TGFileFormatManager fileFormatManager = TGFileFormatManager.getInstance(context);
			
			if( this.stream == null ) {
				this.stream = createInputStream(context);
				
				fileFormatManager.addReader(this.stream);
				
				if( this.commonFileFormat ) {
					fileFormatManager.addCommonReadFileFormat(this.stream.getFileFormat());
				}
			}
			if( this.detector == null ) {
				this.detector = createFileFormatDetector(context);
				
				if( this.detector != null ) {
					fileFormatManager.addFileFormatDetector(this.detector);
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
					fileFormatManager.removeCommonReadFileFormat(this.stream.getFileFormat());
				}
				
				fileFormatManager.removeReader(this.stream);
				
				this.stream = null;
			}
			if( this.detector != null ) {
				fileFormatManager.removeFileFormatDetector(this.detector);
				
				this.detector = null;
			}
		} catch (Throwable throwable) {
			throw new TGPluginException(throwable.getMessage(),throwable);
		}
	}
}
