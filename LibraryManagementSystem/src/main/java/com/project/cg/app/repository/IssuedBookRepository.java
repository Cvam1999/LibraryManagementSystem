package com.project.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cg.app.entity.IssuedBook;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook,Integer> {
	//public IssuedBook findBycusIdIssuedBook;
}
