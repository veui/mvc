<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Edit Client</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/client/edit.js" />" rel="script" type="text/javascript"></script>
    <link href="<spring:url value="/resources/css/client/edit.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<form name="clientList" action="${pageContext.request.contextPath}/client">
    <table>
        <tr>
            <td>ID</td>
            <td>
                <label>
                    <input name="id" id="id" readonly value="${clientList.id}">
                </label>
            </td>
        </tr>
        <tr>
            <td>Username</td>
            <td>
                <label>
                    <input name="username" id="username" type="text" value="${clientList.username}" />
                </label>
                <div id="username-non-unique-message"></div>
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <label>
                    <input name="password" id="password" type="password" value="${clientList.password}" />
                </label>
            </td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><label>
                <input name="lastName" id="lastName" type="text" value="${clientList.lastName}" />
            </label></td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><label>
                <input name="firstName" id="firstName" type="text" value="${clientList.firstName}" />
            </label></td>
        </tr>
        <tr>
            <td>Email</td>
            <td>
                <label>
                    <input name="email" id="email" type="email" value="${clientList.email}" />
                </label>
                <div id="email-non-unique-message"></div>
            </td>
        </tr>
        <tr>
            <td>Phone</td>
            <td>
                <label>
                    <input name="phone" id="phone" type="text" value="${clientList.phone}" />
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

