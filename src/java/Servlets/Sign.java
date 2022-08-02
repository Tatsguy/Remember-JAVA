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

@WebServlet(name = "Sign", urlPatterns = {"/Sign"})
public class Sign extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("sing-in.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int rol = 2;
        if (request.getParameter("rol") != null) {
            rol = Integer.parseInt(request.getParameter("rol"));
        }
        Conexion conexion = new Conexion();
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setUsuario(usuario);
        user.setEmail(email);
        user.setPassword(password);
        user.setRol(rol);
        conexion.insertarUsuario(user);
        if (request.getParameter("rol") != null) {
            response.sendRedirect("administrador.jsp");
        } else {
            int id = conexion.buscarUser(user.getUsuario(), user.getPassword());
            HttpSession sesion = request.getSession();
            sesion.setAttribute("id", id);
            response.sendRedirect("notas.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
