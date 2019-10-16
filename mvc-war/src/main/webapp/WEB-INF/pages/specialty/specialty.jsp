<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the specialty</title>
    <script src="<spring:url value="/resources/js/specialty.js" />"></script>
    <link href="<spring:url value="/resources/css/specialty/index.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
    <table>
        <tr>
            <th>Specialty ID</th>
            <th>Title</th>
            <th>Department</th>
            <th>Parent</th>
        </tr>
        <c:choose>
            <c:when test="${specialty != null}">
                <tr>
                    <td><c:out value="${specialty.id}" /></td>
                    <td><c:out value="${specialty.title}" /></td>
                    <td>
                        <button onclick="redirect_find_department(${specialty.departmentId})">
                            <c:forEach items="${departmentList}" var="dep">
                                <c:if test="${specialty.departmentId == dep.id}">
                                    ${dep.title}
                                </c:if>
                            </c:forEach>
                        </button>
                    </td>
                    <td>
                        <c:forEach items="${specialtyList}" var="spe">
                            <c:if test="${spe.id == specialty.parentId}">
                                ${spe.title}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td><button onclick="redirect_edit(${specialty.id})">Edit</button></td>
                    <td><button onclick="redirect_delete(${specialty.id})">Delete</button></td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${specialtyList}" var="spec">
                    <tr>
                        <td><c:out value="${spec.id}" /></td>
                        <td><c:out value="${spec.title}" /></td>
                        <td>
                            <button onclick="redirect_find_department(${spec.departmentId})">
                                <c:forEach items="${departmentList}" var="dep">
                                    <c:if test="${spec.departmentId == dep.id}">
                                        ${dep.title}
                                    </c:if>
                                </c:forEach>
                            </button>
                        </td>
                        <td>
                            <c:forEach items="${specialtyList}" var="sl">
                                <c:if test="${spec.parentId == sl.id}">
                                    ${sl.title}
                                </c:if>
                            </c:forEach>
                        </td>
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
                <th>Specialty</th>
            </tr>
            <c:forEach items="${itemList}" var="item">
                <tr>
                    <td><c:out value="${item.id}" /></td>
                    <td><c:out value="${item.item}" /></td>
                    <td><c:out value="${item.price}" /></td>
                    <td>
                        <c:forEach items="${specialtyList}" var="sl">
                            <c:if test="${item.specialtyId == sl.id}">
                                ${sl.title}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
<br />
    <button class="addButton" onclick="redirect_add()">Add specialty</button>
</body>
</html>
