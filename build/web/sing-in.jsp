<%@page import="Modelos.Encriptacion"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <meta name="author" content="Remember">
        <meta name="copyright" content="Remember">
        <meta name="description" content="Pantalla de registrarse"> 
        <meta name="keywords" content="sing-in, registrarse">

        <title>Remember - Registrarse</title>
        <link rel="stylesheet" href="css/sign.css">
        <link rel="shortcut icon" href="images/logoV.png" type="image/x-icon">
    </head>
    <body>

        <!--REGISTRARSE-->
        <div class="fondo">
            <div class="contenido">
                <a id="back-button" href="index.html">&lt;</a>
                <div class="imagen">
                    <img src="images/index/Logo.png" id="logo" alt="">
                </div>
                <div class="formulario">
                    <form action="Sign" method="post" name="myForm" onsubmit="return validate();">
                        <div id="form-container">
                            <div class="flex-column">
                                <input type="text" placeholder="Nombre(s)" name="nombre" id="Nombre">
                                <span class="noVisible" id="spanNombre">Ingrese su nombre(s)</span>

                                <input type="text" placeholder="Usuario" name="usuario" id="Usuario">
                                <span class="noVisible" id="spanUsuario">Ingrese un usuario</span>

                                <input type="email" placeholder="Direcci�n Email" name="email" id="Email">
                                <span class="noVisible" id="spanEmail">Ingrese su email</span>

                                <input type="password" placeholder="Crea una contrase�a" name="password" id="Password">
                                <span class="noVisible" id="spanPassword">Ingrese su contrase�a</span>

                                <input class="button" type="submit" value="Comenzar"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/sign.js"></script>
    </body>
</html>