package com.cg.book.app.exception;

public class CategoryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;       
    public  CategoryNotFoundException()
    {
     super();
    }
    public CategoryNotFoundException(String message) {
        super(message);

    }
}
