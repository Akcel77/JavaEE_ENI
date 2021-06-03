<%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 02/06/2021
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<%@ page import="com.diagneaxel.javaee.messages.Reader" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/head.jsp">
  <jsp:param name="title" value="Courses - Creation d'une nouvelle liste"/>
</jsp:include>
<body>
<jsp:include page="fragments/header.jsp">
  <jsp:param name="h1" value="Courses - Listes de courses"/>
  <jsp:param name="h2" value="Creation d'une nouvelle liste"/>
</jsp:include>
<%@include file="./fragments/errors.jsp"%>
  <body>
  <h1>TEST</h1>
  <h2>Test conditions utilisations</h2>
  <div class="condtionU">
    <a href="${urlTarget}?accept">Valider</a>
    <a href="${urlTarget}">Refuser</a>
  </div>

  </body>
</html>