package com.breaktime.gp_core.core.resource;

import com.breaktime.gp_core.core.util.TGException;

public class TGResourceException extends TGException {
	
	private static final long serialVersionUID = -7010825375252091761L;

	public TGResourceException(){
		super();
	}
	
	public TGResourceException(String message){
		super(message);
	}
	
	public TGResourceException(Throwable cause){
		super(cause);
	}
	
	public TGResourceException(String message, Throwable cause){
		super(message, cause);
	}
}
