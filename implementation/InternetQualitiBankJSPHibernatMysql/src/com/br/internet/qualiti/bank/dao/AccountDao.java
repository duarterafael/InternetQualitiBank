package com.br.internet.qualiti.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.internet.qualiti.bank.model.Account;
import com.br.internet.qualiti.bank.model.Customer;
import com.br.internet.qualiti.bank.util.HibernateUtil;


public class AccountDao {
	
	
	@SuppressWarnings("unchecked")
	public List<Account> getByCustomerId(int customerId) {

		Transaction transaction = null;
		List<Account> accounts = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			accounts = session.createQuery("FROM Account WHERE customer_id = :customer_id")
			.setParameter("customer_id", customerId)		
			.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return accounts;
	}
	
	public void save(Account account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(account);
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
			transaction = session.beginTransaction();
			Account account = session.get(Account.class, id);
			if (account != null) {
				session.delete(account);
				System.out.println("Conta deletada deletado.");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public Account get(int id) {

		Transaction transaction = null;
		Account account = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			account = session.get(Account.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return account;
	}
	
	public void update(Account account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(account);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

}
