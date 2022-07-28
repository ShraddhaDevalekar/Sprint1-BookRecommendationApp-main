package com.cg.book.app.service;

import java.util.List;

import com.cg.book.app.model.Book;

public interface BookService {
	
	public List<Book> getAllBooks();
	
	public Book addBook(Book book);
	
	public Book getBookById(int id);
	
	public Book getBookByName(String name);
	
	public void deleteBook(int id);
	
	public Book UpdateBookById(int id, Book book);

}
