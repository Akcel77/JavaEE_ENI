<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <title>Suivi des repas</title>
    </head>
    <body>
        <img src="${pageContext.request.contextPath}/ressource/reaps.png" alt="">
        <h1>Suivi des repas </h1>
        <div class="user-action">
            <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ServletAjouterRepas';" class="btn btn-danger m-2" value="Ajout d'un nouveau repas">
            <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ServletVueRepas';" class="btn btn-primary m-2" value="Visualiser les repas">
        </div>
    </body>
</html>