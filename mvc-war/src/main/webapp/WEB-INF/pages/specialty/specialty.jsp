<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the specialty</title>
    <script src="<spring:url value="/resources/js/specialty/redirect.js" />"></script>
    <link href="<spring:url value="/resources/css/specialty/index.css" />" rel="stylesheet" type="text/css" />
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
                    <td><button onclick="redirect_find_department(${specialty.departmentId})">
                            ${specialty.departmentId}</button></td>
                    <td><button onclick="redirect_edit(${specialty.id})">Edit</button></td>
                    <td><button onclick="redirect_delete(${specialty.id})">Delete</button></td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${specialtyList}" var="spec">
                    <tr>
                        <td><c:out value="${spec.id}" /></td>
                        <td><c:out value="${spec.title}" /></td>
                        <td><button onclick="redirect_find_department(${spec.departmentId})">${spec.departmentId}</button></td>
                        <td><button onclick="redirect_edit(${spec.id})">Edit</button></td>
                        <td><button onclick="redirect_delete(${spec.id})">Delete</button></td>
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
    <button onclick="redirect_add()"></button>
</body>
</html>
