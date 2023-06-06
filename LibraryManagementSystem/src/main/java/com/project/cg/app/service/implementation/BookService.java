package com.project.cg.app.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cg.app.service.IBookService;
import com.project.cg.app.entity.Book;
import com.project.cg.app.exception.BookNotFoundException;
import com.project.cg.app.repository.BookRepository;

@Service
public class BookService implements IBookService {
	@Autowired
	private BookRepository repository;

	@Override
	public Book addBook(Book book) {
		Book result=repository.save(book);
		return result;
	}

	@Override
	public Book updateBook(Book book, int bookId) {
		Book value=repository.findById(bookId).orElseThrow(()-> new BookNotFoundException("this bookId not found in datababse"));
		value.setTitle(book.getTitle());
		value.setAuthor(book.getAuthor());
		value.setPublisher(book.getPublisher());
		value.setGenre(book.getGenre());
		value.setQuantity(book.getQuantity());
		value.setIssuedBook(book.getIssuedBook());
		return repository.save(value);
	}

	@Override
	public void deleteBook(int bookId) {
		Book value=repository.findById(bookId).orElseThrow(()->new BookNotFoundException("book not found with id:"+bookId ));
		repository.delete(value);
		
		
	}

	@Override
	public Book viewBook(int bookId) {
		return repository.findById(bookId).orElseThrow(()->new BookNotFoundException("book not found with id:"+bookId ));
	}

	@Override
	public List<Book> viewAllBook() {
		List<Book> allBooks=repository.findAll();
		return allBooks;
	}
}
