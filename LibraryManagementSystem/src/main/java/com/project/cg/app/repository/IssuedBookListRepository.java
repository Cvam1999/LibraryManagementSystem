package com.project.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cg.app.entity.IssuedBookList;

@Repository
public interface IssuedBookListRepository extends JpaRepository<IssuedBookList,Integer> {
	//public IssuedBook findBycusIdIssuedBook;
}
