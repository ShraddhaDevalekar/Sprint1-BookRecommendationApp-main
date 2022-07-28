package com.cg.book.app.exception;

public class BookAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException() {
		super();
	}

	public BookAlreadyExistsException(String exceptionMessage) {
		super(exceptionMessage);
	}
}