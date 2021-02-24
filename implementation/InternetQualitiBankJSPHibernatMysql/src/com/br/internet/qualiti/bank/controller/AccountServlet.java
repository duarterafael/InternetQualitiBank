package com.br.internet.qualiti.bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.internet.qualiti.bank.dao.AccountDao;
import com.br.internet.qualiti.bank.dao.CustomerDao;
import com.br.internet.qualiti.bank.model.Account;
import com.br.internet.qualiti.bank.model.Customer;
import com.br.internet.qualiti.bank.util.Constants;

@WebServlet("/account/*")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDao accountDao;
	private CustomerDao customerDao;
	
	public void init() {
		customerDao = CustomerDao.getInstance();
		accountDao = AccountDao.getInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter(Constants.ACTION_KEY);
		
		try {
			if(action == null || action.isEmpty()) {
				list(request, response);
			} else if (action.equalsIgnoreCase(Constants.NEW_ACTION)) {
				showNewForm(request, response);
			} else if (action.equalsIgnoreCase(Constants.INSERT_ACTION)) {
				insert(request, response);
			} else if (action.equalsIgnoreCase(Constants.DELETE_ACTION)) {
				delete(request, response);
			} else if (action.equalsIgnoreCase(Constants.EDIT_ACTION)) {
				showEditForm(request, response);
			} else if (action.equalsIgnoreCase(Constants.UPDATE_ACTION)) {
				update(request, response);
			} else {
				list(request, response);	
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int customerId = Integer.parseInt(request.getParameter(Constants.CUSTOMER_ID_COL_NAME));
		Customer customer = customerDao.get(customerId);
		
		List<Account> accounts = accountDao.getByCustomerId(customerId);
		request.setAttribute("accounts", accounts);
		request.setAttribute("customer", customer);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("account-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter(Constants.CUSTOMER_ID_COL_NAME));
		Customer customer = customerDao.get(customerId);
		request.setAttribute("customer", customer);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("account-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter(Constants.CUSTOMER_ID_COL_NAME));
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		
		Account selectAccount = accountDao.get(id);
		Customer selectedCustomer = customerDao.get(customerId);
		
		request.setAttribute("customer", selectedCustomer);
		request.setAttribute("account", selectAccount);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("account-form.jsp");
		dispatcher.forward(request, response);

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String number = request.getParameter(Constants.NUMBER_COL_NAME);
		Double balance = Double.parseDouble(request.getParameter(Constants.BALANCE_COL_NAME));
		int customerId = Integer.parseInt(request.getParameter(Constants.CUSTOMER_ID_COL_NAME));
		
		Customer customer = customerDao.get(customerId);
		Account newAccount = new Account(number, balance, customer);
		
		accountDao.save(newAccount);
		response.sendRedirect(request.getContextPath()+"/account?action=list&customer_id="+customerId);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		int customerId = Integer.parseInt(request.getParameter(Constants.CUSTOMER_ID_COL_NAME));
		
		String number = request.getParameter(Constants.NUMBER_COL_NAME);
		Double balance = Double.parseDouble(request.getParameter(Constants.BALANCE_COL_NAME));
		
		Account accountInDb = accountDao.get(id);
		
		accountInDb.setBalance(balance);
		accountInDb.setNumber(number);
		accountDao.update(accountInDb);
		response.sendRedirect(request.getContextPath()+"/account?action=list&customer_id="+customerId);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		int customerId = Integer.parseInt(request.getParameter(Constants.CUSTOMER_ID_COL_NAME));
		accountDao.delete(id);
		response.sendRedirect(request.getContextPath()+"/account?action=list&customer_id="+customerId);
	}
}
