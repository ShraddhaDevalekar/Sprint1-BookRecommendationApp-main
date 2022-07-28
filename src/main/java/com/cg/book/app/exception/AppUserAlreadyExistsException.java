package com.cg.book.app.exception;

public class AppUserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppUserAlreadyExistsException() {
		super();
	}

	public AppUserAlreadyExistsException(String exceptionMessage) {
		super(exceptionMessage);
	}

}