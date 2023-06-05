package com.project.cg.app.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import com.project.cg.app.entity.Customer;
import com.project.cg.app.repository.CustomerRepository;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	
		@InjectMocks
		private CustomerService customerService;
		
		@Mock
		private CustomerRepository customerRepository;
		
		
		@Test
		void testViewAllCustomers() {
			
			List<Customer> customers = getCustomersMockData();		
			Mockito.when(customerRepository.findAll()).thenReturn(customers);
			
			List<Customer> customerInfo = customerService.viewAllCustomer();
			
			assertThat(customerInfo.size() == customers.size());
			assertThat(customerInfo.get(0).getCusId()==1);
			assertThat(customerInfo.get(0).getCusName().equals("shivam"));
		}
		
		@Test
		void testGetCustomer() {
			
			int customerId = 10;
			
			Optional<Customer> customer = getCustomerMockData();
			
			Mockito.when(customerRepository.findById(customerId)).thenReturn(customer);
			
			Customer customer1 = customerService.viewCustomer(customerId, "Aakash@123");
			assertEquals("aakash", customer1.getCusName());
			assertThat(customer1.getCusName().equals("aakash"));
			assertThat(customer1.getCusEmail().equals("aakash@gmail.com"));
		}
		
		@Test
		void testAddCustomer() {
			
			Optional<Customer> customerOpt = getCustomerMockData();
			Customer customer =customerOpt.get();
			Mockito.when(customerRepository.save(customer)).thenReturn(customer);
			
			Customer cus = customerService.addCustomer(customer);
			assertThat(customer.getCusName().equals("aakash"));
			assertThat(cus.getCusName().equals(customer.getCusName()));
		}
		
		@Test
		void testUpdateCustomer() {
			
			int customerId = 10;
			Optional<Customer> customerOpt = getCustomerMockData();
			Customer customer =customerOpt.get();
			Mockito.when(customerRepository.findById(customerId)).thenReturn(customerOpt);
			Mockito.when(customerRepository.save(customer)).thenReturn(customer);
			
			Customer updatedCustomer = customerService.updateCustomer(customer, customerId);
			
			assertThat(customer.getCusId()==updatedCustomer.getCusId());
			assertThat(customer.getCusEmail()==updatedCustomer.getCusEmail());
			
		}
		
		@Test
		void testDeleteFlatBooking() {
			
			int customerId = 10;
			Optional<Customer> customerOpt = getCustomerMockData();
			Customer customer =customerOpt.get();
			Mockito.when(customerRepository.findById(customerId)).thenReturn(customerOpt);
			doNothing().when(customerRepository).delete(customer);
		
			customerService.deleteCustomer(customerId);
			
			
			assertThat(customer.getCusId()==10);
			
		}	
		
		
		private List<Customer> getCustomersMockData(){
			List<Customer> customers = new ArrayList<>();
				Customer c1=new Customer();
				c1.setCusId(1);
				c1.setCusName("shivam");
				c1.setAddress("up");
				c1.setPassword("Shivam@123");
				c1.setCusEmail("shivam@gmail.com");
				
				Customer c2=new Customer();
				c1.setCusId(2);
				c1.setCusName("pankaj");
				c1.setAddress("up");
				c1.setPassword("Pankaj@123");
				c1.setCusEmail("pankaj@gmail.com");
				
				Customer c3=new Customer();
				c1.setCusId(3);
				c1.setCusName("suraj");
				c1.setAddress("uk");
				c1.setPassword("Suraj@123");
				c1.setCusEmail("suraj@gmail.com");
				
				customers.add(c1);
				customers.add(c2);
				customers.add(c3);
			return customers;
		}
		
		private Optional<Customer> getCustomerMockData(){
			
			Customer customer = new Customer();
			customer.setCusId(10);
			customer.setCusName("aakash");
			customer.setAddress("up");
			customer.setCusEmail("aakash@gmail.com");
			customer.setPassword("Aakash@123");
			return Optional.of(customer);
		}
		
	
	}
	
	


