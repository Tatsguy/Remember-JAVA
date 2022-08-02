package Servlets;

import BD.Conexion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("Usuario");
        String password = request.getParameter("Password");

        Conexion conexion = new Conexion();
        int id = conexion.buscarUser(usuario, password);
        if (id < 1) {
            response.sendRedirect("login.jsp?datosIncorrectos=Si");
        } else {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("id", id);
            int rol = conexion.getRol(id);
            if (rol==1) {
                response.sendRedirect("administrador.jsp");
            } else {
                response.sendRedirect("notas.jsp");
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
