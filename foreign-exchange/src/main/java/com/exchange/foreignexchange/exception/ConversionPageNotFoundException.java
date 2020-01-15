package com.exchange.foreignexchange.exception;

public class ConversionPageNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ConversionPageNotFoundException(int pageNumber) {
        super("There is no conversion page number : " + pageNumber);
    }
}
