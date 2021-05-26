<%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 26/05/2021
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Try Test</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <h1 style="text-align: center; margin: 5rem auto;">Chi Fou Mi </h1>
    <div class="div-box">

        <a href="ServletBot?userEntry=chi"><img src="${pageContext.request.contextPath}/img/chi.png" alt="">  </a>
        <a href="ServletBot?userEntry=fou"><img src="${pageContext.request.contextPath}/img/fou.png" alt="">   </a>
        <a href="ServletBot?userEntry=mi"><img src="${pageContext.request.contextPath}/img/mi.png" alt="">  </a>

    </div>

</body>
</html>
