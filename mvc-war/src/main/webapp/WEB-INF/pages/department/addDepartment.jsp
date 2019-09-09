<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add department</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="<spring:url value="/resources/css/department/add.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Department Input Form</h1>
<hr />

<table>
    <tr>
        <td>Title</td>
        <td>
            <label>
                <input name="title" id="title" type="text" />
            </label>
            <div id="department-non-unique-message"></div>
        </td>
    </tr>
    <tr>
        <td></td>
        <td><button type="button" onclick="add()">Submit</button></td>
    </tr>
</table>
</body>
</html>
