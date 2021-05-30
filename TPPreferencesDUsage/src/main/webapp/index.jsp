<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body class="container">
<% List<String> test = (ArrayList<String>)  application.getAttribute("colors"); %>
<h1>Accueil</h1>
<form action="${pageContext.request.contextPath}/book/accueil.jsp" method="post">

    <input type="submit" value="valider">
</form>
<p>Tu as visite le site <%= request.getAttribute("countVisit")%> fois !</p>
<input type="button" onclick="window.location.href = '${pageContext.request.contextPath}/ServletCouleurUtilisateurPref';" class="btn btn-primary" value="Voir la page">
</body>
</html>