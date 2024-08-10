package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoUsuarioDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    //CRUD
    
    //LISTAR
    public List listar(){
        String sql = "select * from TipoUsuario";
        List<TipoUsuario> listaTipoUsuario = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                TipoUsuario tU  = new TipoUsuario();
                tU.setCodigoTIpoUsuario(rs.getInt(1));
                tU.setTipoUsuario(rs.getString(2));
                listaTipoUsuario.add(tU);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaTipoUsuario;
    }
    
    //AGREGAR
    
    public int agregar (TipoUsuario tU){
        String sql = "insert into TipoUsuario (tipoUsuario) values(?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);;
            ps.setString(1, tU.getTipoUsuario());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //BUSCAR POR CÓDIGO 
    public TipoUsuario listarCodigoTipoUsuario(int id){
        TipoUsuario tU = new TipoUsuario();
        String sql = "select * from TipoUsuario where codigoTipoUsuario = "+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                tU.setTipoUsuario(rs.getString(2));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return tU;
    }
    
    //ACTUALIZAR
    public int actualizar (TipoUsuario tU){
        String sql = "update TipoUsuario set tipoUsuario =? where codigoTipoUsuario = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, tU.getTipoUsuario());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //ELIMNAR
    public void eliminar (int id){
        String sql = "delete from TipoUsuario where codigoTipoUsuario = "+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
