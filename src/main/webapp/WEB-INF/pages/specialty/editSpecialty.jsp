<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Edit Specialty</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<form name="specialtyList" action="${pageContext.request.contextPath}/specialty">
    <table>
        <tr>
            <td>Title</td>
            <td>
                <label>
                    <input name="title" id="title" type="text" value="${specialtyList.title}" />
                </label>
            </td>
        </tr>
        <tr>
            <td>Department ID</td>
            <td>
                <select id="selectId">
                    <c:forEach items="${departmentList}" var="department">
                        <option id="optionDp" value="${department.id}">${department.id}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><button type="button" onclick="edit()">Submit</button></td>
        </tr>
    </table>
</form>
</body>
<script>
    function edit() {
        function validateString(username) {
            return /^[0-9a-zA-Z_.-]+$/.test(username);
        }
        var isValid = true;
        var edit = {
            id : ${specialtyList.id},
            title : $('#title').val(),
            departmentId : $('#optionDp').val()
        };
        if (validateString(edit.title) === false) {
            isValid = false;
        }
        if (isValid === true) {
            $.ajax({
                url: "/specialty/edit",
                type: 'POST',
                processData: false,
                data: JSON.stringify(edit),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    if (data.stat === 1) {
                        window.location.assign("/specialty");
                    }
                }
            })
        } else {
            window.alert('Your data is invalid. Please enter correct data');
            $('#title').val('');
        }
    }
</script>
</html>

<style>
    body {
        font-family: Arial, sans-serif;
        padding: 0;
        margin: 0;
        color: #222222;
        background: #f0b841;
    }

    .mainMenu {
        margin-left: 30%;
        padding-top: 0%;
        display: block;
    }


    table td {
        color: white;
        padding: 20px 90px;
        position: relative;
        text-align: center;
    }


    div {
        box-sizing: border-box;
    }

    section {
        background: #f0b841;
        height: 100vh;
        font-size: 21px;
    }

    h1 {
        font-size: 75px;
        color: #fff;
        text-transform: uppercase;
        text-align: center;
        margin: -5px;
    }

    input {
        border-radius: 8px;
        outline: none;
        background-color: #f5f5e7;
        border: none;
        text-align: center;
        height: 40px;
        font-family: Arial, sans-serif;
        font-size: 16px;
    }

    .myButton {
        -moz-box-shadow: 0px 10px 14px -7px #3cb558;
        -webkit-box-shadow: 0px 10px 14px -7px #3cb558;
        box-shadow: 0px 10px 14px -7px #3cb558;
        background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #1be053), color-stop(1, #b3ad09));
        background:-moz-linear-gradient(top, #1be053 5%, #b3ad09 100%);
        background:-webkit-linear-gradient(top, #1be053 5%, #b3ad09 100%);
        background:-o-linear-gradient(top, #1be053 5%, #b3ad09 100%);
        background:-ms-linear-gradient(top, #1be053 5%, #b3ad09 100%);
        background:linear-gradient(to bottom, #1be053 5%, #b3ad09 100%);
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#1be053', endColorstr='#b3ad09',GradientType=0);
        background-color:#1be053;
        -moz-border-radius:8px;
        -webkit-border-radius:8px;
        border-radius:8px;
        display:inline-block;
        cursor:pointer;
        color:#ffffff;
        font-family:Arial;
        font-size:20px;
        font-weight:bold;
        padding:13px 32px;
        text-decoration:none;
        text-shadow:0px 1px 0px #12941d;
    }
    .myButton:hover {
        background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #b3ad09), color-stop(1, #1be053));
        background:-moz-linear-gradient(top, #b3ad09 5%, #1be053 100%);
        background:-webkit-linear-gradient(top, #b3ad09 5%, #1be053 100%);
        background:-o-linear-gradient(top, #b3ad09 5%, #1be053 100%);
        background:-ms-linear-gradient(top, #b3ad09 5%, #1be053 100%);
        background:linear-gradient(to bottom, #b3ad09 5%, #1be053 100%);
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#b3ad09', endColorstr='#1be053',GradientType=0);
        background-color:#b3ad09;
    }
    .myButton:active {
        position:relative;
        top:1px;
    }
</style>