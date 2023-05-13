package com.breaktime.gp_core.core.graphics.command;

import com.breaktime.gp_core.core.ui.resource.UIPainter;

public interface TGPaintCommand {
	
	void paint(UIPainter painter, float x, float y, float scale);
	
	float getMaximumX();
	
	float getMaximumY();
	
	float getMinimumX();
	
	float getMinimumY();
}
