<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add order</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
            <select id="selectClient">
                <c:forEach items="${clientList}" var="cli">
                    <option id="optionDp" value="${cli.id}">${cli.id}</option>
                </c:forEach>
            </select>
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
            $('#date').val('');
            $('#amount').val('');
            $('#cost').val('');
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
