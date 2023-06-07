package com.project.cg.app.service.implementation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cg.app.entity.Customer;
import com.project.cg.app.exception.CustomResponseException;
import com.project.cg.app.exception.UserNotFoundException;
import com.project.cg.app.repository.CustomerRepository;
import com.project.cg.app.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	final static Logger logger=LogManager.getLogger(CustomerService.class);
	@Autowired
	private CustomerRepository repo;
	
	
	public static String doHashing(String password) {
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
	
	    /*
		 * MethodName  : addCustomer
		 * Description : Method will add customer into database
		 * Input Arguments : Customer Object 
		 * Return Value : Customer Object 
		 */
	 
	 @Override
	public Customer addCustomer(Customer cust) {
		logger.info("Business method addCustomer initiated");
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
		logger.info("Business method addCustomer executed");
		return result;
	}
	 
	    /*
		 * MethodName  : updateCustomer
		 * Description : Method will update existing customer details
		 * Input Arguments : Customer Object, Customer Id(Primary key) Integer 
		 * Return Value : Customer Object 
		 * Exception : UserNotFoundException
		 */

	@Override
	public Customer updateCustomer(Customer cust, int cusId) throws UserNotFoundException {
		logger.info("Business method updateCustomer initiated");
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
		logger.info("Business method updateCustomer executed");
		return repo.save(value);
	}
	
	/*
	 * MethodName  : deleteCustomer
	 * Description : Method will delete existing customer details
	 * Input Arguments : Customer Id(Primary key) Integer 
	 * Return Value :  void 
	 * Exception : UserNotFoundException
	 */

	@Override
	public void deleteCustomer(int cusId)throws UserNotFoundException {
		logger.info("Business method deleteCustomer initiated");
		Customer value=repo.findById(cusId).orElseThrow(()->new UserNotFoundException("user not found with customer id:"+cusId));
		repo.delete(value);
	}

	/*
	 * MethodName  : viewCustomer
	 * Description : Method will show existing customer details
	 * Input Arguments : Customer Id(Primary key) Integer , Password String
	 * Return Value : Customer Object 
	 * Exception : UserNotFoundException
	 */
	
	public Customer viewCustomer(int cusId,String password) {
		logger.info("Business method viewCustomer initiated");
		validateUser(cusId,password);
		logger.info("Business method viewCustomer executed");
		return repo.findById(cusId).orElseThrow();
	}
	
	/*
	 * MethodName  : viewAllCustomer
	 * Description : Method will show all existing customers details
	 * Input Arguments : None 
	 * Return Value : List<Customer> 
	 */

	@Override
	public List<Customer> viewAllCustomer() {
		logger.info("Business method viewAllCustomer initiated");
		List<Customer> allCustomers=repo.findAll();
		logger.info("Business method viewAllCustomer executed");
		return allCustomers;
	}
	
	/*
	 ***************************** Validation part*****************************
	 */
	
	
	/*************************************************************************************
	 * Method:                          	validateUser
     *Description:                      	To check the user/customer existing into database or not.
	     *@parameter:                  .    customer Id Integer, password String
		 *@returns boolean               - 	it will match password is correct or not with each id.
		 *@throws UserNotFoundException & CustomResponseException -  It is raised due to mismatch of user details.                          	 
	 *************************************************************************************/
	
	
	
	public boolean validateUser(Integer cusId, String password) throws UserNotFoundException, CustomResponseException{
		logger.info("validateUser() is initiated");
		boolean flag = false;

		Customer user = repo.findById(cusId).orElseThrow(()->new UserNotFoundException("user not found with customer id:"+cusId));
		String matchPassword= doHashing(password);
		 if (matchPassword.equals(user.getPassword())) {
			flag = true;
		 logger.info("validationSuccessful");
		 }
		else {
			logger.error("Please check password");
			throw new CustomResponseException("Please check password");
		}
		 logger.info("validateUser() has executed");
		return flag;
	}
	
	/*************************************************************************************
	 * Method:                          	checkValidUser
     *Description:                      	To check the user/customer existing into database or not by emailId.
	     *@parameter:                  .    email String
		 *@returns boolean               - 	it will match email id is in database or not.
		 *@throws CustomResponseException -  It will raise if email already exists in database.                          	 
	 *************************************************************************************/
	
	public boolean checkValidUser(String email) {
		logger.info("checkValidUser() is initiated");
		boolean flag = false;
		Customer customer=repo.findByCusEmail(email);
		
			if(customer!=null && email.equals(customer.getCusEmail()) ) {
				logger.error("this email is already in database");
				throw new CustomResponseException("this email is already in database ");
			}
			else {
				flag=true;
				logger.info("validationSuccessful");
			}
				
		logger.info("checkValidUser() has executed");
		return flag;
	/*	
	 * Another Method to checkValidUser
	    boolean flag = false;
		List<Customer> customerList=repo.findAll();
		for(int i=0;i<customerList.size();i++) {
			if(!(customerList.get(i).getCusEmail().equals(email))) {
				flag=true;
			}
			else {
				throw new UserNotFoundException("this email is already in database ");
			}
				
		}
		return flag;
	*/
	}
	
	/*************************************************************************************
	 * Method:                          	validateUsername
     *Description:                      	To check the user/customer name format.
	     *@parameter:                  .    customerName String
		 *@returns boolean               - 	it will check userName format is valid or not.
		 *@throws CustomResponseException -  It will raise if userName format mismatch.                          	 
	 *************************************************************************************/
	
	public static boolean validateUsername(String cusName) throws CustomResponseException{  		
		logger.info("validateUsername() is initiated");
		boolean flag = false;
		if(cusName.isEmpty()) {
			logger.error("User Name cannot be empty");
			throw new CustomResponseException("User Name cannot be empty");
			}
		else if(!cusName.matches("^[a-zA-Z]+$")) {
			logger.error("use proper name format");
			throw new CustomResponseException(usernameformat);
			}
		else if(cusName.length()<3 || cusName.length()>30) {
			logger.error("User Name length must be in range 3 to 30");
			throw new CustomResponseException("User Name length must be in range 3 to 30");
		}
		else {
			flag = true;
			logger.info("validationSuccessful");
		}
		logger.info("validateUsername() has executed");
		return flag;
    }
	

	/*************************************************************************************
	 * Method:                          	validateAddress
     *Description:                      	To check the user/customer address is empty or not.
	     *@parameter:                  .    address String
		 *@returns boolean               - 	it will check address is empty or not.
		 *@throws CustomResponseException -  It will raise if address is empty.                          	 
	 *************************************************************************************/
	
	public static boolean validateAddress(String address) throws CustomResponseException{  		
		logger.info("validateAddress() is initiated");
		boolean flag = false;
		if(address.isEmpty()) {
			logger.error("Address cannot be empty");
			throw new CustomResponseException("Address cannot be empty");
			}
		else {
			flag = true;
			logger.info("validationSuccessful");
		}
		logger.info("validateAddress() has executed");
		return flag;
    }
	
	/*************************************************************************************
	 * Method:                          	validateEmail
     *Description:                      	To check the user/customer email format.
	     *@parameter:                  .    email String
		 *@returns boolean               - 	it will check email format is correct or not.
		 *@throws CustomResponseException -  It will raise if email format mismatch.                          	 
	 *************************************************************************************/
	
	public static boolean validateEmail(String email) throws CustomResponseException{  		
		logger.info("validateEmail() is initiated");
		boolean flag = false;
		if(email.isEmpty()) {
			logger.error("email cannot be empty");
			throw new CustomResponseException("email cannot be empty");
			}
		else if(!email.matches("[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")) {
			logger.error("use proper email format");
			throw new CustomResponseException("use proper email format");
			}
		else {
			flag = true;
			logger.info("validationSuccessful");
		}
		logger.info("validateEmail() has executed");
		return flag;
    }
	
	/*************************************************************************************
	 * Method:                          	validatePassword
     *Description:                      	To check the user/customer password format.
	     *@parameter:                  .    password String
		 *@returns boolean               - 	it will check password format is correct or not.
		 *@throws CustomResponseException -  It will raise if password format mismatch.                          	 
	 *************************************************************************************/
	
	public static boolean validatePassword(String password) throws CustomResponseException
    {  
		logger.info("validatePassword() is initiated");
		boolean flag = false;
		if(password.isEmpty()) {
			logger.error("Password cannot be empty");
			throw new CustomResponseException("Password cannot be empty");
		}
		else if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$")) {
			logger.error("use proper password format");
			throw new CustomResponseException(passformat);
		}
		else {
			flag = true;
			logger.info("validationSuccessful");
		}
		logger.info("validatePassword() has executed");
		return flag;
    }
	/*************************************************************************************
	 * Method:                          	validateContacNo
     *Description:                      	To check the user/customer contactNo format.
	     *@parameter:                  .    contactNo String
		 *@returns boolean               - 	it will check contactNo format is valid or not.
		 *@throws CustomResponseException -  It will raise if contactNo format mismatch. 
		 *
		 *                          This will support the following formats:

		8880344456
		+918880344456
		+91 8880344456
		+91-8880344456
		08880344456
		918880344456	 
	 *************************************************************************************/
	
	public static boolean validateContacNo(String number) throws CustomResponseException
    {  
		logger.info("validateContacNo() is initiated");
		boolean flag = false;
		if(number.isEmpty()) {
			logger.error("Contact Number cannot be empty");
			throw new CustomResponseException("Contact Number cannot be empty");
		}
		else if(!number.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$")){
			logger.error("enter correct  mobile number");
			throw new CustomResponseException("enter correct  mobile number");
		}
		else {
			flag = true;
			logger.info("validationSuccessful");
		}
		logger.info("validateContacNo() has executed");
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
