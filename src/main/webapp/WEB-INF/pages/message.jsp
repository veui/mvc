<%--
  Created by IntelliJ IDEA.
  User: arttu
  Date: 01.07.2019
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Msg : ${msg}
<br />
${1 + 1}
<br/>
<%= new java.util.Date() %>
<c:if test="${not empty msg}">${msg}</c:if>
</body>
</html>
