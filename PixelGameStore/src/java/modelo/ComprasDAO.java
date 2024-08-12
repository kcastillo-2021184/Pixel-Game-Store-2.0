package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Compras;

public class ComprasDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List <Compras> Listar(){
        String sql = "select * from Compras";
        List<Compras> listaCompras = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Compras com = new Compras();
                com.setNumeroDocumento(rs.getInt(1));
                com.setFechaDocumento(rs.getString(2));
                com.setDescripcion(rs.getString(3));
                com.setTotalDocumento(rs.getDouble(4));
                listaCompras.add(com);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaCompras;
    }
        
    public int agregar(Compras compra){
        String sql = "Insert into Compras(numeroDocumento, fechaDocumento, descripcion) values (?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, compra.getNumeroDocumento());
            ps.setString(2, compra.getFechaDocumento());
            ps.setString(3, compra.getDescripcion());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
     
    public Compras ListarNumeroDocumento(int id){
        Compras com = new Compras();
        String sql = "Select * from Compras where numeroDocumento ="+ id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                com.setNumeroDocumento(rs.getInt(1));
                com.setFechaDocumento(rs.getString(2));
                com.setDescripcion(rs.getString(3));
                com.setTotalDocumento(rs.getDouble(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return com;
    }
    
    public int actualizar(Compras com){
        String sql = "Update Compras set fechaDocumento = ?, descripcion = ?, totalDocumento = ? where numeroDocumento = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, com.getFechaDocumento());
            ps.setString(2, com.getDescripcion());
            ps.setDouble(3, com.getTotalDocumento());
            ps.setInt(4, com.getNumeroDocumento());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "delete from Compras where numeroDocumento = " +id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
        
  }