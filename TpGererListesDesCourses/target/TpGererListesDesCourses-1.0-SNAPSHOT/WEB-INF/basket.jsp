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
    <jsp:param name="title" value="Details de liste"/>
  </jsp:include>
  <body>
    <jsp:include page="fragments/header.jsp">
      <jsp:param name="h1" value="Courses - Listes de courses"/>
      <jsp:param name="h2" value="Details de la liste"/>
    </jsp:include>
    <%@include file="./fragments/errors.jsp"%>

    <section>
      <core:if test="${!empty liste}">
        <h3>Liste : ${liste.getName()}</h3>
        <input type="hidden" value="${liste.getIdentifier()}" name="identifier"/>
        <input type="hidden" value="${liste.getName()}" name="name"/>
      </core:if>
      <core:choose>
        <core:when test="${!empty articles}">

            <ul>
              <core:forEach var="article" items="${articles}">
                <li>
                  <div>

                    <form action="${pageContext.request.contextPath}/Basket" id="${article.getIdentifier()}" method="post">
                      <label class="custom-color custom-checkbox">
                        <input type="hidden" name="identifier" value="${liste.identifier}/">
                        <input type="hidden" name="identifierArticle" value="${article.getIdentifier()}">

                        <span class="custom-control-indicator"></span>
                        <span class="custom-control-description">${article.name}</span>
                      </label>




                    </form>

                  </div>

                </li>
              </core:forEach>
              <button class="btn btn-link" type="submit"><i class="material-icons">check_circle</i> </button>
            </ul>


        </core:when>

        <core:otherwise>
          <p>Aucun article choisi</p>
        </core:otherwise>
      </core:choose>
    </section>
    <p>
      <a class="btn" href="${pageContext.request.contextPath}/listes"><i class="material-icons">arrow_back</i></a>
      <a class="btn" href="${pageContext.request.contextPath}/Basket?identifier=${liste.getIdentifier()}&uncheckAll=${true}"><i class="material-icons">save</i></a>
    </p>
    <hr/>
    <%@include file="fragments/footer.jsp"%>

  </body>
</html>
