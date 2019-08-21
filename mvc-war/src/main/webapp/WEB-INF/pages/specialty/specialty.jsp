<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the specialty</title>
    <link href="<spring:url value="/resources/css/index.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
    <table>
        <tr>
            <th>Specialty ID</th>
            <th>Title</th>
            <th>Department ID</th>
        </tr>
        <c:choose>
            <c:when test="${specialty != null}">
                <tr>
                    <td><c:out value="${specialty.id}" /></td>
                    <td><c:out value="${specialty.title}" /></td>
                    <td><a href="../../department/find/<c:out value='${specialty.departmentId}' />">${specialty.departmentId}</a></td>
                    <td><a href="../../specialty/edit/${specialty.id}">Edit</a></td>
                    <td><a href="../../specialty/delete/${specialty.id}">Delete</a></td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${specialtyList}" var="spec">
                    <tr>
                        <td><c:out value="${spec.id}" /></td>
                        <td><c:out value="${spec.title}" /></td>
                        <td><a href="../department/find/<c:out value='${spec.departmentId}' />">${spec.departmentId}</a></td>
                        <td><a href="specialty/edit/${spec.id}">Edit</a></td>
                        <td><a href="specialty/delete/${spec.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
    <br />
    <c:if test="${itemList != null}">
        <table>
            <tr>
                <th>Item ID</th>
                <th>Item</th>
                <th>Price</th>
                <th>Specialty ID</th>
            </tr>
            <c:forEach items="${itemList}" var="item">
                <tr>
                    <td><c:out value="${item.id}" /></td>
                    <td><c:out value="${item.item}" /></td>
                    <td><c:out value="${item.price}" /></td>
                    <td><c:out value="${item.specialtyId}" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
<br />
<a href="${pageContext.request.contextPath}/specialty/add">Add specialty</a>
</body>
</html>
