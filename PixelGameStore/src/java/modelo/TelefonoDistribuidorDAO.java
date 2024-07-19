package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TelefonoDistribuidorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List listar(){
        String sql = "select * from TelefonoDistribuidor";
        List<TelefonoDistribuidor> listaTelefonoDistribuidor = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                TelefonoDistribuidor td = new TelefonoDistribuidor();
                td.setCodigoTelefonoDistribuidor(rs.getInt(1));
                td.setNumeroPrincipal(rs.getString(2));
                td.setNumeroSecundario(rs.getString(3));
                td.setObservaciones(rs.getString(4));
                td.setCodigoDistribuidor(rs.getInt(5));
                listaTelefonoDistribuidor.add(td);
            }        
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaTelefonoDistribuidor;
    }
    
    public int agregar(TelefonoDistribuidor td){
        String sql = "inset into TelefonoDistribuidor (numeroPrincipal, numeroSecundario, observaciones) values (?,?,?);";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, td.getNumeroPrincipal());
            ps.setString(2, td.getNumeroSecundario());
            ps.setString(3, td.getObservaciones());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    
    public TelefonoDistribuidor listarCodigoTelefonoDistribuidor(int id){
        TelefonoDistribuidor td = new TelefonoDistribuidor();
        String sql = "select * from TelefonoDistribuidor where codigoTelefonoDistribuidor =" + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                td.setNumeroPrincipal(rs.getString(2));
                td.setNumeroSecundario(rs.getString(3));
                td.setObservaciones(rs.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return td;
    }
    
    public int actualizar(TelefonoDistribuidor td){
        String sql = "update TelefonoDistribuidor set numeroPrincipal = ?, numeroSecunario = ?, observaciones = ? where codigoTelefonoDistribuidor = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, td.getNumeroPrincipal());
            ps.setString(2, td.getNumeroSecundario());
            ps.setString(3, td.getObservaciones());
            ps.setInt(4, td.getCodigoTelefonoDistribuidor());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "delete from TelefonoDistribuidor where codigoTelefonoDistribuidor =" + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
