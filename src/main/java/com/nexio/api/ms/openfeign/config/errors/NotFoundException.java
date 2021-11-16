package com.nexio.api.ms.openfeign.config.errors;

public class NotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3092795663376641908L;

	public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "NotFoundException: "+getMessage();
    }

}
