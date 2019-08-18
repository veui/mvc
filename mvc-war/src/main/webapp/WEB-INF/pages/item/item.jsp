<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Main page of the item</title>
</head>
<body>
    <table>
        <tr>
            <th>Item ID</th>
            <th>Item</th>
            <th>Price</th>
            <th>Specialty ID</th>
        </tr>
        <c:choose>
            <c:when test="${item != null}">
                <tr>
                    <td><c:out value="${item.id}" /></td>
                    <td><c:out value="${item.item}" /></td>
                    <td><c:out value="${item.price}" /></td>
                    <td>
                        <a href="/specialty/find/<c:out value='${item.specialtyId}' />">${item.specialtyId}</a>
                    </td>
                    <td><a href="item/edit/${item.id}">Edit</a></td>
                    <td><a href="item/delete/${item.id}">Delete</a></td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${itemList}" var="it">
                    <tr>
                        <td><c:out value="${it.id}" /></td>
                        <td><c:out value="${it.item}" /></td>
                        <td><c:out value="${it.price}" /></td>
                        <td><a href="/specialty/find/<c:out value='${it.specialtyId}' />">${it.specialtyId}</a></td>
                        <td><a href="item/edit/${it.id}">Edit</a></td>
                        <td><a href="item/delete/${it.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
<br />
<a href="${pageContext.request.contextPath}/item/add">Add item</a>
</body>
</html>

<style>
    body {
        font-family: Arial, sans-serif;
        padding: 0;@localhost
    margin: 0;
        color: #222222;
        background: #f0b841;
        height: 100vh;
    }

    header {
        margin-left: 30px;
        padding-top: 1%;
    }

    .mainMenu {
        margin-left: 24%;
        padding-top: 1%;
        display: block;
    }

    .mainMenu a {
        color: #fff;
        text-transform: uppercase;
        text-align: center;
        font-size: 18px;
    }

    table {
        font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
        font-size: 14px;
        width: 820px;
        text-align: left;
        border-collapse: collapse;
        background: #252F48;
        margin: 5px;
        color: white;
    }
    table th {
        color: #EDB749;
        border-bottom: 1px solid #37B5A5;
        padding: 12px 17px;
    }
    table td {
        color: #f2fbff;
        border-bottom: 1px solid #37B5A5;
        border-right:1px solid #37B5A5;
        padding: 7px 17px;

    }

    h1 {
        font-size: 75px;
        color: #fff;
        text-transform: uppercase;
        text-align: center;
    }

    .addButton {
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
        margin-left: 30%;
    }
    .addButton:hover {
        background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #b3ad09), color-stop(1, #1be053));
        background:-moz-linear-gradient(top, #b3ad09 5%, #1be053 100%);
        background:-webkit-linear-gradient(top, #b3ad09 5%, #1be053 100%);
        background:-o-linear-gradient(top, #b3ad09 5%, #1be053 100%);
        background:-ms-linear-gradient(top, #b3ad09 5%, #1be053 100%);
        background:linear-gradient(to bottom, #b3ad09 5%, #1be053 100%);
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#b3ad09', endColorstr='#1be053',GradientType=0);
        background-color:#b3ad09;
    }
    .addButton:active {
        position:relative;
        top:1px;
    }
</style>