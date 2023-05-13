package com.breaktime.gp_core.core.util.properties;

import com.breaktime.gp_core.core.util.TGException;

public class TGPropertiesException extends TGException {
	
	private static final long serialVersionUID = 8298443126251976034L;

	public TGPropertiesException(String message){
		super(message);
	}
	
	public TGPropertiesException(Throwable cause){
		super(cause);
	}
	
	public TGPropertiesException(String message, Throwable cause){
		super(message, cause);
	}
}
