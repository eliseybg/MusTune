package com.breaktime.gp_core.core.io.tg;

import com.breaktime.gp_core.core.io.base.TGFileFormat;
import com.breaktime.gp_core.core.io.base.TGFileFormatDetector;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TGFileFormatDetectorImpl implements TGFileFormatDetector {
	
	private TGFileFormatVersion[] supportedVersions;
	
	public TGFileFormatDetectorImpl(TGFileFormatVersion... supportedVersions) {
		this.supportedVersions = supportedVersions;
	}
	
	public TGFileFormat getFileFormat(InputStream is) {
		try {
			String version = this.readVersion(new DataInputStream(is));
			if( version != null ) {
				for(TGFileFormatVersion supportedVersion : this.supportedVersions) {
					if( version.equals(supportedVersion.getVersion())) {
						return supportedVersion.getFileFormat();
					}
				}
			}
			return null;
		} catch (Throwable throwable) {
			return null;
		}
	}
	
	private String readVersion(DataInputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		int length = (is.read() & 0xFF);
		for(int i = 0; i < length; i ++){
			sb.append(is.readChar());
		}
		return sb.toString();
	}
}
