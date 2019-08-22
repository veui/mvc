<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add client</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/client/add.js" />"></script>
    <link href="<spring:url value="/resources/css/client/add.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
    <table>
        <tr>
            <td>Username</td>
            <td>
                <label>
                    <input name="username" id="username" type="text" />
                </label>
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <label>
                    <input name="password" id="password" type="password" />
                </label>
            </td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><label>
                <input name="lastName" id="lastName" type="text" />
            </label></td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><label>
                <input name="firstName" id="firstName" type="text" />
            </label></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><label>
                <input name="email" id="email" type="email" />
            </label></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td>
                <label>
                    <input name="phone" id="phone" type="text" />
                </label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><button type="button" onclick="add()">Submit</button></td>
        </tr>
    </table>
</body>
</html>
