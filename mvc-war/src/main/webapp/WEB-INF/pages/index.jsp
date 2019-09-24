<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main page of the client</title>
    <script src="<spring:url value="/resources/js/index.js" />"></script>
    <link href="<spring:url value="/resources/css/index.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<c:if test="${hierarchical != null}">
    <table>
        <tr>
            <th>Specialty ID</th>
            <th>Title</th>
            <th>Department ID</th>
            <th>Parent ID</th>
            <th>Item ID</th>
            <th>Item</th>
            <th>Price</th>
            <th>Specialty ID</th>
        </tr>
        <c:forEach items="${hierarchical}" var="hier">
            <tr>
                <td onclick="redirect(${hier.specId})">${hier.specId}</td>
                <td>${hier.title}</td>
                <td>${hier.departmentId}</td>
                <td onclick="redirect(${hier.parentId})">${hier.parentId}</td>
                <td onclick="redirect_item(${hier.id})">${hier.id}</td>
                <td><c:out value="${hier.item}" /></td>
                <td><c:out value="${hier.price}" /></td>
                <td onclick="redirect_specialty(${hier.specialtyId})">${hier.specialtyId}</td>
                <td><button onclick="redirect_order(${hier.id})">Order</button></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br />
</body>
</html>
