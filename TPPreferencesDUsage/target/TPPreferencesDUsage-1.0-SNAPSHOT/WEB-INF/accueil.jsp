<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--<%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 28/05/2021
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%
            String couleur = "noir";
            if(session.getAttribute("couleurs") != null){
                couleur= (String) session.getAttribute("couleurs");
            }
        %>
        <% List<String> colors = (ArrayList<String>) application.getAttribute("couleurs"); %>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/<%=couleur%>.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
        <title>Choix des preferences</title>
    </head>

    <body>
        <h1>Merci de chosir votre couleur de preference</h1>
        <form action="<%=request.getContextPath()%>/ServletCouleurUtilisateurPref" method="post" >
            <label for="getColor" class="form-label">Couleur</label>
            <select name="getColor" class="form-select" id="getColor">
                <option selected>Merci de choisir votre couleur de preference</option>
                <option value="<%= colors.get(0) %>"><%= colors.get(0) %></option>
                <option value="<%= colors.get(1) %>"><%= colors.get(1) %></option>
                <option value="<%= colors.get(2) %>"><%= colors.get(2) %></option>
                <option value="<%= colors.get(3) %>"><%= colors.get(3) %></option>
            </select>


            <button type="submit"  class="btn btn-primary" ">Valider</button>



        </form>

        <%
            Cookie cookieNbAcCookie = (Cookie)request.getAttribute("cookieNbAcces");
        %>


        <p>Tu as visite le site <%=cookieNbAcCookie.getValue() %> fois !</p>


        <a href="<%= request.getContextPath()%>">retour</a>
    </body>
</html>