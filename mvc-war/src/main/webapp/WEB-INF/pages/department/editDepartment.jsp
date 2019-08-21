<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Edit Department</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="<spring:url value="/resources/css/edit.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<form name="departmentList" action="${pageContext.request.contextPath}/department">
    <table>
        <tr>
            <td>ID</td>
            <td>
                <label>
                    <input name="id" id="id" readonly value="${departmentList.id}" />
                </label>
            </td>
        </tr>
        <tr>
            <td>Title</td>
            <td>
                <label>
                    <input name="title" id="title" type="text" value="${departmentList.title}" />
                </label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><button type="button" onclick="edit()">Submit</button></td>
        </tr>
    </table>
</form>
</body>
</html>
