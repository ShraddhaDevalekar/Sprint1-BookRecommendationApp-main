package com.cg.book.app.service;

import java.util.List;

import com.cg.book.app.model.Author;

public interface AuthorService {

	Author getAuthorById(int id);

	List<Author> getAllAuthors();

	Author addAuthor(Author author);

	Author getAuthorByName(String name);

	void deleteAuthor(int id);

}