package Servlets;

import BD.Conexion;
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
@WebServlet(name = "BorrarUsuario", urlPatterns = {"/BorrarUsuario"})
public class BorrarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conexion con = new Conexion();
        if (request.getParameter("id") == null) {
            response.sendRedirect("index.html");
        } else {
            con.borrarUsuario(Integer.parseInt(request.getParameter("id")));
            if(request.getParameter("admin")!= null)
                response.sendRedirect("administrador.jsp");
            else
                response.sendRedirect("cerrar-sesion.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
