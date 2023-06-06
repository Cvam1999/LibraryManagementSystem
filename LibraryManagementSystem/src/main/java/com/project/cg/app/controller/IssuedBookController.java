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
import com.project.cg.app.entity.IssuedBook;
import com.project.cg.app.service.implementation.IssuedBookService;

@RestController
@RequestMapping("IssuedBook")
public class IssuedBookController {
	
	private static final Logger logger = LogManager.getLogger(IssuedBookController.class);
	@Autowired
	private IssuedBookService service;
	
	@PostMapping(value="/addIssuedBook")
	public ResponseEntity<String> addBook(@RequestBody IssuedBook book){
		logger.info("----addBook() method initialized");
		IssuedBook value=service.addIssuedBook(book);
		ResponseEntity<String> retValue=new ResponseEntity<>("Book issue with book no:"+ value.getIssueId(),HttpStatus.CREATED);
		logger.info("addBook() has executed");
		return retValue;
	}
	@PutMapping(value="/updateIssuedBook/{id}")
	public ResponseEntity<String> updateBook(@RequestBody IssuedBook book,@PathVariable Integer id){
		logger.info("----updateBook() method initialized");
		IssuedBook value=service.updateIssuedBook(book,id);
		ResponseEntity<String> retValue=new ResponseEntity<>("IssuedBook updated with book no:"+ value.getIssueId(),HttpStatus.ACCEPTED);
		logger.info("updateBook() has executed");
		return retValue;
	}
	@DeleteMapping(value="/deleteIssuedBook/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id){
		logger.info("----deleteBook() method initialized");
		service.deleteIssuedBook(id);
		ResponseEntity<String> retValue=new ResponseEntity<>("IssuedBook deleted with book no:"+ id,HttpStatus.OK);
		logger.info("deleteBook() has executed");
		return retValue;
	}
	@GetMapping(value="/viewIssuedBookById/{id}")
	public ResponseEntity<IssuedBook> viewBook(@PathVariable Integer id){
		logger.info("----viewBook() method initialized");
		IssuedBook value=service.viewIssuedBook(id);
		ResponseEntity<IssuedBook> retValue=new ResponseEntity<>(value,HttpStatus.OK);
		logger.info("viewBook() has executed");
		return retValue;
	}
	@GetMapping(value="/viewAllBook")
	public ResponseEntity<List<IssuedBook>> viewAllBook(){
		logger.info("----viewAllBook() method initialized");
		List<IssuedBook> list=service.viewAllIssuedBook();
		ResponseEntity<List<IssuedBook>> retValue=new ResponseEntity<>(list,HttpStatus.OK);
		logger.info("viewAllBook() has executed");
		return retValue;
	}
}
