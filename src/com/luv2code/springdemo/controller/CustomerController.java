package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//inject dao
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("list")
	public String listCustomers(Model model) {
		
		//get stuff from DAO
		List<Customer> customers = customerDAO.getCustomers();
		
		//add customers to model
		//first arg is name in model, second arg is the value we're passing in
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
}
