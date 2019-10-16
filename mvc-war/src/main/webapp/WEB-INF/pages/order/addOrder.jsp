<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add order</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/order.js" />"></script>
    <link rel="stylesheet" href="<spring:url value="/resources/css/order/add.css" />" type="text/css">
</head>
<body>
<h1>Order Input Form</h1>
<hr />

<table>
    <tr>
        <td>Date</td>
        <td>
            <label>
                <input name="date" id="date" type="date" />
            </label>
            <div id="date-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>Amount</td>
        <td>
            <label>
                <input name="amount" id="amount" type="text" />
            </label>
            <div id="amount-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>Cost</td>
        <td>
            <label>
                <input name="cost" id="cost" type="text" />
            </label>
            <div id="cost-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>Client</td>
        <td>
            <label for="selectClient">
                <datalist id="datalist">
                    <c:forEach items="${clientList}" var="cli">
                        <option id="optionDp" value="${cli.id}">${cli.username}</option>
                    </c:forEach>
                </datalist>
                <input name="Typelist" list="datalist" id="selectClient">
            </label>
            <div id="client-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>Item</td>
        <td>
            <c:choose>
                <c:when test="${item != null}">
                    <select disabled id="selectItem">
                        <option value="${item.id}">${item.item}</option>
                    </select>
                </c:when>

                <c:otherwise>
                    <select id="selectItem">
                        <c:forEach items="${itemList}" var="it">
                            <option id="optionDp" value="${it.id}">${it.item}</option>
                        </c:forEach>
                    </select>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td><button class="myButton" type="button" onclick="add()">Submit</button></td>
    </tr>
</table>
</body>
</html>
