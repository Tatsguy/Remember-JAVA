<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <meta name="author" content="Remember">
        <meta name="copyright" content="Remember">
        <meta name="description" content="Pantalla de login de la página Remember"> 
        <meta name="keywords" content="Login, Iniciar sesión">

        <!--ESTILOS-->
        <link rel="stylesheet" href="css/login.css">
        <link rel="shortcut icon" href="images/logoV.png" type="image/x-icon">
        <title>Remember - Inicar Sesi�n</title>
    </head>
    <body>
        <%
        String claseData;
        String datosIncorrectos = request.getParameter("datosIncorrectos");
        if(datosIncorrectos==null){
            claseData="noVisible";
        }else{
            claseData="siVisible";
        }
        %>
        <!--INICIAR SESI�N-->
        <div class="container">
            <div class="row">
                <a href="index.html" id="regresar">&lt;</a>
                <div class="columna">
                    <form action="Login" method="post" name="myForm" onsubmit="return validate();">      
                        <img src="images/index/Logo.png" alt="">
                        <div class="usuario-caja">
                            <input type="text" name="Usuario" placeholder="Ingrese su usuario" id="usuario"> <br>
                            <span class="noVisible" id="spanUsuario">Ingrese su usuario</span>
                        </div>

                        <div class="contrase�a-caja">
                            <input type="password" name="Password" placeholder="Ingrese su contrase�a" id="password"> <br>
                            <span class="noVisible" id="spanPassword">Ingrese su contrase�a</span>
                        </div>

                        <div class="boton-caja">
                            <button type="submit">Ingresar</button>
                        </div>
                        <span class="<%=claseData%>" style="margin: auto; right: 0; left: 0; bottom: 0;" id="spanData">Los datos no coinciden con ningun registro.</span>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/login.js"></script>
    </body>
</html>