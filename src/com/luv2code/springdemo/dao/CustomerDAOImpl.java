package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get hibernate session
		Session session = factory.getCurrentSession();
		
		//create query & execute query
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		List<Customer> customers = query.getResultList();
		
		//return results of query
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		//get hibernate session
		Session session = factory.getCurrentSession();
		
		//save customer in DB
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		
		Session session = factory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}

}
