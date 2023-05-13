package com.breaktime.gp_core.core.graphics.control.print;

import com.breaktime.gp_core.core.ui.resource.UIInset;
import com.breaktime.gp_core.core.ui.resource.UIPainter;
import com.breaktime.gp_core.core.ui.resource.UISize;

public interface TGPrintDocument{
	
	void start();
	
	void finish();
	
	void pageStart();
	
	void pageFinish();
	
	boolean isPaintable(int page);
	
	boolean isTransparentBackground();
	
	UIPainter getPainter();
	
	UISize getSize();
	
	UIInset getMargins();
}
