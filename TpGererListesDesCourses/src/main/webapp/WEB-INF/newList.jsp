<%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 01/06/2021
  Time: 14:16
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
    <section>
        <form method="post" action="${pageContext.request.contextPath}/addArticle">
            <core:if test="${empty liste}">
                <label for="name">Nouvelle Liste :</label>
                <input class="form-control" type="text" id="name" name="name" value="">
            </core:if>
            <core:if test="${!empty liste}">
                <h3>Liste : ${liste.getName()}</h3>
                <input type="hidden" value="${liste.getIdentifier()}" name="identifier"/>
                <input type="hidden" value="${liste.getName()}" name="name"/>
            </core:if>
            <core:choose>
            <core:when test="${!empty articles}">
                <core:forEach var="article" items="${articles}">
                    <li>${article.getName()}
                        <a href="${pageContext.request.contextPath}/deleteArticle?identifier=${liste.getIdentifier()}&identifierArticle=${article.getIdentifier()}"><i class="material-icons right">delete</i></a>
                    </li>
                </core:forEach>
            </core:when>
            <core:otherwise>
            <p>Pas d'article disponible.</p>
            </core:otherwise>
            </core:choose>
            </ul>
            <article>
                <h4>Nouvel article</h4>
                <label for="nameArticle">Article :</label>
                <input type="text" id="nameArticle" name="nameArticle">
                <button  class="btn btn-link" type="submit"><i class="material-icons">add</i></button>
            </article>
        </form>
    </section>
    <p><a class="btn" href="${pageContext.request.contextPath}/listes"><i class="material-icons">arrow_back</i></a></p>
    <hr />
    <%@include file="fragments/footer.jsp"%>
</body>
</html>
