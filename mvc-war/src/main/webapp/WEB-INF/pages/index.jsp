<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main page of the client</title>
    <link href="<spring:url value="/resources/css/index.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<c:if test="${hierarchicalItem != null}">
    <table>
        <tr>
            <th>Item ID</th>
            <th>Item</th>
            <th>Price</th>
            <th>Specialty ID</th>
        </tr>
        <c:forEach items="${hierarchicalItem}" var="hier">
            <tr>
                <td><a href="/item/find/<c:out value='${hier.id}' />">${hier.id}</a></td>
                <td><c:out value="${hier.item}" /></td>
                <td><c:out value="${hier.price}" /></td>
                <td><a href="/specialty/find/<c:out value='${hier.specialtyId}' />">${hier.specialtyId}</a></td>
                <td><a href="order/add/${hier.id}">Order</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br />
</body>
</html>
