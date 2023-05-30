package com.project.cg.app.exception;

public class BookCannotBeIssueException extends RuntimeException{
	public BookCannotBeIssueException(String message) {
		super(message);
	}
}
