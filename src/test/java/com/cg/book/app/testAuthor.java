package com.cg.book.app;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.book.app.exception.AuthorAlreadyExistsException;
import com.cg.book.app.exception.AuthorNotFoundException;
import com.cg.book.app.model.Author;
import com.cg.book.app.repository.AuthorRepository;
import com.cg.book.app.service.AuthorService;

//RunWith annotation is used to run this test with SpringRunnerclass
@RunWith(SpringRunner.class)

//
//annotation works by creating the ApplicationContext used in your tests through SpringApplication.
@SpringBootTest 

class testAuthor {

	
	@Autowired
	private AuthorService authorService;
	
	//mocks the data from repository
	@MockBean 
	private AuthorRepository authorRepository;
	
//	
//	@Test 
//	public void testPositiveDelete()throws AuthorNotFoundException{
//		Author author = new Author();
//		author.setAuthorId(100);
//		
//		//if the ID exists then remove the data
//		Mockito.when(authorRepository.existsById(100)).thenReturn(true);
//		authorService.deleteAuthor(100);
//			
//		//Mockito.atLeastOnce()) checks if the delete method is invoked once	
//		verify(authorRepository,Mockito.atLeastOnce()).deleteById(100);
//	}

//	 
	//test case for negative search.
	@Test
	public void testNegativeFindById() throws AuthorNotFoundException {
		Author author = new Author();
		author.setAuthorId(100);
		
		//The exception will be thrown to Executable if the search is negative.
		Executable ex=()->{
			authorService.getAuthorById(100);
		};
		
		//verifies if the IDs match or not
		verify(authorRepository,never()).findById(100);
		
		//the exception is now thrown when verification is false.
		Assertions.assertThrows(AuthorNotFoundException.class, ex);
	}

	@Test
	 public void showAllAuthorsTest() {
		
		//if the findAll() method is true, case returns the below two values.
		Mockito.when(authorRepository.findAll())
		.thenReturn(Stream.of(new Author(),
				new Author()).
				collect(Collectors.toList()));
		
		//expected number of data =2
		assertEquals(2,authorService.getAllAuthors().size());
	}







}













