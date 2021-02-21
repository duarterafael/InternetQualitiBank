<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Internet Qualiti Bank</title>
</head>
<body>
	<center>
		<h1>Internet Qualiti Bank</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/customer?action=list">Listar clientes</a>
 
        </h2>
	</center>
    <div align="center">
    	<h2>
        	Cliente: <c:out value='${customer.name}'/>
        </h2>
		<c:if test="${account != null}">
			<form action="${pageContext.request.contextPath}/account?action=update&customer_id=<c:out value='${customer.id}'/>" method="post">
        </c:if>
        <c:if test="${account == null}">
			<form action="${pageContext.request.contextPath}/account?action=insert&customer_id=<c:out value='${customer.id}'/>" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${account != null}">
            			Atualizar conta
            		</c:if>
            		<c:if test="${account == null}">
            			Adcionar conta
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${account != null}">
        			<input type="hidden" name="id" value="<c:out value='${account.id}' />" />
        		</c:if>            
            <tr>
                <th>Numero: </th>
                <td>
                	<input type="text" name="number" size="45"
                			value="<c:out value='${account.number}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Saldo: </th>
                <td>
                	<input type="number" name="balance" size="45"
                			value="<c:out value='${account.balance}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            	<c:if test="${account != null}">
							<input type="submit" value="Atualizar" />
		        </c:if>
		        <c:if test="${account == null}">
							<input type="submit" value="Salvar" />
		        </c:if>
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
