package com.project.cg.app.entity;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.cg.app.entity.Book;
import com.project.cg.app.entity.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class IssuedBook {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int issueId;
	@OneToOne
	private Book book;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer user;
	private LocalDate issueDate;
	private LocalDate dueDate;

	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Customer getUser() {
		return user;
	}
	public void setUser(Customer user) {
		this.user = user;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public IssuedBook(int issueId, Book book, Customer user, LocalDate issueDate, LocalDate dueDate) {
		super();
		this.issueId = issueId;
		this.book = book;
		this.user = user;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
	}
	public IssuedBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
