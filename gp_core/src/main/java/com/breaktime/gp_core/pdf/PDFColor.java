package com.breaktime.gp_core.pdf;

import com.breaktime.gp_core.core.ui.resource.UIColor;
import com.breaktime.gp_core.core.ui.resource.UIColorModel;

import com.itextpdf.text.BaseColor;

public class PDFColor extends UIColorModel implements UIColor {
	
	private boolean disposed;
	
	public PDFColor(int red, int green, int blue){
		super(red, green, blue);
	}
	
	public PDFColor(UIColorModel model) {
		this(model.getRed(), model.getGreen(), model.getBlue());
	}

	public PDFColor(UIColor color) {
		this(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public void dispose() {
		this.disposed = true;
	}

	public boolean isDisposed() {
		return this.disposed;
	}
	
	public BaseColor createHandle() {
		return new BaseColor(Math.round(getRed()), Math.round(getGreen()), Math.round(getBlue()));
	}
}
