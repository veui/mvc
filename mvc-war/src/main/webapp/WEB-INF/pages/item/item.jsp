<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the item</title>
    <script src="<spring:url value="/resources/js/item/redirect.js" />"></script>
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
                    <td onclick="redirect_find_spec(${item.specialtyId})"><c:out value='${item.specialtyId}' /></td>
                    <td><button onclick="redirect_edit()">Edit</button></td>
                    <td><button onclick="redirect_delete(${item.id})">Delete</button></td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${itemList}" var="it">
                    <tr>
                        <td><c:out value="${it.id}" /></td>
                        <td><c:out value="${it.item}" /></td>
                        <td><c:out value="${it.price}" /></td>
                        <td onclick="redirect_find_spec(${item.specialtyId})"><c:out value='${item.specialtyId}' /></td>
                        <td><button onclick="redirect_edit()">Edit</button></td>
                        <td><button onclick="redirect_delete(${item.id})">Delete</button></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
<br />
    <button onclick="redirect_add()">Add</button>
</body>
</html>
