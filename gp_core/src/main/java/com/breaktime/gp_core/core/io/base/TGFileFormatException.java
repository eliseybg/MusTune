package com.breaktime.gp_core.core.io.base;

import com.breaktime.gp_core.core.util.TGException;

public class TGFileFormatException extends TGException {
	
	private static final long serialVersionUID = 1L;
	
	public TGFileFormatException() {
		super();
	}
	
	public TGFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TGFileFormatException(String message) {
		super(message);
	}
	
	public TGFileFormatException(Throwable cause) {
		super(cause);
	}
}
