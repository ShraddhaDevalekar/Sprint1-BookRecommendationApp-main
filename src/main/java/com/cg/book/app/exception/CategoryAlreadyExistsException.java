package com.cg.book.app.exception;

public class CategoryAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoryAlreadyExistsException() {
		super();
	}

	public CategoryAlreadyExistsException(String exceptionMessage) {
		super(exceptionMessage);
	}
}