<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <%
            String couleur = "noir";
            if(session.getAttribute("couleurs") != null){
                couleur=(String)session.getAttribute("couleurs");
            }
        %>


        <title>Preference d'usage</title>
        <!-- CSS only -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/<%=couleur%>.css">


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
    </head>
    <body >

        <div class="box">
            <h1>Accueil</h1>
            <input type="button" onclick="window.location.href = '${pageContext.request.contextPath}/ServletCouleurUtilisateurPref';" class="btn btn-primary" value="Choisi ta couleur de preference">
        </div>





    </body>
</html>