package com.exchange.foreignexchange.exception;

public class ConversionNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ConversionNotFoundException(Long transactionId) {
        super("There is no conversion with this id : " + transactionId);
    }
}
