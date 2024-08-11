package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FacturaDAO {
    Conexion cn = new Conexion ();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List <Factura> listar(){
        
        String sql = "Select * from Factura";
        List <Factura> listaFactura = new ArrayList <>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Factura factura = new Factura();
                factura.setNumeroFactura(rs.getInt("numeroFactura"));
                factura.setEstado(rs.getString("estado"));
                factura.setTotalFactura(rs.getString("totalFactura"));
                factura.setFechaFactura(rs.getDate("fechaFactura"));
                factura.setCodigoCliente(rs.getInt("codigoCliente"));
                factura.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                listaFactura.add(factura);
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaFactura;
    }
    
    
    
    public int agregar (Factura factura){
        String sql = "insert into Factura(numeroFactura, estado, totalFactura, fechaFactura, codigoCliente, codigoEmpleado) values (?, ?, ?, ?, ?, ?, ?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, factura.getNumeroFactura());
            ps.setString(2, factura.getEstado());
            ps.setString(3, factura.getTotalFactura());
            ps.setDate(4, (Date) factura.getFechaFactura());
            ps.setInt(5, factura.getCodigoCliente());
            ps.setInt(6, factura.getCodigoEmpleado());
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return resp;
    }
    
    
    public Factura listarNumeroFactura (int id){
        Factura factura = new Factura ();
        String sql = "Select * from Factura  where numeroFactura = " + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                factura.setNumeroFactura(rs.getInt("numeroFactura"));
                factura.setEstado(rs.getString("estado"));
                factura.setTotalFactura(rs.getString("totalFactura"));
                factura.setFechaFactura(rs.getDate("fechaFactura"));
                factura.setCodigoCliente(rs.getInt("codigoCliente"));
                factura.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return factura;
    }
    
    
    
    public int actualizar (Factura factura){
        String sql = "update Factura set numeroFactura=?, estado=?, totalFactura=?, fechaFactura=?, codigoCliente=?, codigoEmpleado=?";
        try{
            con = cn.Conexion();
            ps= con.prepareStatement(sql);
            rs= ps.executeQuery();
            ps.setInt(1, factura.getNumeroFactura());
            ps.setString(2, factura.getEstado());
            ps.setString(3, factura.getTotalFactura());
            ps.setDate(4, (Date) factura.getFechaFactura());
            ps.setInt(5, factura.getCodigoCliente());
            ps.setInt(6, factura.getCodigoEmpleado());
            ps.executeUpdate();
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    
    
    public void eliminar (int id){
        String sql = "delete from Factura where numeroFacura = " + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
