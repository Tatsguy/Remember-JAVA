package Servlets;

import BD.Conexion;
import Modelos.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "UpdateUser", urlPatterns = {"/UpdateUser"})
public class UpdateUser extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conexion con = new Conexion();
        Usuario u = new Usuario();
        u.setId_user(Integer.parseInt(request.getParameter("id")));
        u.setUsuario(request.getParameter("usuario"));
        u.setEmail(request.getParameter("email"));
        u.setNombre(request.getParameter("nombre"));
        u.setPassword(request.getParameter("password"));
        con.modificarUsuario(u);
        response.sendRedirect("notas.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
