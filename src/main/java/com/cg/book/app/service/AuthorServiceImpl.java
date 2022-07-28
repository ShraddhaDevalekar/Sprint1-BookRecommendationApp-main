package com.cg.book.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.book.app.exception.AppUserNotFoundException;
import com.cg.book.app.exception.AuthorAlreadyExistsException;
import com.cg.book.app.exception.AuthorNotFoundException;
import com.cg.book.app.model.Author;
import com.cg.book.app.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AuthorRepository authorRepository;

	public List<Author> getAllAuthors() {
		List<Author> authorList = authorRepository.findAll();
		if (authorList.isEmpty()) {
			String exceptionMessage = "Authors don't exist in the database.";
			LOG.warn(exceptionMessage);
			throw new AppUserNotFoundException(exceptionMessage);
		} else {
			LOG.info("List returned successfully.");
			return authorList;
		}
	}

	@Override
	public Author getAuthorById(int id) {
		Author auth = authorRepository.findAuthorByAuthorId(id);
		if (auth == null) {
			String exceptionMessage = "Author does not exist in the database.";
			LOG.warn(exceptionMessage);
			throw new AuthorNotFoundException(exceptionMessage);
		} else {
			LOG.info("List returned successfully.");
			return auth;
		}
	}

	@Override
	public Author addAuthor(Author author) {
		Author auth = authorRepository.findAuthorByAuthorName(author.getAuthorName());
		if (auth != null) {
			String exceptionMessage = "Author already exist in the database.";
			LOG.warn(exceptionMessage);
			throw new AuthorAlreadyExistsException(exceptionMessage);
		} else {
			LOG.info("List returned successfully.");
			return authorRepository.save(author);
		}

	}

	@Override
	public Author getAuthorByName(String name) {
		Author auth = authorRepository.findAuthorByAuthorName(name);
		if (auth == null) {
			String exceptionMessage = "Author does not exist in the database.";
			LOG.warn(exceptionMessage);
			throw new AuthorNotFoundException(exceptionMessage);
		} else {
			LOG.info("List returned successfully.");
			return auth;
		}
	}

	@Override
	public void deleteAuthor(int id) {
		// TODO Auto-generated method stub
		Author auth = authorRepository.findAuthorByAuthorId(id);
		if (auth != null) {
			authorRepository.deleteById(id);
		} else {
			throw new AuthorNotFoundException("Author not found");
		}

	}

}