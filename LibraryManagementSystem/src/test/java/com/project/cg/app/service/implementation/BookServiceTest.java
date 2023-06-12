package com.project.cg.app.service.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doNothing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.cg.app.entity.Book;

import com.project.cg.app.repository.BookRepository;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
	
	@Test
	void testViewAllBooks() {
		
		List<Book> books = getBooksMockData();		
		Mockito.when(bookRepository.findAll()).thenReturn(books);
		
		List<Book> bookInfo = bookService.viewAllBook();
		
		
		assertThat(bookInfo.get(1).getAuthor().equals("shivam"));
	}
	
	@Test
	void testGetBook() {
		
		int bookId = 10;
		
		Optional<Book> book = getBookMockData();
		
		Mockito.when(bookRepository.findById(bookId)).thenReturn(book);
		
		Book book1 = bookService.viewBook(bookId);

		assertThat(book1.getAuthor().equals("suraj"));
		assertThat(book1.getTitle().equals("java"));
	}
	
	@Test
	void testAddBook() {
		
		Optional<Book> bookOpt = getBookMockData();
		Book book =bookOpt.get();
		Mockito.when(bookRepository.save(book)).thenReturn(book);
	
		Book book1 = bookService.addBook(book);
		
		assertEquals("java", book.getTitle());
		assertThat(book.getTitle().equals("java"));
		assertThat(book.getQuantity()==book1.getQuantity());
	}
	
	@Test
	void testUpdateBook() {
		
		int bookId = 10;
		Optional<Book> bookOpt = getBookMockData();
		Book book =bookOpt.get();
		Mockito.when(bookRepository.findById(bookId)).thenReturn(bookOpt);
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		
		Book updatedBook = bookService.updateBook(book, bookId);
		
		assertThat(book.getAuthor().equals(updatedBook.getAuthor()));
		assertThat(book.getTitle().equals(updatedBook.getTitle()));
		
	}
	
	@Test
	void testDeleteBook()  {
		
		int bookId = 10;
		Optional<Book> bookOpt = getBookMockData();
		Book book =bookOpt.get();
		Mockito.when(bookRepository.findById(bookId)).thenReturn(bookOpt);
		doNothing().when(bookRepository).delete(book);
	
		bookService.deleteBook(bookId);
		
		
		assertThat(book.getTitle().equals("java"));
		
	}	
	
	
	private List<Book> getBooksMockData(){
		List<Book> books = new ArrayList<>();
			Book b1=new Book();
			b1.setBookId(1);
			b1.setAuthor("cvam");
			b1.setGenre("coding");
			b1.setQuantity(10);
			
			Book b2=new Book();
			b2.setBookId(2);
			b2.setAuthor("shivam");
			b2.setGenre("history");
			b2.setQuantity(11);
			
			books.add(b1);
			books.add(b2);
		return books;
	}
	
	private Optional<Book> getBookMockData(){
		
		Book b=new Book();
		b.setBookId(10);
		b.setAuthor("suraj");
		b.setTitle("java");
		return Optional.of(b);
	}
}
