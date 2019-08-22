<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>

<html>
    <head>
        <title>Edit Order</title>
        <link href="<spring:url value="/resources/css/order/edit.css" />" rel="stylesheet" type="text/css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
<body>
    <table>
        <tr>
            <td>ID</td>
            <td>
                <label><input name="id" id="id" readonly value="${orderList.id}" /></label>
            </td>
        </tr>
        <tr>
            <td>Date</td>
            <td>
                <label>
                    <input name="date" id="date" type="date" />
                </label>
            </td>
        </tr>
        <tr>
            <td>Amount</td>
            <td>
                <label>
                    <input name="amount" id="amount" type="text" />
                </label>
            </td>
        </tr>
        <tr>
            <td>Cost</td>
            <td><label>
                <input name="cost" id="cost" type="text" />
            </label></td>
        </tr>
        <tr>
            <td>Client ID</td>
            <td>
                <label for="selectClient">
                    <select id="selectClient">
                        <c:forEach items="${clientList}" var="cli">
                            <option id="optionDp" value="${cli.id}">${cli.id}</option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td>Item ID</td>
            <td>
                <label for="selectItem">
                    <select id="selectItem">
                        <c:forEach items="${itemList}" var="it">
                            <option id="optionDp" value="${it.id}">${it.id}</option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td><button type="button" onclick="edit()">Submit</button></td>
        </tr>
    </table>
</body>
</html>
