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
import com.project.cg.app.entity.IssuedBook;
import com.project.cg.app.service.implementation.IssuedBookService;

@RestController
@RequestMapping("IssuedBookCtrl")
public class IssuedBookController {
	
	@Autowired
	private IssuedBookService service;
	
	@PostMapping(value="/addIssuedBook")
	public ResponseEntity<String> addBook(@RequestBody IssuedBook book){
		IssuedBook value=service.addIssuedBook(book);
		ResponseEntity<String> retValue=new ResponseEntity<>("Book issue with book no:"+ value.getIssueId(),HttpStatus.CREATED);
		return retValue;
	}
	@PutMapping(value="/updateIssuedBook/{id}")
	public ResponseEntity<String> updateBook(@RequestBody IssuedBook book,@PathVariable Integer id){
		IssuedBook value=service.updateIssuedBook(book,id);
		ResponseEntity<String> retValue=new ResponseEntity<>("IssuedBook updated with book no:"+ value.getIssueId(),HttpStatus.ACCEPTED);
		return retValue;
	}
	@DeleteMapping(value="/deleteIssuedBook/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id){
		service.deleteIssuedBook(id);
		ResponseEntity<String> retValue=new ResponseEntity<>("IssuedBook deleted with book no:"+ id,HttpStatus.OK);
		return retValue;
	}
	@GetMapping(value="/viewIssuedBookById/{id}")
	public ResponseEntity<IssuedBook> viewBook(@PathVariable Integer id){
		IssuedBook value=service.viewIssuedBook(id);
		ResponseEntity<IssuedBook> retValue=new ResponseEntity<>(value,HttpStatus.OK);
		return retValue;
	}
	@GetMapping(value="/viewAllBook")
	public ResponseEntity<List<IssuedBook>> viewAllBook(){
		List<IssuedBook> list=service.viewAllIssuedBook();
		ResponseEntity<List<IssuedBook>> retValue=new ResponseEntity<>(list,HttpStatus.OK);
		return retValue;
	}
}
