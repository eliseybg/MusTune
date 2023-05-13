package com.breaktime.gp_core.gpx;

import com.breaktime.gp_core.core.io.base.TGFileFormatException;

public class GPXFormatException extends TGFileFormatException {
	
	private static final long serialVersionUID = 1L;
	
	public GPXFormatException(String message) {
		super(message);
	}
	
	public GPXFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}
