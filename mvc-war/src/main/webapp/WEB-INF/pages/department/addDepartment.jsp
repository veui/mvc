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
    <link href="<spring:url value="/resources/css/add.css" />" rel="stylesheet" type="text/css">
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
        </td>
    </tr>
    <tr>
        <td></td>
        <td><button type="button" onclick="add()">Submit</button></td>
    </tr>
</table>

</body>

<script type="text/javascript" >
    function validateString(username) {
        return /^[0-9a-zA-Z_.-]+$/.test(username);
    }
    function add() {
        var isValid = true;
        var add = {
            title : $('#title').val()
        };
        if (validateString(add.title) === false) {
            isValid = false;
        }
        if (isValid === true) {
            $.ajax({
                url: "/department/add",
                type: 'POST',
                processData: false,
                data: JSON.stringify(add),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    if (data.stat === 1) {
                        window.location.replace("/department")
                    }
                }
            });
        } else {
            window.alert('Your data is invalid. Please enter correct data');
        }
    }
</script>
</html>
