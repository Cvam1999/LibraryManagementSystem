package com.project.cg.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="Book")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;
	@Column(name = "Title")
	@NotBlank
	private String title;
	@NotBlank
	private String author;
	@NotBlank
	private String publisher;
	@Min(value = 1)
	@Max(value = 100)
	private int quantity;
	@NotBlank
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
//	public Book(int bookId, String title, String author, String publisher, int quantity, String genre,
//			int issuedBook) {
//		super();
//		this.bookId = bookId;
//		this.title = title;
//		this.author = author;
//		this.publisher = publisher;
//		this.quantity = quantity;
//		this.genre = genre;
//		this.issuedBook = issuedBook;
//	}
//	public Book() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
	

}

