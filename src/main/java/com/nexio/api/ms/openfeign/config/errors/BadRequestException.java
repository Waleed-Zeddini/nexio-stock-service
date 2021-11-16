package com.nexio.api.ms.openfeign.config.errors;


public class BadRequestException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4001630320754635598L;

	public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "BadRequestException: "+getMessage();
    }

}