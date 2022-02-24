package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//inject dao
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("list")
	public String listCustomers(Model model) {
		
		//get stuff from service
		List<Customer> customers = customerService.getCustomers();
		
		//add customers to model
		//first arg is name in model, second arg is the value we're passing in
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		//create model attribute to bind form data
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		
		//get customer from service
		Customer customer = customerService.getCustomer(id);
		
		//set customer as model attribute to pre-populate form
		model.addAttribute("customer", customer);
		
		//send over form
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		//save customer using our service
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
}
