<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the order</title>
    <link href="<spring:url value="/resources/css/index.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<c:if test="${orderList != null}">
    <table>
        <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Amount</th>
            <th>Cost</th>
            <th>Client ID</th>
            <th>Item ID</th>
        </tr>
        <c:forEach items="${orderList}" var="ord">
            <tr>
                <td><c:out value="${ord.id}" /></td>
                <td><c:out value="${ord.date}" /></td>
                <td><c:out value="${ord.amount}" /></td>
                <td><c:out value="${ord.cost}" /></td>
                <td><a href="/client/find/<c:out value='${ord.clientId}' />">${ord.clientId}</a></td>
                <td><a href="/item/find/<c:out value='${ord.itemId}' />">${ord.itemId}</a></td>
                <td><a href="order/edit/${ord.id}">Edit</a></td>
                <td><a href="order/delete/${ord.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br />
<a href="${pageContext.request.contextPath}/order/add">Add order</a>
</body>
</html>
