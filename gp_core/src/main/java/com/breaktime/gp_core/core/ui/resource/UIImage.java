package com.breaktime.gp_core.core.ui.resource;

public interface UIImage extends UIResource {
	
	float getWidth();
	
	float getHeight();
	
	UIPainter createPainter();
}
