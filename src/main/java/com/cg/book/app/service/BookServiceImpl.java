package com.cg.book.app.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.book.app.exception.AuthorAlreadyExistsException;
import com.cg.book.app.exception.BookAlreadyExistsException;
import com.cg.book.app.exception.BookNotFoundException;
import com.cg.book.app.model.Author;
import com.cg.book.app.model.Book;
import com.cg.book.app.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books;
	}

	@Override
	public Book addBook(Book book) {
			Book b = bookRepository.findBookByBookName(book.getBookName());
			if (b != null) {
				String exceptionMessage = "Book already exist in the database.";
				LOG.warn(exceptionMessage);
				throw new BookAlreadyExistsException(exceptionMessage);
			} else {
				LOG.info("List returned successfully.");
				return bookRepository.save(b);
			}

		}

	

	@Override
	public Book getBookById(int id) {

		return bookRepository.findBookByBookId(id);
	}

	@Override
	public Book getBookByName(String name) {
		return bookRepository.findBookByBookName(name);
	}

	@Override
	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		Book book = bookRepository.findBookByBookId(id);
		if (book != null) {
			bookRepository.deleteById(id);
		} else {
			throw new BookNotFoundException("Book not found");
		}

	}

	@Override
	public Book UpdateBookById(int id, Book book) {
		Book bookForUpdate = bookRepository.findBookByBookId(id);
		if (bookForUpdate != null) {
			return bookRepository.save(book);
		} else {
			throw new BookNotFoundException("Book not found");
		}

	}
}