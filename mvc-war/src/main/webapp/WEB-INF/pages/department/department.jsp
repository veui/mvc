<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the department</title>
    <script src="<spring:url value="/resources/js/department/department.js" />"></script>
    <link href="<spring:url value="/resources/css/department/index.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
    <table id="departmentTable">
        <tr>
            <th>Department ID</th>
            <th>Department</th>
        </tr>
        <c:choose>
            <c:when test="${department != null}">
                <td><c:out value="${department.id}" /></td>
                <td><c:out value="${department.title}" /></td>
                <td><button onclick="redirect_edit(${department.id})">Edit</button></td>
                <td><button onclick="redirect_delete(${department.id})">Delete</button></td>
            </c:when>
            <c:otherwise>
                <c:forEach items="${departmentList}" var="department">
                    <tr>
                        <td><c:out value="${department.id}" /></td>
                        <td><c:out value="${department.title}" /></td>
                        <td><button onclick="redirect_edit(${department.id})">Edit</button></td>
                        <td><button onclick="redirect_delete(${department.id})">Delete</button></td>
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
                    <td onclick="redirect_find_spec(${specialty.id})"><c:out value="${specialty.id}" /></td>
                    <td><c:out value="${specialty.title}" /></td>
                    <td><button onclick="redirect_find(${specialty.departmentId})">
                            ${specialty.departmentId}</button></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
<br />
    <button onclick="redirect_add()">Add</button>
</body>
</html>
