<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the client</title>
    <link href="<spring:url value="/resources/css/client/index.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
    <table>
        <tr>
            <th>Client ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone number</th>
        </tr>
        <c:choose>
            <c:when test="${client != null}">
                <tr>
                    <td><c:out value="${client.id}" /></td>
                    <td><c:out value="${client.username}" /></td>
                    <td><c:out value="${client.password}" /></td>
                    <td><c:out value="${client.firstName}" /></td>
                    <td><c:out value="${client.lastName}" /></td>
                    <td><c:out value="${client.email}" /></td>
                    <td><c:out value="${client.phone}" /></td>
                    <td><a href="client/edit/${client.id}">Edit</a></td>
                    <td><a href="client/delete/${client.id}">Delete</a></td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${clientList}" var="client">
                    <tr>
                        <td><c:out value="${client.id}" /></td>
                        <td><c:out value="${client.username}" /></td>
                        <td><c:out value="${client.password}" /></td>
                        <td><c:out value="${client.firstName}" /></td>
                        <td><c:out value="${client.lastName}" /></td>
                        <td><c:out value="${client.email}" /></td>
                        <td><c:out value="${client.phone}" /></td>
                        <td><a href="client/edit/${client.id}">Edit</a></td>
                        <td><a href="client/delete/${client.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
<br />
<a href="${pageContext.request.contextPath}/client/add">Add client</a>
</body>
</html>
