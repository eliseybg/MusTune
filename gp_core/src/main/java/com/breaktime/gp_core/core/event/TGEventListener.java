package com.breaktime.gp_core.core.event;

public interface TGEventListener {
	
	public void processEvent(TGEvent event) throws TGEventException;
}
