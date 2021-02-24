package com.br.internet.qualiti.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.internet.qualiti.bank.model.Customer;
import com.br.internet.qualiti.bank.util.HibernateUtil;

public class CustomerDao {
	
	private static CustomerDao instance;
	
	private CustomerDao() {}
	
	public static CustomerDao getInstance()
	{
		if(instance == null)
		{
			instance = new CustomerDao();
		}
		return instance;
	}
	
	public void save(Customer customer) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(customer);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void update(Customer customer) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(customer);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void delete(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a customer object
			Customer customer = session.get(Customer.class, id);
			if (customer != null) {
				session.delete(customer);
				System.out.println("Cliente deletado.");
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Customer get(int id) {

		Transaction transaction = null;
		Customer customer = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an customer object
			customer = session.get(Customer.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return customer;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> get() {

		Transaction transaction = null;
		List<Customer> customers = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an customer object
			
			customers = session.createQuery("from Customer").getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return customers;
	}
}
