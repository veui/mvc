<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add order</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<spring:url value="/resources/css/add.css" />" type="text/css">
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
            <c:choose>
                <c:when test="${item != null}">
                    <select disabled id="selectItem">
                        <option value="${item.id}">${item.id}</option>
                    </select>
                </c:when>

                <c:otherwise>
                    <select id="selectItem">
                        <c:forEach items="${itemList}" var="it">
                            <option id="optionDp" value="${it.id}">${it.id}</option>
                        </c:forEach>
                    </select>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td><button type="button" onclick="add()">Submit</button></td>
    </tr>
</table>
</body>

<script type="text/javascript" >
    function validateFloat(float) {
        var valid = false;
        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/;
        if (regex.test(float)) {
            valid = true;
        }
        return valid;
    }
    function validateInt(int) {
        var valid = false;
        if (typeof int === 'number' && int > 0) {
            valid = true;
        }
        return valid;
    }
    function add() {
        var isValid = true;
        var add = {
            date : $('#date').val(),
            amount : $('#amount').val(),
            cost : $('#cost').val(),
            clientId : $("#selectClient :selected").val(),
            itemId : $("#selectItem :selected").val()
        };
        if (validateInt(add.amount) === false) {
            isValid = false;
        }
        if (validateFloat(add.cost) === false) {
            isValid = false;
        }
        if (isValid === true) {
            $.ajax({
                url: "/order/add",
                type: 'POST',
                processData: false,
                data: JSON.stringify(add),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    if (data.stat === 1) {
                        window.location.replace("/order");
                    }
                }
            });
        } else {
            window.alert('Your data is invalid. Please enter correct data');
        }
    }
</script>
</html>
