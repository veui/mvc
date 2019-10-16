<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the order</title>
    <script src="<spring:url value="/resources/js/order.js" />"></script>
    <link href="<spring:url value="/resources/css/order/index.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<c:if test="${orderList != null}">
    <table>
        <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Amount</th>
            <th>Cost</th>
            <th>Client</th>
            <th>Item</th>
        </tr>
        <c:forEach items="${orderList}" var="ord">
            <tr>
                <td><c:out value="${ord.id}" /></td>
                <td><c:out value="${ord.date}" /></td>
                <td><c:out value="${ord.amount}" /></td>
                <td><c:out value="${ord.cost}" /></td>
                <td onclick="redirect_find_client(${ord.clientId})">
                        <c:forEach items="${clientList}" var="cli">
                            <c:if test="${ord.clientId == cli.id}">
                                ${cli.username}
                            </c:if>
                        </c:forEach>
                </td>
                <td onclick="redirect_find_item(${ord.itemId})">
                        <c:forEach items="${itemList}" var="it">
                            <c:if test="${ord.itemId == it.id}">
                                ${it.item}
                            </c:if>
                        </c:forEach>
                </td>
                <td><button onclick="redirect_edit(${ord.id})">Edit</button> </td>
                <td><button onclick="redirect_delete(${ord.id})">Delete</button> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br />
<button class="addButton" onclick="redirect_add()">Add order</button>
</body>
</html>
