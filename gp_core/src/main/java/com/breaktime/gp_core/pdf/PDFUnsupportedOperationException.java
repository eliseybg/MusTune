package com.breaktime.gp_core.pdf;

public class PDFUnsupportedOperationException extends PDFRuntimeException {
	
	private static final long serialVersionUID = 6288731180146551079L;
	
	public PDFUnsupportedOperationException(){
		super();
	}
	
	public PDFUnsupportedOperationException(String message){
		super(message);
	}
	
	public PDFUnsupportedOperationException(Throwable cause){
		super(cause.getMessage(), cause);
	}
	
	public PDFUnsupportedOperationException(String message, Throwable cause){
		super(message, cause);
	}
}
