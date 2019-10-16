<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add client</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/client.js" />"></script>
    <link href="<spring:url value="/resources/css/client/add.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>Client Input Form</h1>
<hr />
<table>
    <tr>
        <td>Username</td>
        <td>
            <label>
                <input name="username" id="username" type="text" />
            </label>
            <div id="username-non-unique-message"></div>
            <div id="username-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>Password</td>
        <td>
            <label>
                <input name="password" id="password" type="password" />
            </label>
            <div id="password-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>Last Name</td>
        <td>
            <label>
                <input name="lastName" id="lastName" type="text" />
            </label>
            <div id="lastname-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>First Name</td>
        <td>
            <label>
                <input name="firstName" id="firstName" type="text" />
            </label>
            <div id="firstname-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>Email</td>
        <td>
            <label>
                <input name="email" id="email" type="email" />
            </label>
            <div id="email-non-unique-message"></div>
            <div id="email-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>Phone</td>
        <td>
            <label>
                <input name="phone" id="phone" type="text" />
            </label>
            <div id="phone-non-unique-message"></div>
            <div id="phone-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td ></td>
        <td><button class="myButton" type="button" onclick="add()">Submit</button></td>
    </tr>
</table>
</body>
</html>
