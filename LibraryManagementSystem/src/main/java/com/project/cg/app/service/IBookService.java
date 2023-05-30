package com.project.cg.app.service;

import java.util.List;

import com.project.cg.app.entity.Book;

public interface IBookService {
	public Book addBook(Book book);
	public Book updateBook(Book book,int bookId);
	public void deleteBook(int bookId);
	public Book viewBook(int bookId);
	public List<Book> viewAllBook();
}
