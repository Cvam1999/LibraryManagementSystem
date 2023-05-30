package com.project.cg.app.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;

import com.project.cg.app.entity.Book;
import com.project.cg.app.entity.Customer;
import com.project.cg.app.entity.IssuedBook;
import com.project.cg.app.exception.BookCannotBeIssueException;
import com.project.cg.app.exception.BookNotFoundException;
import com.project.cg.app.exception.UserNotFoundException;
import com.project.cg.app.repository.BookRepository;
import com.project.cg.app.repository.CustomerRepository;
import com.project.cg.app.repository.IssuedBookRepository;
import com.project.cg.app.service.IIssuedBookService;



@Service
public class IssuedBookService implements IIssuedBookService {
	
	@Autowired
	private IssuedBookRepository repo;
	@Autowired
	private BookRepository repository;
	@Autowired
	private CustomerRepository reposi;
	@Override
	public IssuedBook addIssuedBook(IssuedBook book) {
		if (book.getBook().getBookId()==0 && book.getUser().getCusId()==0) {
			throw new BookNotFoundException("Book details cannot be blank");
		}
		int id=book.getBook().getBookId();
		int cusid=book.getUser().getCusId();
		int count=0;
		Book value=repository.findById(id).orElseThrow((()-> new BookNotFoundException("Please enter correct bookId")));
		Customer c=reposi.findById(cusid).orElseThrow(()->new UserNotFoundException("Please enter correct cusId"));
		validateIssueDate(book);
		validateDueDate(book);
		List<IssuedBook> books=repo.findAll();
		if(value.getIssuedBook()<value.getQuantity()) {
		value.setIssuedBook((value.getIssuedBook())+1);
		}
		else {
			throw new BookCannotBeIssueException("this book is out of stock");
		}
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getBook().getBookId()==id &&
					books.get(i).getUser().getCusId()==cusid) {
			 throw new BookCannotBeIssueException("same book cannot be issue again to same user");
				
			}
			if(books.get(i).getUser().getCusId()==cusid) {
				count++;
			if(count>4) {
				throw new BookCannotBeIssueException("one user cannot have more than 5 books");
			}
			}
		
		}
	
		 repository.save(value);
		return repo.save(book);
	}

	@Override
	public IssuedBook updateIssuedBook(IssuedBook book, int IssuedbookId) {
		IssuedBook value=repo.findById(IssuedbookId).orElseThrow(()-> new BookCannotBeIssueException("this issuedBookId not found in database"));
		value.setBook(book.getBook());
		value.setUser(book.getUser());
		value.setIssueDate(book.getIssueDate());
		value.setDueDate(book.getDueDate());
		return repo.save(value);
	}

	@Override
	public void deleteIssuedBook(int IssuedbookId) {
		IssuedBook val=repo.findById(IssuedbookId).orElseThrow(()->new BookNotFoundException("book details not found with id:"+IssuedbookId ));
		Book value=repository.findById(val.getBook().getBookId()).orElseThrow();
		if(value.getIssuedBook()>0) {
		value.setIssuedBook((value.getIssuedBook())-1);
		}
		repository.save(value);
		repo.delete(val);
	}

	@Override
	public IssuedBook viewIssuedBook(int IssuedbookId) {
		return repo.findById(IssuedbookId).orElseThrow(()->new BookNotFoundException("book details not found with id:"+IssuedbookId ));
	}

	@Override
	public List<IssuedBook> viewAllIssuedBook() {
		List<IssuedBook> books=repo.findAll();
		return books;
	}
//	public static boolean validateIssueBook(IssuedBook book)
//			throws BookNotFoundException, UserNotFoundException {
//		
//		boolean flag = false;
//		if (book.getBook().getBookId()==0 && book.getUser().getCusId()==0) {
//			throw new BookNotFoundException("Book details cannot be blank");
//		} else {
//			
//			validateIssueDate(book);
//			validateDueDate(book);
//			flag = true;
//		}
//		return flag;
//
//	}

	public static boolean validateIssueDate(IssuedBook book) throws BookNotFoundException {
		LocalDate date=LocalDate.now(); 
		boolean flag = false;
		if (book.getIssueDate() == null) {
			
			throw new BookNotFoundException("Issue_Date cannot be empty");
		} else if (!book.getIssueDate().isEqual(date)) {
			
			throw new BookNotFoundException("Issue_Date should be Today_Date");
		} else {
			flag = true;
			
		}
		return flag;
	}

	public static boolean validateDueDate(IssuedBook book) throws BookNotFoundException {
		
		boolean flag = false;
		if (book.getDueDate() == null) {
			throw new BookNotFoundException("Due_Date cannot be empty");
		} else if (book.getDueDate().isBefore(book.getIssueDate())) {
			throw new BookNotFoundException("Due_Date cannot be before Issue_From_Date");
		}
		 else if (book.getDueDate().isEqual(LocalDate.now())) {
				throw new BookNotFoundException("Due_Date cannot be Today_Date");
			} else {
			flag = true;
		}
		return flag;
	}


}
