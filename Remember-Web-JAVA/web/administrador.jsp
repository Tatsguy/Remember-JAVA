<%@page import="Modelos.Nota"%>
<%@page import="Modelos.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="BD.Conexion"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Remember Team">
        <meta name="copyright" content="Remember">
        <meta name="description" content="Interfaz de administración de usuarios">
        <meta name="keywords" content="Administrador, notas, Remember">
        <link rel="shortcut icon" href="images/logoV.png" type="image/x-icon">
        <title>Remember - Administrador</title>
        <link rel="stylesheet" href="css/administrador.css">
        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
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

            <!--SIDEBAR-------------------------------------------------->
            <div class="notes__sidebar">
                <div class="user">
                    <div class="user-avatar"></div>
                    <div id="nickname">
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
                            <p class="tool-name">Cerrar sesi�n</p>
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

            <!--TABLAS---------------------------------------------------->
            <div id="app-admin">
                <h1>Bienvenido  <%=user.getUsuario()%></h1>
                <div class="administrator">
                    <div class="table-container">
                        <h2>Usuarios</h2>
                        <div class="table-scroll">
                            <table class="content-table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>NOMBRE</th>
                                        <th>USUARIO</th>
                                        <th>E-MAIL</th>
                                        <th>CONTRASE�A</th>
                                        <th>ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <%
                                        List<Usuario> lista = con.mostrarUsuarios();
                                        for (int i = 0; i < lista.size(); i++) {
                                    %>
                                    <tr data-href="index.html">
                                        <td><%=lista.get(i).getId_user()%></td>
                                        <td><%=lista.get(i).getNombre()%></td>
                                        <td><%=lista.get(i).getUsuario()%></td>
                                        <td><%=lista.get(i).getEmail()%></td>
                                        <td><%=lista.get(i).getPassword()%></td>
                                        <td><a href="BorrarUsuario?id=<%=lista.get(i).getId_user()%>&admin=true">borrar</a></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="table-container">
                        <h2>Notas</h2>
                        <div class="table-scroll">
                            <table class="content-table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>CONTENIDO</th>
                                        <th>USER-ID</th>
                                        <th>ACCIONES</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Nota> listaNota = con.getNotas();
                                        for (int i = 0; i < listaNota.size(); i++) {
                                    %>
                                    <tr data-href="index.html">
                                        <td><%=listaNota.get(i).getId_note()%></td>
                                        <td><%=listaNota.get(i).getContenido()%></td>
                                        <td><%=listaNota.get(i).getId_user()%></td>
                                        <td><a href="BorrarNota?id=<%=listaNota.get(i).getId_note()%>">borrar</a></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="admin-tools">
                    <button class="admin-btn">A�adir Usuario</button>
                    <button class="admin-btn">Modificar Usuario</button>
                </div>
            </div>
        </div>
        <script src="js/reloj.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", ()=>{
                const rows = document.querySelectorAll("tr[data-href]");
                rows.forEach(row =>{
                    row.addEventListener("click", ()=>{
                        window.location.href = row.dataset.href;
                    });
                });
            });
        </script>
    </body>

</html>