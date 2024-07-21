package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UsuarioDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public Usuario validar(String user, String contrasena){
        Usuario usuario = new Usuario();
        String  sql = "select * from usuario where usuarioLogin = ? and contrasena = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            while(rs.next()){
                usuario.setCodigoTipoUsuario(rs.getInt("codigoUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setApellidoUsuario(rs.getString("apellidoUsuario"));
                usuario.setUsuarioLogin(rs.getString("usuarioLogin"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setCodigoTipoUsuario(rs.getInt("codigoTipoUsuario"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
    
    
}
