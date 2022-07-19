<%@page import="Modelos.Nota"%>
<%@page import="java.util.List"%>
<%@page import="Modelos.Usuario"%>
<%@page import="BD.Conexion"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Remember">
        <meta name="copyright" content="Remember">
        <meta name="description" content="Interfaz de notas">
        <meta name="keywords" content="Notas, Remember, Recordatorios">

        <!--ESTILOS-->
        <link rel="shortcut icon" href="images/logoV.png" type="image/x-icon">
        <link rel="stylesheet" href="css/notes.css">
        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
        <title>Remember - Notas</title>

    </head>

    <body>
        <%
            Usuario user = new Usuario();
            Conexion con = new Conexion();
            int idUser = 0;
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("id") == null) {
                response.sendRedirect("login.jsp");
            } else {
                idUser = Integer.parseInt(sesion.getAttribute("id").toString());
                user = con.buscarId(idUser);
            }
        %>
        <div class="notes">
            <!--SIDEBAR--------------------------------------------------------------->
            <div class="notes__sidebar">
                <div class="user">
                    <div class="user-avatar"></div>
                    <div id="nickname">
                        <p id="user">Bienvenido <%=user.getUsuario()%></p>
                        <p class="tool-name" id="updateUser">Ver perfil</p>
                    </div>
                </div>
                <div class="tools">
                    <button class="closeIcon">
                        <i class="ri-close-line"></i>
                    </button>
                    <div class="tool">
                        <a href="index.html"><i class="ri-home-line"></i>
                            <p class="tool-name">Ir a inicio</p>
                        </a>
                    </div>
                    <div class="tool">
                        <a href="cerrar-sesion.jsp"><i class="ri-door-open-line"></i>
                            <p class="tool-name">Cerrar sesión</p>
                        </a>
                    </div>
                </div>
                <button class="hamburger menuIcon">
                    <i class="ri-menu-line"></i>
                </button>
                <div class="widget">
                    <div class="fecha">
                        <p id="diaSemana" class="diaSemana"></p>
                        <p id="dia" class="dia"></p>
                        <p>de </p>
                        <p id="mes" class="mes"></p>
                        <p>del </p>
                        <p id="year" class="year"></p>
                    </div>
                    <div class="reloj">
                        <p id="horas" class="horas"></p>
                        <p>:</p>
                        <p id="minutos" class="minutos"></p>
                        <p>:</p>
                        <div class="caja-segundos">
                            <p class="ampm" id="ampm"></p>
                            <p class="segundos" id="segundos"></p>
                        </div>
                    </div>
                </div>
            </div>

            <!--CREACIÓN DE NOTAS----------------------------------------------------->
            <div id="app">
                <%
                    List<Nota> lista = con.getNotasUser(idUser);
                    for (int i = 0; i < lista.size(); i++) {
                %>
                <div class="note-container" style="background-color: <%=lista.get(i).getFondo()%>;" id="<%=lista.get(i).getId_note()%>">
                    <ul class="funciones-nota">
                        <li class="icono-gotita">
                            <img src="./images/notas/gotita-icono.jpg">
                        </li>
                        <li class="icono-reloj">
                            <img src="./images/notas/reloj-icono.png">
                        </li>
                    </ul>
                    <textarea class="note" placeholder="Nota vacia" style="color: <%=lista.get(i).getColor()%>; background-color: <%=lista.get(i).getFondo()%>;"><%=lista.get(i).getContenido()%></textarea>
                </div>
                <%
                    }
                %>
                <button class="add-note" type="button">+</button>
            </div>

            <!--RELOJ---------------------------------------------------------------->
            <div class="reminder-form-container container" id="reminder-form-container">
                <div class="form-container-inner">
                    <form action="" class="form">
                        <p id="instruction">Selecciona la fecha y hora</p>
                        <input class="form-input" type="datetime-local">
                        <button class="btn form-input" type="submit">
                            Elegir
                        </button>
                    </form>
                    <span class="form-note">Or press Esc to close</span>
                </div>

                <button class="btn form-close-btn place-items-center" id="form-close-btn">
                    <i class="ri-close-line"></i>
                </button>
            </div>
            <!--Editar Perfil---------------------------------------------------------------->
            <div class="update-form-container container" id="update-form-container">
                <div class="form-container-inner" style="background-color: white; width: 50%; padding: 15px; border-radius: 10px">
                    <form action="UpdateUser" id="userForm" method="post" class="form" style="color:black;">
                        <p class="instruction">Foto:</p>
                        <input type="file" id="myfile" name="myfile">
                        <input class="form-input" name="id" type="text" hidden="true" value="<%=user.getId_user()%>">
                        <p class="instruction">Usuario:</p>
                        <input class="form-input" name="usuario" type="text" value="<%=user.getUsuario()%>">
                        <p class="instruction">Email:</p>
                        <input class="form-input" name="email" type="email" value="<%=user.getEmail()%>">
                        <p class="instruction">Nombre:</p>
                        <input class="form-input" name="nombre" type="text" value="<%=user.getNombre()%>">
                        <p class="instruction">Password:</p>
                        <input class="form-input" type="password" name="password" value="<%=user.getPassword()%>">
                        <button class="btn form-input" type="submit" style="cursor: pointer;">Modificar Cuenta</button>
                        <a class="btn form-input" style="background-color: red; display: block;" href="BorrarUsuario?id=<%=user.getId_user()%>">Eliminar Cuenta</a>
                    </form>
                    <span class="form-note">Or press Esc to close</span>
                </div>

                <button class="btn form-close-btn place-items-center" id="update-close-btn">
                    <i class="ri-close-line"></i>
                </button>
            </div>
        </div>
        <script src="js/notes.js"></script>
        <script src="js/reloj.js"></script>
    </body>

</html>