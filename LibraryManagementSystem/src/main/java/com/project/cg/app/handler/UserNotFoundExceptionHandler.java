package com.project.cg.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.project.cg.app.exception.UserNotFoundException;

@RestControllerAdvice
public class UserNotFoundExceptionHandler {
	@Autowired
	private JSONObjects obj;
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> bookNotFoundException(UserNotFoundException exe)
	{
		obj.setError(exe.getMessage());
		ResponseEntity<Object> retvalue=new ResponseEntity<Object>(obj,HttpStatus.NOT_FOUND);
		return retvalue;
	
}
}
