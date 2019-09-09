<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the item</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/item/index.css" />" />
</head>
<body>
    <table>
        <tr>
            <th>Item ID</th>
            <th>Item</th>
            <th>Price</th>
            <th>Specialty</th>
        </tr>
        <c:choose>
            <c:when test="${item != null}">
                <tr>
                    <td><c:out value="${item.id}" /></td>
                    <td><c:out value="${item.item}" /></td>
                    <td><c:out value="${item.price}" /></td>
                    <td>
                        <a href="../../specialty/find/<c:out value='${item.specialtyId}' />">
                                ${item.specialtyId}</a>
                    </td>
                    <td><a href="../../item/edit/${item.id}">Edit</a></td>
                    <td><a href="../../item/delete/${item.id}">Delete</a></td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${itemList}" var="it">
                    <tr>
                        <td><c:out value="${it.id}" /></td>
                        <td><c:out value="${it.item}" /></td>
                        <td><c:out value="${it.price}" /></td>
                        <td><a href="../../specialty/find/<c:out value='${it.specialtyId}' />">
                                ${it.specialtyId}
                        </td>
                        <td><a href="../../item/edit/${it.id}">Edit</a></td>
                        <td><a href="../../item/delete/${it.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
<br />
<a href="${pageContext.request.contextPath}/item/add">Add item</a>
</body>
</html>
