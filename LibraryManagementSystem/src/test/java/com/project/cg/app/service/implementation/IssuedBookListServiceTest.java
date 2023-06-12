package com.project.cg.app.service.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.cg.app.entity.IssuedBookList;
import com.project.cg.app.repository.BookRepository;
import com.project.cg.app.repository.IssuedBookListRepository;

@ExtendWith(MockitoExtension.class)
public class IssuedBookListServiceTest {
	@InjectMocks
	private IssuedBookListService issuedBookListService;
	
	@Mock
	private IssuedBookListRepository issuedBookListRepository;
	
	
	
	@Test
	void testViewAllIssuedBookList() {
		
		List<IssuedBookList> books = getBooksMockData();		
		Mockito.when(issuedBookListRepository.findAll()).thenReturn(books);
		
		List<IssuedBookList> bookInfo = issuedBookListService.viewAllIssuedBook();
		
		assertThat(bookInfo.size() == books.size());
		
	}
	
	@Test
	void testGetBook() {
		
		int bookId = 10;
		
		Optional<IssuedBookList> book = getBookMockData();
		
		Mockito.when(issuedBookListRepository.findById(bookId)).thenReturn(book);
		
		IssuedBookList book1 = issuedBookListService.viewIssuedBook(bookId);

		assertThat(book1.getIssueId()==10);
		
	}
	
	@Test
	void testAddBook() {
		
		Optional<IssuedBookList> bookOpt = getBookMockData();
		IssuedBookList book =bookOpt.get();
		Mockito.when(issuedBookListRepository.save(book)).thenReturn(book);
	
		IssuedBookList book1 = issuedBookListService.addIssuedBook(book);
		
		assertThat(book.getIssueId()==book1.getIssueId());
	}
	
	@Test
	void testUpdateBook() {
		
		int bookId = 10;
		Optional<IssuedBookList> bookOpt = getBookMockData();
		IssuedBookList book =bookOpt.get();
		Mockito.when(issuedBookListRepository.findById(bookId)).thenReturn(bookOpt);
		Mockito.when(issuedBookListRepository.save(book)).thenReturn(book);
		
		IssuedBookList updatedBook = issuedBookListService.updateIssuedBook(book, bookId);
		
		assertThat(book.getIssueId()==updatedBook.getIssueId());
		
		
	}
	
	@Test
	void testDeleteBook()  {
		
		int bookId = 10;
		Optional<IssuedBookList> bookOpt = getBookMockData();
		IssuedBookList book =bookOpt.get();
		Mockito.when(issuedBookListRepository.findById(bookId)).thenReturn(bookOpt);
		doNothing().when(issuedBookListRepository).delete(book);
		
	
		issuedBookListService.deleteIssuedBook(bookId);
		
		assertThat(book.getIssueId()==10);
		
		
	}	
	
	
	private List<IssuedBookList> getBooksMockData(){
		List<IssuedBookList> books = new ArrayList<>();
			IssuedBookList b1=new IssuedBookList();
			b1.setIssueId(1);
			b1.setIssueDate(LocalDate.now());
			b1.setDueDate(LocalDate.now());
		
			
			IssuedBookList b2=new IssuedBookList();
			b1.setIssueId(2);
			b1.setIssueDate(LocalDate.now());
			b1.setDueDate(LocalDate.now());
			
			books.add(b1);
			books.add(b2);
		return books;
	}
	
	private Optional<IssuedBookList> getBookMockData(){
		
		IssuedBookList b=new IssuedBookList();
		b.setIssueId(10);
		b.setIssueDate(LocalDate.now());
		b.setDueDate(LocalDate.now());
		return Optional.of(b);
	}
	
}
