package com.breaktime.gp_core.core.io.base;

import com.breaktime.gp_core.core.event.TGEvent;

public class TGFileFormatAvailabilityEvent extends TGEvent {
	
	public static final String EVENT_TYPE = "file-format-availability";
	
	public TGFileFormatAvailabilityEvent() {
		super(EVENT_TYPE);
	}
}
