package Servlets;

import BD.Conexion;
import Modelos.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdateUser", urlPatterns = {"/UpdateUser"})
public class UpdateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conexion con = new Conexion();
        Usuario u = new Usuario();
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("id") == null) {
            response.sendRedirect("login.jsp");
        } else {
            u.setId_user(Integer.parseInt(request.getParameter("id")));
            u.setUsuario(request.getParameter("usuario"));
            u.setEmail(request.getParameter("email"));
            u.setNombre(request.getParameter("nombre"));
            u.setPassword(request.getParameter("password"));
            //Este if define si la modificación la esta realizando un administrador o un usuario
            if(request.getParameter("rol")==null)
                u.setRol(2);
            else
                u.setRol(Integer.parseInt(request.getParameter("rol")));
            con.modificarUsuario(u);
            int rol = con.getRol(Integer.parseInt(sesion.getAttribute("id").toString()));
            if (rol == 1) {
                response.sendRedirect("administrador.jsp");
            } else {
                response.sendRedirect("notas.jsp");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
