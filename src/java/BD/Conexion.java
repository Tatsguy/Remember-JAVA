package BD;

import Modelos.Nota;
import Modelos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

    Connection con;
    Statement smt;
    ResultSet rs;

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/remember", "root", "JevG0304");
            System.out.println("Conexión Realizada");
            smt = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error " + ex);
        }
    }

    public void desconectar() {
        try {
            con.close();
            System.out.println("Conexión Cerrada");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
    }

    public int insertarUsuario(Usuario user) {
        try {
            String sql = "INSERT INTO users VALUES(null,'"
                    + user.getNombre() + "','"
                    + user.getUsuario() + "','"
                    + user.getEmail() + "','"
                    + user.getPassword() + "')";
            conectar();
            int regs = smt.executeUpdate(sql);
            System.out.println("Inserción realizada");
            desconectar();
            return regs;
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
            return 0;
        }
    }

    public List<Usuario> mostrarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            conectar();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId_user(rs.getInt("id_user"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("contraseña"));
                lista.add(u);
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al extraer datos " + ex + " " + sql);
            lista = null;
        }
        return lista;
    }

    public int buscarUser(String user, String password) {
        String sql = "SELECT id_user FROM users WHERE usuario='" + user + "' AND contraseña='" + password + "'";
        int the_id = 0;
        try {
            conectar();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                the_id = rs.getInt("id_user");
                System.out.println("El id es" + the_id);
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al extraer datos" + ex + " " + sql);
        }
        return the_id;
    }

    public Usuario buscarId(int id) {
        String sql = "SELECT * FROM users WHERE id_user=" + id;
        Usuario u = new Usuario();
        try {
            conectar();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                u.setId_user(rs.getInt("id_user"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("contraseña"));
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al extraer datos" + ex + " " + sql);
            u = null;
        }
        return u;
    }

    public List<Nota> getNotasUser(int id) {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM notes WHERE id_user="+id;
        try {
            conectar();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                Nota u = new Nota();
                u.setId_note(rs.getInt("id_note"));
                u.setContenido(rs.getString("contenido"));
                u.setColor(rs.getString("color"));
                u.setFondo(rs.getString("fondo"));
                lista.add(u);
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al extraer datos " + ex + " " + sql);
            lista = null;
        }
        return lista;
    }
    
    public List<Nota> getNotas() {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM notes";
        try {
            conectar();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                Nota u = new Nota();
                u.setId_note(rs.getInt("id_note"));
                u.setContenido(rs.getString("contenido"));
                u.setId_user(rs.getInt("id_user"));
                lista.add(u);
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al extraer datos " + ex + " " + sql);
            lista = null;
        }
        return lista;
    }
    
     public int modificarUsuario(Usuario user){
        try {
            String sql = "UPDATE users SET "
                    +"usuario='"+user.getUsuario()+"',"
                    +"email='"+user.getEmail()+"',"
                    +"nombre='"+user.getNombre()+"',"
                    +"contraseña='"+user.getPassword()+"' "
                    +"WHERE id_user='"+user.getId_user()+"'";
            conectar();
            int regs = smt.executeUpdate(sql);
            desconectar();
            System.out.println("Modificación realizada");
            return regs;
        } catch (SQLException ex) {
            System.out.println("Error al modificar"+ex);
            return 0;
        }
    }
     
     public int borrarUsuario(int id){
        try {
            String sql = "DELETE FROM users WHERE id_user="+id;
            conectar();
            int regs = smt.executeUpdate(sql);
            desconectar();
            System.out.println("Se elimino el registro");
            return regs;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar "+ex);
            return 0;
        }
    }
     
     public int borrarNota(int id){
        try {
            String sql = "DELETE FROM notes WHERE id_note="+id;
            conectar();
            int regs = smt.executeUpdate(sql);
            desconectar();
            System.out.println("Se elimino el registro");
            return regs;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar "+ex);
            return 0;
        }
    }
}
