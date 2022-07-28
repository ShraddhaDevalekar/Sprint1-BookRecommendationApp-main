package com.cg.book.app.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.book.app.model.Author;
import com.cg.book.app.service.AuthorService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {

	@Autowired
	AuthorService authorService;

/************************************************************************************
	 * Method: getAuthorById 
	 * Description: It is used to view Authors by Id from author table /
	 * http://localhost:9999/user/get-author-by-id
*************************************************************************************/
	@GetMapping("/get-author-by-id/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable(name = "id") int id) {

		Author author = authorService.getAuthorById(id);
		if (author == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(author));
	}

/***************************************************************************************
	 * Method: getAuthorByName 
	 * Description: It is used to view Authors by name from author table 
	 * http://localhost:9999/user/get-author-by-name
****************************************************************************************/

	@GetMapping("/get-author-by-name/{name}")
	public ResponseEntity<Author> getAuthorByName(@PathVariable(name = "name") String name) {

		Author author = authorService.getAuthorByName(name);
		if (author == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(author));
	}

/********************************************************
	 * Method: getAllAuthors 
	 * Description: It is used to view all Authors in author table
	 * http://localhost:9999/user/get-all-authors
********************************************************/
	@GetMapping("/get-all-authors")
	public List<Author> getAuthors() {
		List<Author> allAuthorList = (List<Author>) authorService.getAllAuthors();
		return allAuthorList;
	}

}
