<%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 01/06/2021
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.diagneaxel.javaee.messages.Reader" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <jsp:include page="fragments/head.jsp">
        <jsp:param name="title" value="Courses - Listes Disponibles"/>
    </jsp:include>
    <body>

        <jsp:include page="fragments/header.jsp">
            <jsp:param name="h1" value="Courses - Listes de courses"/>
            <jsp:param name="h2" value="Liste Disponible"/>
        </jsp:include>
        <%@include file="./fragments/errors.jsp"%>
        <section>
            <core:choose>
            <core:when test="${!empty listes}">
                <ul>
                    <core:forEach var="liste" items="${listes}">
                        <li>Liste : ${liste.name}
                            <div class="block">
                                <a href="${pageContext.request.contextPath}/display?identifier=${liste.getIdentifier()}" title="Editer la liste"><i  class="material-icons">create</i></a>
                                <a href="${pageContext.request.contextPath}/Basket?identifier=${liste.getIdentifier()}"  title="Commencer ses courses"><i class="material-icons">shopping_cart</i> </a>
                                <a href="${pageContext.request.contextPath}/deleteListe?identifier=${liste.getIdentifier()}" class=" text-danger" title="Supprimer"><i class="material-icons">delete</i></a>
                            </div>

                        </li>
                    </core:forEach>
                </ul>
            </core:when>
            <core:otherwise>
            <p>Pas de liste disponible.<p>
            </core:otherwise>
            </core:choose>
        </section>

        <p><a href="${pageContext.request.contextPath}/display" title="Creer une nouvelle liste"><i class="material-icons">note_add</i></a></p>
        <hr />
        <%@include file="fragments/footer.jsp"%>
    </body>
</html>
