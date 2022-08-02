package Servlets;

import BD.Conexion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BorrarUsuario", urlPatterns = {"/BorrarUsuario"})
public class BorrarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conexion con = new Conexion();
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("id") == null) {
            response.sendRedirect("login.jsp");
        } else {
            int rol = con.getRol(Integer.parseInt(sesion.getAttribute("id").toString()));
            // Si es un administrador
            if (rol == 1) {
                con.borrarUsuario(Integer.parseInt(request.getParameter("id")));
                //Si el administrador se elimina a si mismo
                if (Integer.parseInt(request.getParameter("id")) == Integer.parseInt(sesion.getAttribute("id").toString()))
                    response.sendRedirect("cerrar-sesion.jsp");
                else
                    response.sendRedirect("administrador.jsp");
            } else {
                //El usuario comun solo se puede eliminar a si mismo
                con.borrarUsuario(Integer.parseInt(sesion.getAttribute("id").toString()));
                response.sendRedirect("cerrar-sesion.jsp");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
