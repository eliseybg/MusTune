package com.breaktime.gp_core.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.breaktime.gp_core.core.ui.resource.UIFont;
import com.breaktime.gp_core.core.ui.resource.UIFontModel;

import java.io.IOException;

public class PDFFont extends UIFontModel implements UIFont {

	private boolean disposed;
	
	public PDFFont(String name, float height, boolean bold, boolean italic) {
		super(name, height, bold, italic);
	}
	
	public PDFFont(UIFontModel model) {
		this(model.getName(), model.getHeight(), model.isBold(), model.isItalic());
	}

	public PDFFont(UIFont font) {
		this(font.getName(), font.getHeight(), font.isBold(), font.isItalic());
	}
	
	public void dispose() {
		this.disposed = true;
	}

	public boolean isDisposed() {
		return this.disposed;
	}
	
	public BaseFont createHandle() {
		try {
			return BaseFont.createFont(this.getName(), BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			throw new PDFRuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			throw new PDFRuntimeException(e.getMessage(), e);
		}
	}
}
