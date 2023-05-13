package com.breaktime.gp_core.core.io.base;

import java.io.InputStream;

public interface TGFileFormatDetector {
	
	TGFileFormat getFileFormat(InputStream inputStream);
}
