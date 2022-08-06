package com.cg.book.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.book.app.model.Author;
import com.cg.book.app.model.Book;
import com.cg.book.app.model.Category;
import com.cg.book.app.service.AuthorService;
import com.cg.book.app.service.BookService;
import com.cg.book.app.service.CategoryService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")


public class AdminController {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private CategoryService categoryService;
	
/************************************************************************************
	 * Method: get-all-books 
	 * Description: It is used to view all books from books table /
*************************************************************************************/
	
	@GetMapping("/get-all-books")
	public List<Book> getBooks() {
		List<Book> allBookList = (List<Book>) bookService.getAllBooks();
		return allBookList;
	}

/************************************************************************************
	 * Method: get-book-by-id 
	 * Description: It is used to view the book by id from books table /
*************************************************************************************/
	
	@GetMapping("/get-book-by-id/{id}")
	public ResponseEntity<Book> getBookByid(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
/************************************************************************************
	 * Method: get-book-by-name 
	 * Description: It is used to view books by book name from books table /
*************************************************************************************/
	
	@GetMapping("/get-book-by-name/{name}")
	public ResponseEntity<Book> getBookByName(@PathVariable("name") String name) {
		Book book = bookService.getBookByName(name);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
/************************************************************************************
	 * Method: add-book 
	 * Description: It is used to add the books to books table /
*************************************************************************************/

//	@SuppressWarnings("null")
	@PostMapping(value = "/add-book")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {

		Book b = bookService.addBook(book);
		if (b != null) {
			return new ResponseEntity<Book>(b,  HttpStatus.CREATED);

		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}

/************************************************************************************
	 * Method: updateBookById 
	 * Description: It is used to update the books by id in books table /
*************************************************************************************/
	
	@PutMapping("/updateBookById/{id}")
	public Book updateBookById(@PathVariable("id") int id, @RequestBody Book book) {

		Book b = bookService.getBookById(id);

		if (book.getBookName() != null || book.getBookName().trim().length() > 0) {
			b.setBookName(book.getBookName());
		}

		if (book.getPrice() > 0.0) {
			b.setPrice(book.getPrice());
		}

		if (book.getRating() > 0.0) {
			b.setRating(book.getRating());
		}

		if (book.getCategory() != null) {
			Category c = new Category();
			c.setCategoryId(book.getCategory().getCategoryId());
			c.setCategoryName(book.getCategory().getCategoryName());
			b.setCategory(book.getCategory());
		}

		if (book.getAuthor() != null) {
			Author a = new Author();
			a.setAuthorId(book.getAuthor().getAuthorId());
			a.setAuthorName(book.getAuthor().getAuthorName());
			b.setAuthor(book.getAuthor());
		}

		Book updatedBook = bookService.UpdateBookById(id, b);

		return updatedBook;
	}

/************************************************************************************
	 * Method: deleteBookById 
	 * Description: It is used to delete the book by id from books table /
*************************************************************************************/

	// @SuppressWarnings("null")
	@DeleteMapping("/deleteBookById/{id}")
	public Map<String, Boolean> deleteBookById(@PathVariable("id") int id) {

		bookService.deleteBook(id);

		Map<String, Boolean> res = new HashMap<String, Boolean>();
		res.put("Deleted", true);
		return res;
	}
/************************************************************************************
	 * Method: deleteAuthorById 
	 * Description: It is used to delete the author by id from author table /
*************************************************************************************/	
	
	// @SuppressWarnings("null")
		@DeleteMapping("/deleteAuthorById/{id}")
		public Map<String, Boolean> deleteAuthorById(@PathVariable("id") int id) {

			authorService.deleteAuthor(id);

			Map<String, Boolean> res = new HashMap<String, Boolean>();
			res.put("Deleted", true);
			return res;
		}

	
/************************************************************************************
	 * Method: addAuthor
	 * Description: It is used to add the authors in author table /
*************************************************************************************/

//	@SuppressWarnings("null")
	@PostMapping(value = "/addAuthor")
	public ResponseEntity<Author> addAuthor(@RequestBody Author author) {

		Author a = authorService.addAuthor(author);
		if (a != null) {
			return new ResponseEntity<Author>(a,  HttpStatus.CREATED);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	
/************************************************************************************
	 * Method: get-author-by-id 
	 * Description: It is used to view authors by id from author table /
*************************************************************************************/

	@GetMapping("/get-author-by-id/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable(name = "id") int id) {

		Author author = authorService.getAuthorById(id);
		if (author == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(author));
	}

/************************************************************************************
	 * Method: get-author-by-name 
	 * Description: It is used to view all authors by name from author table /
*************************************************************************************/
	
	@GetMapping("/get-author-by-name/{name}")
	public ResponseEntity<Author> getAuthorByName(@PathVariable(name = "name") String name) {

		Author author = authorService.getAuthorByName(name);
		if (author == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(author));
	}

/************************************************************************************
	 * Method: get-all-authors 
	 * Description: It is used to view all authors from author table /
*************************************************************************************/	

	@GetMapping("/get-all-authors")
	public List<Author> getAuthors() {
		List<Author> allAuthorList = (List<Author>) authorService.getAllAuthors();
		return allAuthorList;
	}

/************************************************************************************
	 * Method: addCategory 
	 * Description: It is used to add category in categories table /
*************************************************************************************/
	
	// @SuppressWarnings("null")
	@PostMapping(value = "/addCategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {

		Category c = categoryService.addCategory(category);
		if (c != null) {
			return new ResponseEntity<Category>(c, HttpStatus.CREATED);
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}
	
/************************************************************************************
	 * Method: get-all-categories 
	 * Description: It is used to view all categories from categories table /
*************************************************************************************/
	
	@GetMapping("/getAllCategory")
	public List<Category> getAllCategory() {
		List<Category> allCategoryList = (List<Category>) categoryService.getAllCategory();
		return allCategoryList;
	}

}