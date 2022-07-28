package com.cg.book.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.book.app.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

	public Author findAuthorByAuthorId(int id);
	
	public Author findAuthorByAuthorName(String name);

}