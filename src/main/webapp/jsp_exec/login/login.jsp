<%--
  User: Ròmulo Galindo
  Date: 12/12/18
  Time: 17:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iniciar Sesión - Acceso Corp.</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <%--<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="w3-theme-l4">
<div style="min-width:200px;margin: 10% auto;">

    <div class="w3-container w3-content">
        <div class="w3-container w3-blue">
            <h2>Iniciar sesión</h2>
        </div>

        <form class="w3-container w3-card-4" action="/action_page.php" method="post">
            <div class="w3-panel w3-red w3-round-xxlarge">
                <p>Error iniciando sesión </p>
            </div>
            <p>Ingrese sus credenciales</p>
            <p>
                <label class="w3-text-blue"><b>Usario</b></label>
                <input class="w3-input w3-border" name="username" type="text"></p>
            <p>
                <label class="w3-text-blue"><b>Contraseña</b></label>
                <input class="w3-input w3-border" name="password" type="password"></p>
            <p>
                <button class="w3-btn w3-blue">Ingresar</button>
            </p>
        </form>
    </div>
</div>
</body>
</html>
