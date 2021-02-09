<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
	<title>Internet Qualiti Bank</title>
</head>
<body>
	<center>
		<h1>Internet Qualiti Bank</h1>
        <h2>
        	<a href="new">Add New Customer</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Customer</a>
        	
        </h2>
	</center>
    <div align="center">
        <table class="table">
            <tr class="thead-dark">
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Actions</th>
            </tr>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td scope="row"><c:out value="${customer.id}" /></td>
                    <td><c:out value="${customer.name}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td>
                    <a href="edit?id=<c:out value='${customer.id}'/>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                   <a href="delete?id=<c:out value='${customer.id}'/>">Delete</a>
                    	                	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
</body>
</html>
