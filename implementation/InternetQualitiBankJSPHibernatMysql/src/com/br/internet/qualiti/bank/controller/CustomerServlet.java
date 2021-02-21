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

import com.br.internet.qualiti.bank.dao.CustomerDao;
import com.br.internet.qualiti.bank.model.Customer;
import com.br.internet.qualiti.bank.util.Constants;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDao customerDao;
	
	public void init() {
		customerDao = new CustomerDao();
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
			}
			else if (action.equalsIgnoreCase(Constants.NEW_ACTION)) {
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
		List<Customer> customers = customerDao.get();
		request.setAttribute("customers", customers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		Customer selectedCustomer = customerDao.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
		request.setAttribute("customer", selectedCustomer);
		dispatcher.forward(request, response);

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter(Constants.NAME_COL_NAME);
		String email = request.getParameter(Constants.EMAIL_COL_NAME);
		Customer newCustomer = new Customer(name, email);
		
		customerDao.save(newCustomer);
		response.sendRedirect(request.getContextPath());
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		String name = request.getParameter(Constants.NAME_COL_NAME);
		String email = request.getParameter(Constants.EMAIL_COL_NAME);
		
		Customer customer = new Customer(id, name, email);
		customerDao.update(customer);
		response.sendRedirect(request.getContextPath());
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		customerDao.delete(id);
		response.sendRedirect(request.getContextPath());
	}
}
