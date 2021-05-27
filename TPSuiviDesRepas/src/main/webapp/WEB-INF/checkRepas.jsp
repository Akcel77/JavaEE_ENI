<%@ page import="com.diagneaxel.TP5suiviDesRepas.bo.Repas" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.diagneaxel.TP5suiviDesRepas.bo.Aliments" %><%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 27/05/2021
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Vue des repas</title>
</head>
<body>
<h1 style="margin: 5rem auto;">Historique des repas</h1>

    <div style="width: 60%; margin: 5rem auto;" class="table-block">
        <table class="table table-warning">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Date</th>
                <th scope="col">Heure</th>
                <th scope="col">Aliments</th>
            </tr>
            </thead>

            <tbody>
            <% for (Repas r : (ArrayList<Repas>) request.getAttribute("repas")) { %>
            <tr>
                <th scope="row"><%= r.getId() %></th>
                <td><%= r.getDate() %></td>
                <td><%= r.getHeure() %></td>
                <td><% for (Aliments alim : r.getAliments()) { %>
                    <%= alim.getAliment() + " " %>
                    <% } %></td>
            </tr>
            <% } %>
            </tbody>
        </table>

    </div>
    <div class="back-button" style="margin: 1rem auto; text-align: center; display: block">
        <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ServletAjouterRepas';" class="btn btn-success mb-3" value="Ajout Repas">
        <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>';" class="btn btn-primary" value="Acceuil">
    </div>
</body>
</html>
