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
        	<a href="${pageContext.request.contextPath}/account?action=new&customer_id=<c:out value='${customer.id}'/>">Nova conta</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="${pageContext.request.contextPath}/customer?action=list">Listar clientes</a>
        </h2>
	</center>
    <div align="center">
    	<h2>
        	Cliente: <c:out value='${customer.name}'/>
        </h2>
        <table class="table table-striped table-bordered" style="width:100%">
            <tr class="thead-dark">
                <th scope="col">ID</th>
                <th scope="col">Numero</th>
                <th scope="col">Saldo</th>
                <th scope="col">Ações</th>
            </tr>
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td scope="row"><c:out value="${account.id}" /></td>
                    <td><c:out value="${account.number}" /></td>
                    <td><c:out value="${account.balance}" /></td>
                    <td>
                    <a href="${pageContext.request.contextPath}/account?action=edit&customer_id=<c:out value='${customer.id}'/>&id=<c:out value='${account.id}'/>">Atualizar</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                   	<a href="${pageContext.request.contextPath}/account?action=delete&customer_id=<c:out value='${customer.id}'/>&id=<c:out value='${account.id}'/>">Deletar</a>
                    	                	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
</body>
</html>
