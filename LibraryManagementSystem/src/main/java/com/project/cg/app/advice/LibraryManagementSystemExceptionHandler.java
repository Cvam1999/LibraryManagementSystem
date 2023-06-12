package com.project.cg.app.advice;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LibraryManagementSystemExceptionHandler {
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public LinkedHashMap<String, String> handleBardRequest(MethodArgumentNotValidException me){
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();		
		
		me.getFieldErrors().stream().forEach(fieldError -> {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
				
		return errors;
	}

}
