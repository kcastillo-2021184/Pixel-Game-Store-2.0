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
    
    public List <Compras> listar(){
        String sql = "Select * form compras";
        List<Compras> listaCompras = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Compras compra = new Compras();
                compra.setNumeroDocumento(rs.getInt("numeroDocumento"));
                compra.setFechaDocumento(rs.getDate("fechaDocumento"));
                compra.setDescripcion(rs.getString("descripcion"));
                compra.setTotalDocumento(rs.getDouble("totalDocumento"));
                listaCompras.add(compra);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaCompras;
    }
        
    public int agregar(Compras compra){
        String sql = "Insert into compras(numeroDocumento, fechaDocumento, descripcion, totalDocumento) values(?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, (Date) compra.getFechaDocumento());
            ps.setString(2, compra.getDescripcion());
            ps.setDouble(3, compra.getTotalDocumento());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
     
    public Compras listarNumeroDocumento(int id){
        Compras compra = new Compras();
        String sql = "select * from compras where numeroDocumento = " + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                compra.setNumeroDocumento(rs.getInt("numeroDocumento"));
                compra.setFechaDocumento(rs.getDate("fechaDocumento"));
                compra.setDescripcion(rs.getString("descripcion"));
                compra.setTotalDocumento(rs.getDouble("totalDocumento"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return compra;
    }
    
    public int actualizar(Compras compra){
        String sql = "update compras "
                + "set numeroDocumento = ?, "
                + "set fechaDocumento = ?, "
                + "set descripcion = ?, "
                + "set totalDocumento = ?, ";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, (Date) compra.getFechaDocumento());
            ps.setString(2, compra.getDescripcion());
            ps.setDouble(3, compra.getTotalDocumento());
            ps.setInt(4, compra.getNumeroDocumento());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "delete from compras where numeroDocumento = " +id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
        
  }
