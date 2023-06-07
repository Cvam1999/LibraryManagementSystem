package com.project.cg.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.project.cg.app.exception.UserNotFoundException;


/*
Exception: UserNotFoundException
This is the exception handler of custom UserNotFoundException exception
and it is handled by using annotation @ExceptionHandler
and it is returning a error message string with response status 404 [not found]
by using HttpStatus.NOT_FOUND
*/
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
