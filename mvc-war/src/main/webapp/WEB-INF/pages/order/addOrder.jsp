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
                <input id="selectClient"/>
            </label>
        </td>
    </tr>
    <tr>
        <td>Item</td>
        <td>
            <label for="selectItem">
                <input id="selectItem"/>
            </label>
        </td>
    </tr>
    <tr>
        <td><button class="myButton" type="button" onclick="add()">Submit</button></td>
    </tr>
</table>
</body>
</html>


<script>
    $( function() {
        var availableTags = [];
        var availableTags1 = [];
        <c:forEach items="${itemList}" var="it">
        var str = '${it.item}';
        availableTags.push(str);
        </c:forEach>

        <c:forEach items="${clientList}" var="cli">
        var str = '${cli.username}';
        availableTags1.push(str);
        </c:forEach>

        $( "#selectItem" ).autocomplete({
            source: availableTags
        });

        $( "#selectClient" ).autocomplete({
            source: availableTags1
        });
    });


    function formIdAllForOrderItemAdd(str) {
        <c:forEach items="${itemList}" var="it">
        var strVar = '${it.item}';
        if(strVar == str)
            return '${it.id}';
        </c:forEach>
    }

    function formIdAllForOrderClientAdd(str) {
        <c:forEach items="${clientList}" var="cli">
        var strVar = '${cli.username}';
        if(strVar == str)
            return '${cli.id}';
        </c:forEach>
    }
</script>