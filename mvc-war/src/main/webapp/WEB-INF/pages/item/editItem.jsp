<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Edit Item</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="<spring:url value="/resources/css/item/edit.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<form name="specialtyList" action="${pageContext.request.contextPath}/item">
    <table>
        <tr>
            <td>ID</td>
            <td>
                <label>
                    <input name="id" id="id" readonly value="${itemList.id}">
                </label>
            </td>
        </tr>
        <tr>
            <td>Item</td>
            <td>
                <label>
                    <input name="item" id="item" type="text" value="${itemList.item}" />
                </label>
            </td>
        </tr>
        <tr>
            <td>Price</td>
            <td>
                <label>
                    <input name="price" id="price" type="text" value="${itemList.price}" />
                </label>
            </td>
        </tr>
        <tr>
            <td>Specialty</td>
            <td>
                <label>
                    <select id="selectId">
                        <c:forEach items="${specialtyList}" var="spec">
                            <option id="optionDp" value="${spec.id}">${spec.title}</option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td><button type="button" onclick="edit()">Submit</button></td>
        </tr>
    </table>
</form>
</body>
</html>