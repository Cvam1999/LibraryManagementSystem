package com.project.cg.app.service.implementation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cg.app.entity.Customer;
import com.project.cg.app.exception.CustomResponseException;
import com.project.cg.app.exception.UserNotFoundException;
import com.project.cg.app.repository.CustomerRepository;
import com.project.cg.app.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	@Autowired
	private CustomerRepository repo;
	 public static String doHashing (String password) {
		  try {
		   MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		   //byte[] salt=createSalt();
		   messageDigest.update(password.getBytes());
		   
		   //byte[] resultByteArray = messageDigest.digest(password.getBytes());
		   byte[] resultByteArray = messageDigest.digest();
		   StringBuilder sb = new StringBuilder();
		   
		   for (byte b : resultByteArray) {
		    sb.append(String.format("%02x", b));
		   }
		   
		   return sb.toString();
		   
		  } catch (NoSuchAlgorithmException e) {
		   e.printStackTrace();
		  }
		  
		  return "";
		 }
	 public static byte[] createSalt() {
		 byte[] bytes=new byte[20];
		 SecureRandom random=new SecureRandom();
		 random.nextBytes(bytes);
		 return bytes;
	 }
	
	@Override
	public Customer addCustomer(Customer cust) {
		String name=cust.getCusName();
		String password=cust.getPassword();
		String email=cust.getCusEmail();
		String contactNo=cust.getContactNo();
		String address=cust.getAddress();
		
		String hashPassword=doHashing(password);
		cust.setPassword(hashPassword);
		validateUsername(name);
		validateEmail(email);
		validatePassword(password);
		validateAddress(address);
		validateContacNo(contactNo);
		checkValidUser(email);
		
		Customer result=repo.save(cust);
		return result;
	}

	@Override
	public Customer updateCustomer(Customer cust, int cusId) {
		String name=cust.getCusName();
		String password=cust.getPassword();
		String email=cust.getCusEmail();
		String contactNo=cust.getContactNo();
		String address=cust.getAddress();
		
		String updateHashPassword= doHashing(password);
		cust.setPassword(updateHashPassword);
		validateUsername(name);
		validateEmail(email);
		validatePassword(password);
		validateAddress(address);
		validateContacNo(contactNo);
		
		Customer value=repo.findById(cusId).orElseThrow(()-> new UserNotFoundException("this cusId not found in database"));
		value.setCusName(cust.getCusName());
		value.setCusEmail(cust.getCusEmail());
		value.setPassword(cust.getPassword());
		value.setAddress(cust.getAddress());
		value.setContactNo(cust.getContactNo());
		return repo.save(value);
	}

	@Override
	public void deleteCustomer(int cusId) {
		Customer value=repo.findById(cusId).orElseThrow(()->new UserNotFoundException("user not found with customer id:"+cusId));
		repo.delete(value);
		
	}

	
	public Customer viewCustomer(int cusId,String password) {
		validateUser(cusId,password);
		return repo.findById(cusId).orElseThrow();
	}

	@Override
	public List<Customer> viewAllCustomer() {
		List<Customer> allCustomers=repo.findAll();
		return allCustomers;
	}
	
	public boolean validateUser(Integer cusId, String password){
		
		boolean flag = false;

		Customer user = repo.findById(cusId).orElseThrow(()->new UserNotFoundException("user not found with customer id:"+cusId));
		String matchPassword= doHashing(password);
		 if (matchPassword.equals(user.getPassword()))
			flag = true;
		else
			throw new CustomResponseException("Please check password");
		return flag;
	}
	public boolean checkValidUser(String email) {
		boolean flag = false;
		Customer customer=repo.findByCusEmail(email);
		
			if(customer!=null && email.equals(customer.getCusEmail()) ) {
				throw new CustomResponseException("this email is already in database ");
			}
			else {
				flag=true;
				
			}
				
		
		return flag;
//		boolean flag = false;
//		List<Customer> customerList=repo.findAll();
//		for(int i=0;i<customerList.size();i++) {
//			if(!(customerList.get(i).getCusEmail().equals(email))) {
//				flag=true;
//			}
//			else {
//				throw new UserNotFoundException("this email is already in database ");
//			}
//				
//		}
//		return flag;
	}
	public static boolean validateUsername(String cusName) throws CustomResponseException{  		
		
		boolean flag = false;
		if(cusName.isEmpty()) {
			throw new CustomResponseException("User Name cannot be empty");
			}
		else if(!cusName.matches("^[a-zA-Z]+$")) {
			throw new CustomResponseException(usernameformat);
			}
		else if(cusName.length()<3 || cusName.length()>30) {
			throw new CustomResponseException("User Name length must be in range 3 to 30");
		}
		else {
			flag = true;
		}
		return flag;
    }
	public static boolean validateAddress(String address) throws CustomResponseException{  		
		
		boolean flag = false;
		if(address.isEmpty()) {
			throw new CustomResponseException("Address cannot be empty");
			}
		else {
			flag = true;
		}
		return flag;
    }
	public static boolean validateEmail(String email) throws CustomResponseException{  		
		
		boolean flag = false;
		if(email.isEmpty()) {
			throw new CustomResponseException("email cannot be empty");
			}
		else if(!email.matches("[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")) {
			throw new CustomResponseException("use proper email format");
			}
		else {
			flag = true;
		}
		return flag;
    }
	public static boolean validatePassword(String password) throws CustomResponseException
    {  
		boolean flag = false;
		if(password.isEmpty()) {
			throw new CustomResponseException("Password cannot be empty");
		}
		else if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$")) {
			throw new CustomResponseException(passformat);
		}
		else {
			flag = true;
		}
		return flag;
    }
//	This will support the following formats:
//
//		8880344456
//		+918880344456
//		+91 8880344456
//		+91-8880344456
//		08880344456
//		918880344456
	public static boolean validateContacNo(String number) throws CustomResponseException
    {  
		boolean flag = false;
		if(number.isEmpty()) {
			throw new CustomResponseException("Contact Number cannot be empty");
		}
		else if(!number.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$")){
			throw new CustomResponseException("enter correct  mobile number");
		}
		else {
			flag = true;
		}
		return flag;
    }
	
	static String usernameformat ="Format For UserName is Wrong\r\n "
			+ "\r\n "
			+ " Please Enter Again :\r\n"
			+ "____________________________________________________________\r\n"
			+ "\r\n"
			+ "Valid Format for UserName:\r\n"
			+ "\r\n"
			+ "The first character of the username must be an alphabetic character, i.e., either lowercase character\r\n"
			+ "[a � z] or uppercase character [A � Z].\r\n"
			+ "User Name length should be in range 3 to 30."
			+ "\r\n";
	static String passformat ="Format for password is Wrong\r\n"
			+ "\r\n"
			+ "Please Enter Password Again\r\n"
			+ "\r\n"
			+ "Password cannot be empty\\r\\n"
			+ "Password must contain at least one digit [0-9].\r\n"
			+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
			+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
			+ "Password must contain at least one special character like ! $ @ % ^ # & + = ( ).\r\n"
			+ "Password must contain a length of at least 8 characters and a maximum of 20 characters."
			+ ""  ;
	

}
