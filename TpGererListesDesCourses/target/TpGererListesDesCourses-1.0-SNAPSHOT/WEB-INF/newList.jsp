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
                <label for="name">Nom :</label>
                <input class="form-control" type="text" id="name" name="name" value="">
            </core:if>
            <core:if test="${!empty liste}">
                <p>Nom : ${liste.getName()}</p>
                <input type="hidden" value="${liste.getIdentifier()}" name="identifier"/>
                <input type="hidden" value="${liste.getName()}" name="name"/>
            </core:if>
            <core:choose>
            <core:when test="${!empty articles}">
                <core:forEach var="article" items="${articles}">
                    <li>${article.getName()}
                        <a href="${pageContext.request.contextPath}/deleteArticle?identifier=${liste.getIdentifier()}&identifierArticle=${article.getIdentifier()}"><i class="fas fa-trash-alt"></i></a>
                    </li>
                </core:forEach>
            </core:when>
            <core:otherwise>
            <p>Pas d"article disponible.</p>
            </core:otherwise>
            </core:choose>
            </ul>
            <article>
                <h3>Nouvel article</h3>
                <label for="nameArticle">Nom :</label>
                <input type="text" id="nameArticle" name="nameArticle">
                <button type="submit"><i class="far fa-plus-square"></i></button>
            </article>
        </form>
    </section>
    <p><a class="btn" href="${pageContext.request.contextPath}/listes"><i class="fas fa-arrow-alt-circle-left"></i></a></p>
    <hr />
    <%@include file="fragments/footer.jsp"%>
</body>
</html>
