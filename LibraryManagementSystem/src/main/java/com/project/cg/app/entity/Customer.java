package com.project.cg.app.entity;

import java.util.List;

import org.hibernate.validator.constraints.Range;

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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name="Customer")
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cusId;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$",message = "Format For UserName is Wrong\\r\\n \"\r\n"
			+ "			+ \"\\r\\n \"\r\n"
			+ "			+ \" Please Enter Again :\\r\\n\"\r\n"
			+ "			+ \"____________________________________________________________\\r\\n\"\r\n"
			+ "			+ \"\\r\\n\"\r\n"
			+ "			+ \"Valid Format for UserName:\\r\\n\"\r\n"
			+ "			+ \"\\r\\n\"\r\n"
			+ "			+ \"The first character of the username must be an alphabetic character, i.e., either lowercase character\\r\\n\"\r\n"
			+ "			+ \"[a � z] or uppercase character [A � Z].\\r\\n\"\r\n"
			+ "			+ \"User Name length should be in range 3 to 30.\"\r\n"
			+ "			+ \"\\r\\n")
	@Size(min = 3, max = 30)
	private String cusName;

	@NotBlank(message = "Email cannot be empty")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message="{register.email.invalid}")
	private String cusEmail;
	//@Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$")
	@NotBlank(message = "Password cannot be empty")
	private String password;
	@NotBlank(message = "Address cannot be empty")
	private String address;
	@NotBlank(message = "ContactNo cannot be empty")
	@Pattern(regexp="^(\\+91[\\-\\s]?)?[0]?[789]\\d{9}$",message = "enter correct mobile number")
	private String contactNo;
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<IssuedBookList> issuedBook;
	
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

	public List<IssuedBookList> getIssuedBook() {
		return issuedBook;
	}

	public void setIssuedBook(List<IssuedBookList> issuedBook) {
		this.issuedBook = issuedBook;
	}
	

//	public Customer(int cusId, String cusName, String cusEmail, String password, String address, String contactNo) {
//		super();
//		this.cusId = cusId;
//		this.cusName = cusName;
//		this.cusEmail = cusEmail;
//		this.password = password;
//		this.address = address;
//		this.contactNo = contactNo;
//		this.issuedBook = issuedBook;
//	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
