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
        <p>Nom : ${liste.getName()}</p>
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
                        <input type="checkbox" class="custom-control-input" name="coche" onclick="document.forms['form${article.identifier}'].submit();" ${article.coche?"checked":""}/>
                        <span class="custom-control-indicator"></span>
                        <span class="custom-control-description">${article.name}</span>
                      </label>



                    </form>
                  </div>

                </li>
              </core:forEach>
            </ul>
            <button type="submit" > <i class="far fa-save"></i></button>

        </core:when>

        <core:otherwise>
          <p>Aucun article choisi</p>
        </core:otherwise>
      </core:choose>
    </section>
    <p>
      <a class="btn" href="${pageContext.request.contextPath}/listes"><i class="fas fa-arrow-alt-circle-left"></i></a>
      <a class="btn" href="${pageContext.request.contextPath}/Basket?identifier=${liste.getIdentifier()}&uncheckAll=${true}"><i class="fas fa-eraser"></i></a>
    </p>
    <hr/>
    <%@include file="fragments/footer.jsp"%>

  </body>
</html>
