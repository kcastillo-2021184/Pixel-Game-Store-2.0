package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleFacturaDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public DetalleFactura validar(double precioUnitario, int cantidad){
        DetalleFactura detalleFactura = new DetalleFactura();
        String sql = "Select * from detalleFacutra where precioUnitario = ? and cantidad = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, precioUnitario);
            ps.setInt(2, cantidad);
            rs = ps.executeQuery();
            rs = ps.executeQuery();
            while(rs.next()){
                detalleFactura.setCodigoDetalleFactura(rs.getInt("codigoDetalleFactura"));
                detalleFactura.setPrecioUnitario(rs.getDouble("precioUnitario"));
                detalleFactura.setCantidad(rs.getInt("cantidad"));
                detalleFactura.setNumeroFactura(rs.getInt("numeroFactura"));
                detalleFactura.setCodigoProducto(rs.getString("codigoProducto"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return detalleFactura;
    }
    
    //Método para listar todos los detalles de factura
    public List <DetalleFactura> listar(){
        String sql = "Select * from detalleFactura";
        List<DetalleFactura> listaDetalleFactura = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DetalleFactura detalleFactura = new DetalleFactura();
                detalleFactura.setCodigoDetalleFactura(rs.getInt("codigoDetalleFactura"));
                detalleFactura.setPrecioUnitario(rs.getDouble("precioUnitario"));
                detalleFactura.setCantidad(rs.getInt("cantidad"));
                detalleFactura.setNumeroFactura(rs.getInt("numeroFactura"));
                listaDetalleFactura.add(detalleFactura);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDetalleFactura;
    }
    
    //Método para agregar todos los detalles fatura
    public int agregar(DetalleFactura detalle){
        String sql = "Inser into detalleFactura(precioUnitario, cantidad, numeroFactura, codigoProducto) values (?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, detalle.getPrecioUnitario());
            ps.setInt(2, detalle.getCantidad());
            ps.setInt(3, detalle.getNumeroFactura());
            ps.setString(4, detalle.getCodigoProducto());
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //Método para actualizar un detalle de factura
    public int actualizar(DetalleFactura detalle){
        String sql = "Update detalleFactura set precioUnitario = ?, cantidad = ?, numeroFactura = ?, codigoProducto = ? where codigoDetalleFactura = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, detalle.getPrecioUnitario());
            ps.setInt(2, detalle.getCantidad());
            ps.setInt(3, detalle.getNumeroFactura());
            ps.setString(4, detalle.getCodigoProducto());
            ps.setInt(5, detalle.getCodigoDetalleFactura());
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //Método para eliminar detalle de factura
    public void eliminar(int id){
        String sql = "Delete from detalleFactura where codigoDetalleFactura = "+ id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}