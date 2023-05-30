package com.project.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cg.app.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
