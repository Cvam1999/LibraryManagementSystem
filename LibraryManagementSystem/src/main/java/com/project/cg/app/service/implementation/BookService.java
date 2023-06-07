package com.project.cg.app.service.implementation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cg.app.service.IBookService;
import com.project.cg.app.entity.Book;
import com.project.cg.app.exception.BookNotFoundException;
import com.project.cg.app.repository.BookRepository;

@Service
public class BookService implements IBookService {
	final static Logger logger=LogManager.getLogger(BookService.class);
	@Autowired
	private BookRepository repository;

	/*
	 * MethodName  : addBook
	 * Description : Method will add book into database
	 * Input Arguments : Book Object
	 * Return Value : Book Object 
	 */
	
	@Override
	public Book addBook(Book book) {
		logger.info("Business method addBook initiated");
		Book result=repository.save(book);
		logger.info("Business method addBook executed");
		return result;
	}
	
	/*
	 * MethodName  : updateBook
	 * Description : Method will update existing Book details
	 * Input Arguments : Book Object, Book Id(Primary key) Integer 
	 * Return Value : Book Object 
	 * Exception : BookNotFoundException
	 */

	@Override
	public Book updateBook(Book book, int bookId) throws BookNotFoundException {
		logger.info("Business method updateBook initiated");
		Book value=repository.findById(bookId).orElseThrow(()-> new BookNotFoundException("this bookId not found in datababse"));
		value.setTitle(book.getTitle());
		value.setAuthor(book.getAuthor());
		value.setPublisher(book.getPublisher());
		value.setGenre(book.getGenre());
		value.setQuantity(book.getQuantity());
		value.setIssuedBook(book.getIssuedBook());
		logger.info("Business method updateBook executed");
		return repository.save(value);
	}
	
	/*
	 * MethodName  : deleteBook
	 * Description : Method will delete existing Book details
	 * Input Arguments : Book Id(Primary key) Integer 
	 * Return Value : void 
	 * Exception : BookNotFoundException
	 */

	@Override
	public void deleteBook(int bookId) throws BookNotFoundException {
		logger.info("Business method deleteBook initiated");
		Book value=repository.findById(bookId).orElseThrow(()->new BookNotFoundException("book not found with id:"+bookId ));
		logger.info("Business method deleteBook executed");
		repository.delete(value);
	}
	
	/*
	 * MethodName  : viewBook
	 * Description : Method will show existing Book details
	 * Input Arguments : Book Id(Primary key) Integer 
	 * Return Value : Book Object 
	 * Exception : BookNotFoundException
	 */

	@Override
	public Book viewBook(int bookId) throws BookNotFoundException {
		logger.info("Business method viewBook initiated");
		return repository.findById(bookId).orElseThrow(()->new BookNotFoundException("book not found with id:"+bookId ));
	}
	
	/*
	 * MethodName  : viewAllBook
	 * Description : Method will show all existing Books details
	 * Input Arguments : None 
	 * Return Value : List<Book> 
	 */

	@Override
	public List<Book> viewAllBook() {
		logger.info("Business method viewAllBook initiated");
		List<Book> allBooks=repository.findAll();
		logger.info("Business method viewAllBook executed");
		return allBooks;
	}
}
