package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List <Usuario> listar (){
        String sql = "Select * from Usuario";
        List <Usuario> listaUsuario = new ArrayList <>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Usuario usuario = new Usuario ();
                usuario.setCodigoUsuario(rs.getInt("codigoUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setApellidoUsuario(rs.getString("apellidoUsuario"));
                usuario.setUsuarioLogin(rs.getString("usuarioLogin"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setCodigoTipoUsuario(rs.getInt("codigoTipoUsuario"));
                listaUsuario.add (usuario);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaUsuario;
    }
    
    public int agregar (Usuario usuario){
        String sql = "insert into Usuario (codigousuario, nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) values (?, ?, ?, ?, ?, ?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setInt(1, usuario.getCodigoUsuario());
            ps.setString(2, usuario.getNombreUsuario());
            ps.setString(3, usuario.getApellidoUsuario());
            ps.setString(4, usuario.getUsuarioLogin());
            ps.setString (5, usuario.getContrasena());
            ps.setInt(6, usuario.getCodigoTipoUsuario());
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public Usuario listarCodigoUsuario (int id){
        Usuario usuario = new Usuario();
        String sql = "Select * from Usuario where codigoUsuario = " + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                usuario.setCodigoUsuario (rs.getInt("codigoUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setApellidoUsuario(rs.getString("apellidoUsuario"));
                usuario.setUsuarioLogin(rs.getString("usuarioLogin"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setCodigoTipoUsuario(rs.getInt("codigoTipoUsuario"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
    
    public int actualizar (Usuario usuario){
        String sql = "update Usuario set codigoUsuario = ?, nombreUsuario = ?, apellidoUsuario = ?, usuarioLogin = ?, contrasena = ?, codigoTipoUsuario = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setInt(1, usuario.getCodigoUsuario());
            ps.setString(2, usuario.getNombreUsuario());
            ps.setString(3, usuario.getApellidoUsuario());
            ps.setString(4, usuario.getUsuarioLogin());
            ps.setString(5, usuario.getContrasena());
            ps.setInt(6, usuario.getCodigoTipoUsuario());
            ps.executeUpdate();
        }catch (Exception e ){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar (int id ){
        String sql = "delete from Usuario where codigoUsuario = " + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
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
