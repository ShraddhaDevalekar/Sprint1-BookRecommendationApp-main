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
import com.cg.book.app.model.Book;
import com.cg.book.app.service.BookService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	BookService bookservice;
	
/************************************************************************************
     * Method: get-all-books 
     * Description: It is used to view all books from books table /
*************************************************************************************/

	@GetMapping("/get-all-books")
	public List<Book> getBooks() {
		List<Book> allBookList = (List<Book>) bookservice.getAllBooks();
		return allBookList;
	}
	
/************************************************************************************
     * Method: get-book-by-id 
     * Description: It is used to view all books by id from books table /
*************************************************************************************/

	@GetMapping("/get-book-by-id/{id}")
	public ResponseEntity<Book> getBookByid(@PathVariable("id") int id) {
		Book book = bookservice.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
/************************************************************************************
     * Method: get-book-by-name 
     * Description: It is used to view all books by name from books table /
*************************************************************************************/
	
	@GetMapping("/get-book-by-name/{name}")
	public ResponseEntity<Book> getBookByName(@PathVariable("name") String name) {
		Book book = bookservice.getBookByName(name);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
}