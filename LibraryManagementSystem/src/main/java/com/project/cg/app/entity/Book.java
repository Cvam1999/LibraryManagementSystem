package com.project.cg.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name="Book")
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;
	@Column(name = "Title")
	private String title;
	private String author;
	private String publisher;
	private int quantity;
	private String genre;
	private int issuedBook;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getIssuedBook() {
		return issuedBook;
	}
	public void setIssuedBook(int issuedBook) {
		this.issuedBook = issuedBook;
	}
	public Book(int bookId, String title, String author, String publisher, int quantity, String genre,
			int issuedBook) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.quantity = quantity;
		this.genre = genre;
		this.issuedBook = issuedBook;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

