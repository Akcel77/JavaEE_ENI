<%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 01/06/2021
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<%--Jsp Servant a afficher les messages d'erreurs --%>

<section>
  <core:if test="${!empty errors}">
    <div>
      <p>Une ou plusieurs erreurs sont survenues :</p>
      <ul>
        <core:forEach var="code" items="${errors}">
          <li>${Reader.getMessageError(code)}</li>
        </core:forEach>
      </ul>
    </div>
  </core:if>
</section>