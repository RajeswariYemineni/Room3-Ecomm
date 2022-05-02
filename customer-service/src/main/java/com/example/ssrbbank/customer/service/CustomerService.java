package com.example.ssrbbank.customer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssrbbank.customer.model.CustomerDto;
import com.example.ssrbbank.customer.model.CustomerReponseModel;
import com.example.ssrbbank.customer.model.entity.CustomerEntity;
import com.example.ssrbbank.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	ModelMapper mm = new ModelMapper();
		
	public CustomerDto createCustomer(CustomerDto custDetails) {
		
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		custDetails.setCustomerId(UUID.randomUUID().toString());
		custDetails.setRegDate(new Date(System.currentTimeMillis()));

		CustomerEntity entity = mm.map(custDetails, CustomerEntity.class);
		customerRepository.save(entity);				
		
		return custDetails;
	}

	public CustomerDto searchCustomerWithCustID(String custId) {
		
		CustomerEntity customer = customerRepository.findById(custId).get();
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CustomerDto result = mm.map(customer, CustomerDto.class);
		return result;
	}

	public CustomerDto searchCustomerWithEmail(String email) {
		CustomerEntity customer = customerRepository.findByEmail(email);
		
		CustomerDto result = mm.map(customer, CustomerDto.class);
		return result;
	}

	public CustomerDto editCustomer(CustomerDto custDetails) {	
		
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		custDetails.setRegDate(customerRepository.findById(custDetails.getCustomerId()).get().getRegDate());

		CustomerEntity entity = mm.map(custDetails, CustomerEntity.class);
		customerRepository.save(entity);				
		
		return custDetails;
	}


}
