package com.cg.book.app.exception;

public class AuthorAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorAlreadyExistsException() {
		super();
	}

	public AuthorAlreadyExistsException(String exceptionMessage) {
		super(exceptionMessage);
	}
}