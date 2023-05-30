package com.project.cg.app.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table(name="Customer")
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cusId;
	private String cusName;
	private String cusEmail;
	private String password;
	private String address;
	private String contactNo;
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<IssuedBook> issuedBook;
	
	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public List<IssuedBook> getIssuedBook() {
		return issuedBook;
	}

	public void setIssuedBook(List<IssuedBook> issuedBook) {
		this.issuedBook = issuedBook;
	}
	

	public Customer(int cusId, String cusName, String cusEmail, String password, String address, String contactNo) {
		super();
		this.cusId = cusId;
		this.cusName = cusName;
		this.cusEmail = cusEmail;
		this.password = password;
		this.address = address;
		this.contactNo = contactNo;
		this.issuedBook = issuedBook;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
