package com.project.cg.app.controller;

import java.util.List;

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
	
	@Autowired
	private BookService service;
	
	@PostMapping(value="/addBook")
	public ResponseEntity<String> addBook(@RequestBody Book book){
		Book value=service.addBook(book);
		ResponseEntity<String> retValue=new ResponseEntity<>("Book added with book no"+ value.getBookId(),HttpStatus.CREATED);
		return retValue;
	}
	@PutMapping(value="/updateBook/{id}")
	public ResponseEntity<String> updateBook(@RequestBody Book book,@PathVariable Integer id){
		Book value=service.updateBook(book,id);
		ResponseEntity<String> retValue=new ResponseEntity<>("Book updated with book no"+ value.getBookId(),HttpStatus.ACCEPTED);
		return retValue;
	}
	@DeleteMapping(value="/deleteBook/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id){
		service.deleteBook(id);
		ResponseEntity<String> retValue=new ResponseEntity<>("Book deleted with book no"+ id,HttpStatus.OK);
		return retValue;
	}
	@GetMapping(value="/viewBookById/{id}")
	public ResponseEntity<Book> viewBook(@PathVariable Integer id){
		Book value=service.viewBook(id);
		ResponseEntity<Book> retValue=new ResponseEntity<>(value,HttpStatus.OK);
		return retValue;
	}
	@GetMapping(value="/viewAllBook")
	public ResponseEntity<List<Book>> viewAllBook(){
		List<Book> list=service.viewAllBook();
		ResponseEntity<List<Book>> retValue=new ResponseEntity<>(list,HttpStatus.OK);
		return retValue;
	}
	
}

