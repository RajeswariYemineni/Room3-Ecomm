package com.example.ssrbbank.customer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssrbbank.customer.model.CustomerDto;
import com.example.ssrbbank.customer.model.CustomerEditRequestModel;
import com.example.ssrbbank.customer.model.CustomerReponseModel;
import com.example.ssrbbank.customer.model.CustomerRequestModel;
import com.example.ssrbbank.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
		
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
			)
	public ResponseEntity<CustomerReponseModel> createCustomer(@Valid @RequestBody CustomerRequestModel crm) {
		ModelMapper mm = new ModelMapper();
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		CustomerDto customerDto = mm.map(crm, CustomerDto.class);
		CustomerDto createdCustomer = customerService.createCustomer(customerDto);
		
		CustomerReponseModel returnValue = mm.map(createdCustomer, CustomerReponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@GetMapping("/search")
	public @ResponseBody ResponseEntity<CustomerReponseModel> searchCustomer(@RequestParam(name = "customerId", required = false) String custId, @RequestParam(name = "email", required = false) String email) {
		CustomerDto customer = null;	
		ModelMapper mm = new ModelMapper();
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		if(custId != null && email == null) {
			customer = customerService.searchCustomerWithCustID(custId);
			CustomerReponseModel returnValue = mm.map(customer, CustomerReponseModel.class);
			return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);
		}else if(custId == null && email != null) {
			customer = customerService.searchCustomerWithEmail(email);
			CustomerReponseModel returnValue = mm.map(customer, CustomerReponseModel.class);
			return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);	
		}else if(custId != null && email != null) {
			customer = customerService.searchCustomerWithCustID(custId);
			if(customer.getEmail().equals(email)) {
				CustomerReponseModel returnValue = mm.map(customer, CustomerReponseModel.class);
				return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);	
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
			)
	public @ResponseBody ResponseEntity<CustomerReponseModel> editCustomer(@Valid @RequestBody CustomerEditRequestModel crm) {
		ModelMapper mm = new ModelMapper();
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);		
		
		CustomerDto customerDto = mm.map(crm, CustomerDto.class);
		CustomerDto editCustomer = customerService.editCustomer(customerDto);
		
		CustomerReponseModel returnValue = mm.map(editCustomer, CustomerReponseModel.class);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
		
		
	}
}
