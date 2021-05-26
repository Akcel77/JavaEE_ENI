<%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 26/05/2021
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Winner</title>
</head>
<body>
    <h1 style="text-align: center; margin: 5rem auto;">Winner of the ChiFuMi</h1>
    <div style="text-align: center; display: block; margin: 5rem auto;">
        <p><%= request.getAttribute("resultGame")%></p>
        <button style="margin: 5rem auto;"><a href="<%= request.getContextPath()%>/ServletTry"> Rejouer </a></button>
    </div>
</body>
</html>
