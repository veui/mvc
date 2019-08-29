<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the department</title>
    <link href="<spring:url value="/resources/css/department/index.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
    <table id="departmentTable">
        <tr>
            <th>Department ID</th>
            <th>Username</th>
        </tr>
        <c:choose>
            <c:when test="${department != null}">
                <td><c:out value="${department.id}" /></td>
                <td><c:out value="${department.title}" /></td>
                <td><a href="../../department/edit/${department.id}">Edit</a></td>
                <td><a href="../../department/delete/${department.id}">Delete</a></td>
            </c:when>
            <c:otherwise>
                <c:forEach items="${departmentList}" var="department">
                    <tr>
                        <td><c:out value="${department.id}" /></td>
                        <td><c:out value="${department.title}" /></td>
                        <td><a href="../../department/edit/${department.id}">Edit</a></td>
                        <td><a href="../../department/delete/${department.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
    <br />
    <table>
        <c:if test="${specialtyList != null}">
            <th>
                <td>Specialty ID</td>
                <td>Title</td>
                <td>Department ID</td>
            <th>
            <c:forEach items="${specialtyList}" var="specialty">
                <tr>
                    <td><c:out value="${specialty.id}" /></td>
                    <td><c:out value="${specialty.title}" /></td>
                    <td><a href="../../department/find/<c:out value='${specialty.departmentId}' />">
                            ${specialty.departmentId}</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
<br />
<a href="${pageContext.request.contextPath}/department/add">Add department</a>
</body>
</html>
