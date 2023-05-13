package com.breaktime.gp_core.core.graphics.control;

import com.breaktime.gp_core.core.ui.resource.UIColor;
import com.breaktime.gp_core.core.ui.resource.UIImage;
import com.breaktime.gp_core.core.ui.resource.UIPainter;
import com.breaktime.gp_core.core.ui.resource.UIResourceFactory;

public class TGMeasureBuffer {
	
	private float width;
	
	private float height;
	
	public TGMeasureBuffer(){
		super();
	}
	
	public Object getRegistryKey() {
		return this;
	}
	
	public void register(TGResourceBuffer resourceBuffer) {
		resourceBuffer.register(this.getRegistryKey());
	}
	
	public UIPainter createBuffer(TGResourceBuffer resourceBuffer, UIResourceFactory resourceFactory, float width, float height, UIColor background){
		UIImage buffer = resourceFactory.createImage(width, height);
		this.width = buffer.getWidth();
		this.height = buffer.getHeight();
		
		UIPainter bufferedPainter = buffer.createPainter();
		bufferedPainter.setBackground(background);
		bufferedPainter.initPath(UIPainter.PATH_FILL);
		bufferedPainter.addRectangle(0, 0, this.width, this.height);
		bufferedPainter.closePath();
		
		resourceBuffer.setResource(this.getRegistryKey(), buffer);
		
		return bufferedPainter;
	}
	
	public void paintBuffer(TGResourceBuffer resourceBuffer, UIPainter painter,float x,float y){
		UIImage buffer = resourceBuffer.getResource(this.getRegistryKey());
		painter.drawImage(buffer, x, y);
	}
	
	public boolean isDisposed(TGResourceBuffer resourceBuffer){
		return resourceBuffer.isResourceDisposed(this.getRegistryKey());
	}
}
