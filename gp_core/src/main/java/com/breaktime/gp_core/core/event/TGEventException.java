package com.breaktime.gp_core.core.event;

import com.breaktime.gp_core.core.util.TGException;

public class TGEventException extends TGException {
	
	private static final long serialVersionUID = 658339308041498112L;
	
	public TGEventException(String message){
		super(message);
	}
	
	public TGEventException(Throwable cause){
		super(cause);
	}
	
	public TGEventException(String message, Throwable cause){
		super(message, cause);
	}
}
