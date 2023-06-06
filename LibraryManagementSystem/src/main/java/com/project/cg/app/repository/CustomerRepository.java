package com.project.cg.app.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cg.app.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	

	public Customer findByCusEmail(String email);
}

