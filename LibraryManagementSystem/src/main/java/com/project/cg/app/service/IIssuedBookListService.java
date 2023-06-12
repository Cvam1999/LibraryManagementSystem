package com.project.cg.app.service;

import java.util.List;

import com.project.cg.app.entity.IssuedBookList;

public interface IIssuedBookListService {
	public IssuedBookList addIssuedBook(IssuedBookList book);
	public IssuedBookList updateIssuedBook(IssuedBookList book,int IssuedbookId);
	public void deleteIssuedBook(int IssuedbookId);
	public IssuedBookList viewIssuedBook(int IssuedbookId);
	public List<IssuedBookList> viewAllIssuedBook();
}
