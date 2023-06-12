package com.project.cg.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDate;

import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.cg.app.entity.Book;
import com.project.cg.app.entity.Customer;
import com.project.cg.app.entity.IssuedBookList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
class LibraryManagementSystemApplicationTests {
	
	   private MockMvc mvc;
	 	
	   @Autowired
	   private WebApplicationContext context;
	   
	   ObjectMapper objectMapper = new ObjectMapper();
	  
//	   public void setUp() {
//	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
//	   }
	  
	  
	   
	   @Test
	   public void  addBook() throws Exception {
		   String uri = "/Book/addBook";
		   Book book =new Book();
		   book.setAuthor("cvam");
		   book.setPublisher("cvam");
		   book.setGenre("coding");
		   book.setQuantity(12);
		   book.setTitle("java");
		   book.setIssuedBook(0);
		   String jsonRequest= objectMapper.writeValueAsString(book);
		   mvc = MockMvcBuilders.webAppContextSetup(context).build();
		   
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				      .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(201, status);
	  
	   }
	   @Test
	   public void updateBook() throws Exception {
	      String uri = "/Book/updateBook/852";
	      Book book =new Book();
	       book.setAuthor("cvam");
		   book.setPublisher("cvam");
		   book.setGenre("coding");
		   book.setQuantity(12);
		   book.setTitle("python");
		   book.setIssuedBook(0);
	      
		   String jsonRequest= objectMapper.writeValueAsString(book);
		   mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(202, status);
	      //assertThat(book.getAuthor().equals("cvam"));
	     
	   }
	   @Test
	   public void deleteBook() throws Exception {
	      String uri = "/Book/deleteBook/1452";
	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      //Assert.assertTrue(status==200);
	      assertEquals(200, status);
	    
	   }
	   @Test
	   public void getBooksList() throws Exception {
	      String uri = "/Book/viewAllBook";
	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      
	   }
	   @Test
	   public void getBookById() throws Exception {
	      String uri = "/Book/viewBookById/702";
	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      
	   }
	   @Test
	   public void  addCustomer() throws Exception {
		   String uri = "/Customer/addCustomer";
		   Customer customer =new Customer();
		   customer.setAddress("uk");
		   customer.setContactNo("8884343244");
		   customer.setCusEmail("aakas@gmail.com");
		   customer.setCusName("aakash");
		   customer.setPassword("Aakash@123");
		   
		   String jsonRequest= objectMapper.writeValueAsString(customer);
		   mvc = MockMvcBuilders.webAppContextSetup(context).build();
		   
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				      .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(201, status);
		  
	   }
	   @Test
	   public void updateCustomer() throws Exception {
	      String uri = "/Customer/updateCustomer/2653";
	      Customer customer =new Customer();
	      customer.setAddress("uk");
		   customer.setContactNo("9884343244");
		   customer.setCusEmail("suraj@gmail.com");
		   customer.setCusName("suraj");
		   customer.setPassword("Suraj@123");
	      
		   String jsonRequest= objectMapper.writeValueAsString(customer);
		   mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(202, status);
	      //assertThat(book.getAuthor().equals("cvam"));
	     
	   }
	   @Test
	   public void deleteCustomer() throws Exception {
	      String uri = "/Customer/deleteCustomer/3002";
	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      //Assert.assertTrue(status==200);
	      assertEquals(200, status);
	    
	   }
	   @Test
	   public void getCustomersList() throws Exception {
	      String uri = "/Customer/viewAllCustomer";
	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      
	   }
	   @Test
	   public void getCustomerById() throws Exception {
	      String uri = "/Customer/viewCustomerById/2652/Shivam@123";
	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      
	   }
	   
//	   @Test
//	   public void  addIssuedBookList() throws Exception {
//		   String uri = "/IssuedBookList/addIssuedBook";
//		   IssuedBookList issuedBooklist =new IssuedBookList();
//		   Book book =new Book();
//		   book.setBookId(852);
//		   book.setAuthor("cvam");
//		   book.setPublisher("cvam");
//		   book.setGenre("coding");
//		   book.setQuantity(12);
//		   book.setTitle("java");
//		   book.setIssuedBook(0);
//		   Customer customer =new Customer();
//		   customer.setCusId(2852);
//		   customer.setAddress("uk");
//		   customer.setContactNo("8884343244");
//		   customer.setCusEmail("aakas@gmail.com");
//		   customer.setCusName("aakash");
//		   customer.setPassword("Aakash@123");
//		   issuedBooklist.setBook(book);
//		   issuedBooklist.setUser(customer);
//		   issuedBooklist.setIssueDate(LocalDate.now());
//		   issuedBooklist.setDueDate(LocalDate.now().plusDays(15));
//		   
//		   String jsonRequest= objectMapper.writeValueAsString(issuedBooklist);
//		   mvc = MockMvcBuilders.webAppContextSetup(context).build();
//		   
//		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//				      .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest)).andReturn();
//		   
//		   int status = mvcResult.getResponse().getStatus();
//		   assertEquals(201, status);
//		  
//	   }
//	   @Test
//	   public void updateIssuedBookList() throws Exception {
//	      String uri = "/IssuedBookList/updateIssuedBook/102";
//	      IssuedBookList issuedBooklist =new IssuedBookList();
//	      Book book =new Book();
//	      book.setBookId(852);
//		   book.setAuthor("cvam");
//		   book.setPublisher("cvam");
//		   book.setGenre("coding");
//		   book.setQuantity(12);
//		   book.setTitle("python");
//		   Customer customer =new Customer();
//		   customer.setCusId(2702);
//		   customer.setAddress("uk");
//		   customer.setContactNo("8884343244");
//		   customer.setCusEmail("aakas@gmail.com");
//		   customer.setCusName("aakash");
//		   customer.setPassword("Aakash@123");
//	      issuedBooklist.setBook(book);
//	      issuedBooklist.setUser(null);
//		   issuedBooklist.setIssueDate(LocalDate.now());
//		   issuedBooklist.setDueDate(LocalDate.now().plusDays(19));
//	      
//		   String jsonRequest= objectMapper.writeValueAsString(issuedBooklist);
//		   mvc = MockMvcBuilders.webAppContextSetup(context).build();
//	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
//	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest)).andReturn();
//	      
//	      int status = mvcResult.getResponse().getStatus();
//	      assertEquals(202, status);
//	      //assertThat(book.getAuthor().equals("cvam"));
//	     
//	   }
	   @Test
	   public void deleteIssuedBookList() throws Exception {
	      String uri = "/IssuedBookList/deleteIssuedBook/106";
	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      //Assert.assertTrue(status==200);
	      assertEquals(200, status);
	    
	   }
	   @Test
	   public void getIssuedBookList() throws Exception {
	      String uri = "/IssuedBookList/viewAllIssuedBookList";
	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      
	   }
	   @Test
	   public void getIssuedBookById() throws Exception {
	      String uri = "/IssuedBookList/viewIssuedBookById/52";
	      mvc = MockMvcBuilders.webAppContextSetup(context).build();
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      
	   }

}
