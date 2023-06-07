package com.project.cg.app.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.cg.app.entity.Book;
import com.project.cg.app.entity.IssuedBook;
import com.project.cg.app.exception.BookCannotBeIssueException;
import com.project.cg.app.exception.BookNotFoundException;
import com.project.cg.app.exception.CustomResponseException;
import com.project.cg.app.exception.UserNotFoundException;
import com.project.cg.app.repository.BookRepository;
import com.project.cg.app.repository.CustomerRepository;
import com.project.cg.app.repository.IssuedBookRepository;
import com.project.cg.app.service.IIssuedBookService;



@Service
public class IssuedBookService implements IIssuedBookService {
	final static Logger logger=LogManager.getLogger(IssuedBookService.class);
	@Autowired
	private IssuedBookRepository repo;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	 /*
	 * MethodName  : addIssuedBook
	 * Description : Method will add entry in database of which user/customer has issued which book
	 * Input Arguments : IssuedBook Object 
	 * Return Value : IssuedBook Object 
	 * Exception :BookNotFoundException, UserNotFoundException, BookCannotBeIssueException
	 */
	
	@Override
	public IssuedBook addIssuedBook(IssuedBook book) {
		logger.info("Business method addIssuedBook initiated");
		if (book.getBook().getBookId()==0 && book.getUser().getCusId()==0) {
			logger.error("Booking details cannot be blank");
			throw new BookNotFoundException("Booking details cannot be blank");
		}
		int id=book.getBook().getBookId();
		int cusid=book.getUser().getCusId();
		int count=0;
		Book value=bookRepository.findById(id).orElseThrow((()-> new BookNotFoundException("Please enter correct bookId")));
		customerRepository.findById(cusid).orElseThrow(()->new UserNotFoundException("Please enter correct cusId"));
		validateIssueDate(book);
		validateDueDate(book);
		List<IssuedBook> books=repo.findAll();
		if(value.getIssuedBook()<value.getQuantity()) {
		value.setIssuedBook((value.getIssuedBook())+1);
		}
		else {
			logger.error("this book is out of stock");
			throw new BookCannotBeIssueException("this book is out of stock");
		}
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getBook().getBookId()==id &&
					books.get(i).getUser().getCusId()==cusid) {
				logger.error("same book cannot be issue again to same user");
				throw new BookCannotBeIssueException("same book cannot be issue again to same user");
				
			}
			if(books.get(i).getUser().getCusId()==cusid) {
				count++;
			if(count>4) {
				logger.error("one user cannot have more than 5 books");
				throw new BookCannotBeIssueException("one user cannot have more than 5 books");
			}
			}
		
		}
	
		 bookRepository.save(value);
		 logger.info("Business method addIssuedBook executed");
		return repo.save(book);
	}

	 /*
	 * MethodName  : updateIssuedBook
	 * Description : Method will update existing issued book details
	 * Input Arguments : IssuedBook Object, issuedBook Id(Primary key) Integer 
	 * Return Value : IssuedBook Object 
	 * Exception : CustomResponseException
	 */
	
	@Override
	public IssuedBook updateIssuedBook(IssuedBook book, int issuedBookId)throws CustomResponseException {
		logger.info("Business method updateIssuedBook initiated");
		IssuedBook value=repo.findById(issuedBookId).orElseThrow(()-> new CustomResponseException("this issuedBookId not found in database"));
		value.setBook(book.getBook());
		value.setUser(book.getUser());
		value.setIssueDate(book.getIssueDate());
		value.setDueDate(book.getDueDate());
		logger.info("Business method updateIssuedBook executed");
		return repo.save(value);
	}
	
	 /*
	 * MethodName  : deleteIssuedBook
	 * Description : Method will update existing customer details
	 * Input Arguments : issuedBook Id(Primary key) Integer 
	 * Return Value : void
	 * Exception : BookNotFoundException
	 */

	@Override
	public void deleteIssuedBook(int issuedBookId) {
		logger.info("Business method deleteIssuedBook initiated");
		IssuedBook val=repo.findById(issuedBookId).orElseThrow(()->new BookNotFoundException("book details not found with id:"+issuedBookId ));
		Book value=bookRepository.findById(val.getBook().getBookId()).orElseThrow();
		if(value.getIssuedBook()>0) {
		value.setIssuedBook((value.getIssuedBook())-1);
		}
		bookRepository.save(value);
		repo.delete(val);
		logger.info("Business method deleteIssuedBook executed");
	}
	
	 /*
	 * MethodName  : viewIssuedBook
	 * Description : Method will show issuedBook details
	 * Input Arguments : issuedBook Id(Primary key) Integer 
	 * Return Value : IssuedBook Object 
	 * Exception : BookNotFoundException
	 */

	@Override
	public IssuedBook viewIssuedBook(int issuedBookId) {
		logger.info("Business method viewIssuedBook initiated");
		return repo.findById(issuedBookId).orElseThrow(()->new BookNotFoundException("book details not found with id:"+issuedBookId ));
	}

	 /*
	 * MethodName  : viewAllIssuedBook
	 * Description : Method will show all issuedBooks details
	 * Input Arguments : None 
	 * Return Value : List<IssuedBook>  
	 */
	
	@Override
	public List<IssuedBook> viewAllIssuedBook() {
		logger.info("Business method viewAllIssuedBook initiated");
		List<IssuedBook> books=repo.findAll();
		logger.info("Business method viewAllIssuedBook executed");
		return books;
	}
	
	/*************************************************************************************
	 * Method:                          	validateIssueDate
     *Description:                      	To check the book issued Date.
	     *@parameter:                  .    IssuedBook Object
		 *@returns boolean               - 	it will check date is correct or not.
		 *@throws CustomResponseException -  It will raise in case of mismatch of date.                          	 
	 *************************************************************************************/

	public static boolean validateIssueDate(IssuedBook issuedBook) throws CustomResponseException {
		logger.info("validateIssueDate() is initiated");
		LocalDate date=LocalDate.now(); 
		boolean flag = false;
		if (issuedBook.getIssueDate() == null) {
			logger.error("Issue_Date cannot be empty");
			throw new CustomResponseException("Issue_Date cannot be empty");
		} else if (!issuedBook.getIssueDate().isEqual(date)) {
			logger.error("Issue_Date should be Today_Date");
			throw new CustomResponseException("Issue_Date should be Today_Date");
		} else {
			flag = true;
			logger.info("validationSuccessful");
		}
		logger.info("validateIssueDate() has executed");
		return flag;
	}
	
	/*************************************************************************************
	 * Method:                          	validateDueDate
     *Description:                      	To check the book Due Date.
	     *@parameter:                  .    IssuedBook Object
		 *@returns boolean               - 	it will check date is correct or not.
		 *@throws CustomResponseException -  It will raise in case of mismatch of date.                          	 
	 *************************************************************************************/

	public static boolean validateDueDate(IssuedBook issuedBook) throws CustomResponseException {
		logger.info("validateDueDate() is initiated");
		boolean flag = false;
		if (issuedBook.getDueDate() == null) {
			logger.error("Due_Date cannot be empty");
			throw new CustomResponseException("Due_Date cannot be empty");
		} else if (issuedBook.getDueDate().isBefore(issuedBook.getIssueDate())) {
			logger.error("Due_Date cannot be before Issue_From_Date");
			throw new CustomResponseException("Due_Date cannot be before Issue_From_Date");
		}
		 else if (issuedBook.getDueDate().isEqual(LocalDate.now())) {
			 logger.error("Due_Date cannot be Today_Date");
			 throw new CustomResponseException("Due_Date cannot be Today_Date");
			} else {
			flag = true;
			logger.info("validationSuccessful");
		}
		logger.info("validateDueDate() has executed");
		return flag;
	}


}
