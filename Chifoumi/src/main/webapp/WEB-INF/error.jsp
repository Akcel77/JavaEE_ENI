<%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 26/05/2021
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>ERREUR</title>
</head>
<body style="text-align: center;">
    <h1>ERREUR</h1>
    <h2>Tu ne peux que jouer a Chi Fou ou Mi</h2>
    <p>
        (<%= exception%>) <%=exception.getMessage()%>
    </p>

</body>
</html>
