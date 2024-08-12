package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FacturaDAO {
    Conexion cn = new Conexion(); 
    Connection con; 
    PreparedStatement ps; 
    ResultSet rs; 

    public Factura buscar(int numeroFactura) {
        Factura factura = new Factura();
        String sql = "SELECT * FROM Factura WHERE numeroFactura = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, numeroFactura);
            rs = ps.executeQuery();
            if (rs.next()) {
                factura.setNumeroFactura(rs.getInt("numeroFactura"));
                factura.setEstado(rs.getString("estado"));
                factura.setTotalFactura(rs.getDouble("totalFactura"));
                factura.setFechaFactura(rs.getDate("fechaFactura"));
                factura.setCodigoCliente(rs.getInt("codigoCliente"));
                factura.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factura;
    }

    public List<Factura> listarFacturas() {
        String sql = "SELECT * FROM Factura";
        List<Factura> listaFactura = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setNumeroFactura(rs.getInt("numeroFactura"));
                factura.setEstado(rs.getString("estado"));
                factura.setTotalFactura(rs.getDouble("totalFactura"));
                factura.setFechaFactura(rs.getDate("fechaFactura"));
                factura.setCodigoCliente(rs.getInt("codigoCliente"));
                factura.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                listaFactura.add(factura);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFactura;
    }

    public int agregar(Factura factura) {
        String sql = "INSERT INTO Factura (numeroFactura, estado, fechaFactura, codigoCliente, codigoEmpleado) VALUES (?, ?, ?, ?, ?)";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);

            // Obtener la fecha actual y convertirla a java.sql.Date
            java.util.Date fechaActual = Calendar.getInstance().getTime();
            java.sql.Date fechaSql = new java.sql.Date(fechaActual.getTime());

            ps.setInt(1, factura.getNumeroFactura());
            ps.setString(2, factura.getEstado());
            ps.setDate(3, fechaSql); // Usar la fecha actual en vez de factura.getFechaFactura()
            ps.setInt(4, factura.getCodigoCliente());
            ps.setInt(5, factura.getCodigoEmpleado());

            resp = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resp;
    }

    public boolean existeNumeroFactura(int numeroFactura) {
        String sql = "SELECT COUNT(*) FROM Factura WHERE numeroFactura = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, numeroFactura);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Si el contador es mayor que 0, significa que el número ya existe
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int actualizar(Factura factura) {
        String sql = "UPDATE Factura SET estado = ?, totalFactura = ?, fechaFactura = ?, codigoCliente = ?, codigoEmpleado = ? WHERE numeroFactura = ?";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, factura.getEstado());
            ps.setDouble(2, factura.getTotalFactura());
            ps.setDate(3, new java.sql.Date(factura.getFechaFactura().getTime()));
            ps.setInt(4, factura.getCodigoCliente());
            ps.setInt(5, factura.getCodigoEmpleado());
            ps.setInt(6, factura.getNumeroFactura());
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int eliminar(int numeroFactura) {
        String sql = "DELETE FROM Factura WHERE numeroFactura = ?";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, numeroFactura);
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}
