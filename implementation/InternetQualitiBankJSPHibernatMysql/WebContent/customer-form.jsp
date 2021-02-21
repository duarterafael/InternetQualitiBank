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
		<c:if test="${customer != null}">
			<form action="${pageContext.request.contextPath}/customer?action=update" method="post">
        </c:if>
        <c:if test="${customer == null}">
			<form action="${pageContext.request.contextPath}/customer?action=insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${customer != null}">
            			Atualizar cliente
            		</c:if>
            		<c:if test="${customer == null}">
            			Adcionar cliente
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${customer != null}">
        			<input type="hidden" name="id" value="<c:out value='${customer.id}' />" />
        		</c:if>            
            <tr>
                <th>Name: </th>
                <td>
                	<input type="text" name="name" size="45"
                			value="<c:out value='${customer.name}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                	<input type="text" name="email" size="45"
                			value="<c:out value='${customer.email}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            	<c:if test="${customer != null}">
							<input type="submit" value="Atualizar" />
		        </c:if>
		        <c:if test="${customer == null}">
							<input type="submit" value="Salvar" />
		        </c:if>
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
