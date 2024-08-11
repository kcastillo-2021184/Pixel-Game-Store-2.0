package modelo;

import config.Conexion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductosDAO {
    Conexion cn = new Conexion(); // Configuración de conexión
    Connection con; // Conexión SQL
    PreparedStatement ps; // PreparedStatement
    ResultSet rs; // ResultSet

    // Método para listar todos los productos
    public List<Productos> listar() {
    List<Productos> listaProductos = new ArrayList<>();
    String sql = "SELECT * FROM Productos";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Productos producto = new Productos();
            producto.setCodigoProducto(rs.getString("codigoProducto"));
            producto.setDescripcionProducto(rs.getString("descripcionProducto"));
            producto.setPrecioUnitario(rs.getDouble("precioUnitario"));
            producto.setPrecioDocena(rs.getDouble("precioDocena"));
            producto.setPrecioMayor(rs.getDouble("precioMayor"));
            producto.setExistencia(rs.getInt("existencia"));
            producto.setCodigoCategoria(rs.getInt("codigoCategoria"));
            producto.setCodigoDistribuidor(rs.getInt("codigoDistribuidor"));

            Blob blob = rs.getBlob("vistaPrevia");
            if (blob != null) {
                byte[] bytesDeImagen;
                InputStream entrada;
                int bytesLeidos;
                byte[] buffer = new byte[104857600];
                ByteArrayOutputStream salida = new ByteArrayOutputStream(); 

                entrada = blob.getBinaryStream();
                while((bytesLeidos = entrada.read(buffer)) != -1) {
                    salida.write(buffer, 0, bytesLeidos);
                }
                bytesDeImagen = salida.toByteArray();
                
                producto.setVistaPrevia(bytesDeImagen);
                producto.setVistaPreviaCadena(Base64.getEncoder().encodeToString(bytesDeImagen));
            } else {
                // Si no hay imagen, puedes asignar un valor por defecto o dejar el campo vacío
                producto.setVistaPrevia(null);
                producto.setVistaPreviaCadena(null);
            }
            listaProductos.add(producto);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return listaProductos;
}

    // Método para buscar un producto por código
    public Productos listarCodigoProducto(String codigoProducto) {
        Productos producto = null;
        String sql = "SELECT * FROM Productos WHERE codigoProducto = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigoProducto);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto = new Productos();
                producto.setCodigoProducto(rs.getString("codigoProducto"));
                producto.setDescripcionProducto(rs.getString("descripcionProducto"));
                producto.setPrecioUnitario(rs.getDouble("precioUnitario"));
                producto.setPrecioDocena(rs.getDouble("precioDocena"));
                producto.setPrecioMayor(rs.getDouble("precioMayor"));
                producto.setExistencia(rs.getInt("existencia"));
                producto.setCodigoCategoria(rs.getInt("codigoCategoria"));
                producto.setCodigoDistribuidor(rs.getInt("codigoDistribuidor"));
                
                byte[] bytesDeImagen;
                InputStream entrada;
                Blob blob;
                int bytesLeidos;
                byte[] buffer = new byte[104857600];
                ByteArrayOutputStream salida = new ByteArrayOutputStream(); 

                blob = rs.getBlob("vistaPrevia");
                
                entrada = blob.getBinaryStream();
                while((bytesLeidos = entrada.read(buffer))!=-1){
                    salida.write(buffer, 0, bytesLeidos);
                }
                bytesDeImagen = salida.toByteArray();
                
                producto.setVistaPrevia(Base64.getEncoder().encode(blobToBytes(rs.getBlob("vistaPrevia"))));
                producto.setVistaPreviaCadena(Base64.getEncoder().encodeToString(blobToBytes(rs.getBlob("vistaPrevia"))));
                
                producto.setVistaPrevia(bytesDeImagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    // Método para agregar un nuevo producto
    public int agregar(Productos producto) throws IOException {
        String sql = "INSERT INTO Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor, vistaPrevia) VALUES (?, ?, ?, ?, ?)";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getCodigoProducto());
            ps.setString(2, producto.getDescripcionProducto());
            ps.setInt(3, producto.getCodigoCategoria());
            ps.setInt(4, producto.getCodigoDistribuidor());
            ps.setBlob(5, bytesToBlob(con,producto.getVistaPrevia()));
            resp = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resp;
    }

    // Método para actualizar un producto
    public int actualizar(Productos producto) throws IOException {
        String sql = "UPDATE Productos SET descripcionProducto = ?, precioUnitario = ?, precioDocena = ?, precioMayor = ?, existencia = ?, codigoCategoria = ?, codigoDistribuidor = ?, vistaPrevia = ? WHERE codigoProducto = ?";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getDescripcionProducto());
            ps.setDouble(2, producto.getPrecioUnitario());
            ps.setDouble(3, producto.getPrecioDocena());
            ps.setDouble(4, producto.getPrecioMayor());
            ps.setInt(5, producto.getExistencia());
            ps.setInt(6, producto.getCodigoCategoria());
            ps.setInt(7, producto.getCodigoDistribuidor());
            ps.setBlob(8, bytesToBlob(con,producto.getVistaPrevia()));
            ps.setString(9, producto.getCodigoProducto());
            resp = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resp;
    }

    // Método para eliminar un producto por código
    public int eliminar(String codigoProducto) {
        String sql = "DELETE FROM Productos WHERE codigoProducto = ?";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigoProducto);
            resp = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    
    public static byte[] blobToBytes(Blob blob) throws SQLException, IOException {
        if (blob == null) {
            return new byte[0];
        }
        
        try (InputStream inputStream = blob.getBinaryStream()) {
            long blobLength = blob.length();
            byte[] bytes = new byte[(int) blobLength];
            int bytesRead = 0;
            int read;
            while ((read = inputStream.read(bytes, bytesRead, bytes.length - bytesRead)) != -1) {
                bytesRead += read;
            }
            return bytes;
        }
    }
    
    public static Blob bytesToBlob(Connection connection, byte[] bytes) throws SQLException, IOException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        // Crear un Blob en la base de datos
        Blob blob = connection.createBlob();
        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            blob.setBytes(1, bytes);
        }

        return blob;
    }
}
