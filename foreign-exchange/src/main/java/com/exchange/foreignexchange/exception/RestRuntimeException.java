package com.exchange.foreignexchange.exception;

public class RestRuntimeException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public RestRuntimeException(String message) {
        super(message);
    }

}
