package com.project.cg.app.service;

import java.util.List;

import com.project.cg.app.entity.Customer;

public interface ICustomerService {
	public Customer addCustomer(Customer cust);
	public Customer updateCustomer(Customer cust,int cusId);
	public void deleteCustomer(int cusId);
	public Customer viewCustomer(int cusId,String password);
	public List<Customer> viewAllCustomer();
}
