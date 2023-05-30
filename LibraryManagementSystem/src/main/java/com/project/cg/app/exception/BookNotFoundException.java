package com.project.cg.app.exception;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(String message) {
		super(message);
	}
}
