<%--
  Created by IntelliJ IDEA.
  User: AxelDiagne
  Date: 27/05/2021
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Ajout de Repas</title>
</head>
<body>
    <h1 class="m-5">Ajout de repas</h1>

    <main>
        <form action="<%=request.getContextPath()%>/ServletAjouterRepas" method="post">
            <div class="mb-5">
                <label for="date" class="form-label">Date</label>
                <input type="date" name="date" class="form-control" id="date">

            </div>
            <div class="mb-5">
                <label for="heure" class="form-label">Heure</label>
                <input type="time" name="heure" class="form-control" id="heure">
            </div>
            <div class="mb-5">
                <label for="repas" class="form-label">Repas</label>
                <input type="text" name="repas" class="form-control" id="repas">

            </div>

            <div class="back-button">
                <button style="text-align: center; width: 100%;" type="submit" class="btn btn-success my-3">Envoyer</button>

            </div>
            <input style="width: 20%; text-align: center" type="button" onclick="window.location.href = '<%= request.getContextPath() %>';" class="btn btn-danger my-5" value="Retour">
        </form>


    </main>

</body>
</html>
