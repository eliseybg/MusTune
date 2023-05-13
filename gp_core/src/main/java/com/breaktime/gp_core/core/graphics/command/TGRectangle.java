package com.breaktime.gp_core.core.graphics.command;

import com.breaktime.gp_core.core.ui.resource.UIPainter;

public class TGRectangle implements TGPaintCommand {
	
	private float x;
	private float y;
	private float width;
	private float height;
	
	public TGRectangle(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void paint(UIPainter painter, float x, float y, float scale) {
		painter.addRectangle((x + (this.x * scale)), (y + (this.y * scale)), (this.width * scale), (this.height * scale));
	}
	
	public float getMaximumX() {
		return (this.x + this.width);
	}
	
	public float getMaximumY() {
		return (this.y + this.height);
	}
	
	public float getMinimumX() {
		return this.x;
	}
	
	public float getMinimumY() {
		return this.y;
	}
}
