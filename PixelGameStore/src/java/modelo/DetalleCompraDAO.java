package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

  
public class DetalleCompraDAO {
    
   Conexion cn = new Conexion ();
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
   int resp;
   
   public List listar(){
       String sql = "Select * from DetalleCompra";
       List<DetalleCompra> listaDetalleCompra = new ArrayList<>();
       
       try{
           con = cn.Conexion();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
               DetalleCompra dc = new DetalleCompra();
               dc.setCodigoDetalleCompra(rs.getInt(1));
               dc.setCostoUnitario(rs.getFloat(2));
               dc.setCantidad(rs.getInt(3));
               dc.setCodigoProducto(rs.getString(4));
               dc.setNumeroDocumento(rs.getInt(5));
               listaDetalleCompra.add(dc);
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       
       
       return listaDetalleCompra;
   }
   
    public int agregar(DetalleCompra DC){
       String sql = "Insert into DetalleCompra(costoUnitario, cantidad, codigoProducto, numeroDocumento) values (?,?,?,?,?)";
       try{
           con = cn.Conexion();
           ps = con.prepareStatement(sql);
           ps.setFloat(1, DC.getCostoUnitario());
           ps.setInt(2, DC.getCantidad());
           ps.setString(3, DC.getCodigoProducto());
           ps.setInt(4, DC.getNumeroDocumento());
           ps.executeUpdate();
       }catch(Exception e){
           e.printStackTrace();
       }
            return resp;
    }
    
    public DetalleCompra listaCodigoDetalleCompra(int id){
        DetalleCompra dc = new DetalleCompra();
        String sql = "Select * from DetalleCompra where codigoDetalleCompra ="  +id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                dc.setCostoUnitario(rs.getFloat(2));
                dc.setCantidad(rs.getInt(3));
                dc.setCodigoProducto(rs.getString(4));
                dc.setNumeroDocumento(rs.getInt(5));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       return dc; 
    }
       
    public int actualizar(DetalleCompra dc){
        String sql = "Update DetalleCompra set costoUnitario = ?, cantidad = ? where codigoDetalleCompra = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setFloat(1, dc.getCostoUnitario());
            ps.setInt(2, dc.getCantidad());
        }catch(Exception e){
            e.printStackTrace();
        }
            
        
        return resp;
    }
    
    public void eliminar (int id){
        String sql = "delte from DetalleCompra where codigoDetalleCompra = " + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
}