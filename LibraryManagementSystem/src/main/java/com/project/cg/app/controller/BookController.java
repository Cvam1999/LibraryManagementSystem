package com.project.cg.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cg.app.entity.Book;
import com.project.cg.app.service.implementation.BookService;

@RestController
@RequestMapping("/Book")
public class BookController {
	final static Logger logger=LogManager.getLogger(BookController.class);
	@Autowired
	private BookService service;
	
	@PostMapping(value="/addBook")
	public ResponseEntity<String> addBook(@RequestBody Book book){
		Book value=service.addBook(book);
		ResponseEntity<String> retValue=new ResponseEntity<>("Book with book no"+ value.getBookId()+" sucessfully created.",HttpStatus.CREATED);
		logger.info("addBook() has executed");
		return retValue;
	}
	@PutMapping(value="/updateBook/{id}")
	public ResponseEntity<String> updateBook(@RequestBody Book book,@PathVariable Integer id){
		Book value=service.updateBook(book,id);
		ResponseEntity<String> retValue=new ResponseEntity<>("Book with book no"+ value.getBookId()+" sucessfully updated.",HttpStatus.ACCEPTED);
		logger.info("updateBook() has executed");
		return retValue;
	}
	@DeleteMapping(value="/deleteBook/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id){
		service.deleteBook(id);
		ResponseEntity<String> retValue=new ResponseEntity<>("Book with book no"+ id+" sucessfully deleted.",HttpStatus.OK);
		logger.info("deleteBook() has executed");
		return retValue;
	}
	@GetMapping(value="/viewBookById/{id}")
	public ResponseEntity<Book> viewBook(@PathVariable Integer id){
		Book value=service.viewBook(id);
		ResponseEntity<Book> retValue=new ResponseEntity<>(value,HttpStatus.OK);
		logger.info("viewBook() has executed");
		return retValue;
	}
	@GetMapping(value="/viewAllBook")
	public ResponseEntity<List<Book>> viewAllBook(){
		List<Book> list=service.viewAllBook();
		ResponseEntity<List<Book>> retValue=new ResponseEntity<>(list,HttpStatus.OK);
		logger.info("viewAllBook() has executed");
		return retValue;
	}
	
}

