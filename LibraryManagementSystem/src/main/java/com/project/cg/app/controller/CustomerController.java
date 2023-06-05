package com.project.cg.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cg.app.entity.Customer;
import com.project.cg.app.service.implementation.CustomerService;

@RestController
@RequestMapping("/Customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping(value="/addCustomer")
	public ResponseEntity<String> addCustomer(@RequestBody Customer user){
		Customer value=service.addCustomer(user);
		ResponseEntity<String> retValue=new ResponseEntity<>("Customer added with user Id "+ value.getCusId(),HttpStatus.CREATED);
		return retValue;
	}
	@PutMapping(value="/updateCustomer/{id}")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer user,@PathVariable Integer id){
		Customer value=service.updateCustomer(user,id);
		ResponseEntity<String> retValue=new ResponseEntity<>("Customer updated with user Id "+ value.getCusId(),HttpStatus.ACCEPTED);
		return retValue;
	}
	@DeleteMapping(value="/deleteCustomer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer id){
		service.deleteCustomer(id);
		ResponseEntity<String> retValue=new ResponseEntity<>("Customer deleted with user Id "+ id,HttpStatus.OK);
		return retValue;
	}
	@GetMapping(value="/viewCustomerById/{id}/{password}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable Integer id,@PathVariable String password){
		Customer value=service.viewCustomer(id,password);
		ResponseEntity<Customer> retValue=new ResponseEntity<>(value,HttpStatus.OK);
		return retValue;
	}
	@GetMapping(value="/viewAllCustomer")
	public ResponseEntity<List<Customer>> viewAllCustomer(){
		List<Customer> list=service.viewAllCustomer();
		ResponseEntity<List<Customer>> retValue=new ResponseEntity<>(list,HttpStatus.OK);
		return retValue;
	}
}

