package com.example.ssrbbank.customer.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.ssrbbank.customer.model.entity.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {
	CustomerEntity findByEmail(String email);
}
