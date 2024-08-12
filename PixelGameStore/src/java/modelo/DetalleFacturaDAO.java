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

    public DetalleFactura buscar(int codigoDetalleFactura) {
        DetalleFactura detalle = new DetalleFactura();
        String sql = "SELECT * FROM DetalleFactura WHERE codigoDetalleFactura = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoDetalleFactura);
            rs = ps.executeQuery();
            if (rs.next()) {
                detalle.setCodigoDetalleFactura(rs.getInt("codigoDetalleFactura"));
                detalle.setPrecioUnitario(rs.getDouble("precioUnitario"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setNumeroFactura(rs.getInt("numeroFactura"));
                detalle.setCodigoProducto(rs.getString("codigoProducto"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalle;
    }

    public List<DetalleFactura> listarDetalles() {
        String sql = "SELECT * FROM DetalleFactura";
        List<DetalleFactura> listaDetalles = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleFactura detalle = new DetalleFactura();
                detalle.setCodigoDetalleFactura(rs.getInt("codigoDetalleFactura"));
                detalle.setPrecioUnitario(rs.getDouble("precioUnitario"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setNumeroFactura(rs.getInt("numeroFactura"));
                detalle.setCodigoProducto(rs.getString("codigoProducto"));
                listaDetalles.add(detalle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDetalles;
    }

    public int agregar(DetalleFactura detalle) {
        String sql = "INSERT INTO DetalleFactura (cantidad, numeroFactura, codigoProducto) VALUES (?, ?, ?)";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, detalle.getCantidad());
            ps.setInt(2, detalle.getNumeroFactura());
            ps.setString(3, detalle.getCodigoProducto());
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int actualizar(DetalleFactura detalle) {
        String sql = "UPDATE DetalleFactura SET precioUnitario = ?, cantidad = ?, numeroFactura = ?, codigoProducto = ? WHERE codigoDetalleFactura = ?";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, detalle.getPrecioUnitario());
            ps.setInt(2, detalle.getCantidad());
            ps.setInt(3, detalle.getNumeroFactura());
            ps.setString(4, detalle.getCodigoProducto());
            ps.setInt(5, detalle.getCodigoDetalleFactura());
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int eliminar(int codigoDetalleFactura) {
        String sql = "DELETE FROM DetalleFactura WHERE codigoDetalleFactura = ?";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoDetalleFactura);
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}
