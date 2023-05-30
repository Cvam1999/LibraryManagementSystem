package com.project.cg.app.service;

import java.util.List;

import com.project.cg.app.entity.IssuedBook;

public interface IIssuedBookService {
	public IssuedBook addIssuedBook(IssuedBook book);
	public IssuedBook updateIssuedBook(IssuedBook book,int IssuedbookId);
	public void deleteIssuedBook(int IssuedbookId);
	public IssuedBook viewIssuedBook(int IssuedbookId);
	public List<IssuedBook> viewAllIssuedBook();
}
