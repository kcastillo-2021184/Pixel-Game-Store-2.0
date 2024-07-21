package modelo;

import config.Conexion;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ProductosDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    //Convertir blob a arreglo de bytes
    public byte[] blobABytes(Blob blob){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(ByteArrayInputStream inputStream = (ByteArrayInputStream) blob.getBinaryStream()){
            byte[] buffer = new byte[4096];
            int bytesRead;
            while((bytesRead = inputStream.read(buffer)) != -1){
                baos.write(buffer, 0, bytesRead);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
    
    
    //Convertir arreglo de bytes a blob
    public Blob obtenerBlobDeByteArray(byte[] imagenBytes){
        try{
            Blob blob = cn.Conexion().createBlob();
            if(imagenBytes != null){
                blob.setBytes(1, imagenBytes);
            }else {
                return null;
            }
            return blob;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    //Convertir arreglo de bytes a Imagen
    public Image bytesAImagen(byte[] bytes){
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage image = null;
        try{
            image = ImageIO.read(bais);
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }
    
    //Listar Productos
    public List<Productos> Listar(){
        String sql = "select * from productos";
        List<Productos> listaProductos = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Productos producto = new Productos();
                producto.setCodigoProducto(rs.getString(1));
                producto.setDescripcionProducto(rs.getString(2));
                producto.setPrecioUnitario(rs.getDouble(3));
                producto.setPrecioDocena(rs.getDouble(4));
                producto.setPrecioMayor(rs.getDouble(5));
//                producto.setVistaPrevia(blobABytes(rs.getBlob(6)));
                producto.setExistencia(rs.getInt(7));
                producto.setCodigoCategoria(rs.getInt(8));
                producto.setCodigoDistribuidor(rs.getInt(9));
                listaProductos.add(producto);
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            cerrarRecursos();
        }
        return listaProductos;
    }
    
    //Agregar
    public int agregar(Productos prod){
        String sql = "insert into productos (codigoProducto, descripcionProducto, precioUnitario, precioDocena, precioMayor, vistaPrevia, existencia, codigoCategoria, codigoDistribuidor) values (?,?,?,?,?,?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getCodigoProducto());
            ps.setString(2, prod.getDescripcionProducto());
            ps.setDouble(3, prod.getPrecioUnitario());
            ps.setDouble(4, prod.getPrecioDocena());
            ps.setDouble(5, prod.getPrecioMayor());
//            ps.setBlob(6, obtenerBlobDeByteArray(prod.getVistaPrevia()));
            ps.setInt(7, prod.getExistencia());
            ps.setInt(8, prod.getCodigoCategoria());
            ps.setInt(9, prod.getCodigoDistribuidor());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarRecursos();
        }
        return resp;
    }
    
    //Buscar por Codigo
    public Productos listarPorCodigoProducto(String codigo){
        Productos producto = new Productos();
        String sql = "select * from productos where codigoProducto = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                producto.setCodigoProducto(rs.getString(1));
                producto.setDescripcionProducto(rs.getString(2));
                producto.setPrecioUnitario(rs.getDouble(3));
                producto.setPrecioDocena(rs.getDouble(4));
                producto.setPrecioMayor(rs.getDouble(5));
//                producto.setVistaPrevia(blobABytes(rs.getBlob(6)));
                producto.setExistencia(rs.getInt(7));
                producto.setCodigoCategoria(rs.getInt(8));
                producto.setCodigoDistribuidor(rs.getInt(9));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarRecursos();
        }
        return producto;
    }
    
    //Actualizar
    public int actualizar(Productos prod){
        String sql = "update productos set descripcionProducto = ?, precioUnitario = ?, precioDocena = ?, precioMayor = ?, vistaPrevia = ?, existencia = ?, codigoCategoria = ?, codigoDistribuidor = ? where codigoProducto = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getDescripcionProducto());
            ps.setDouble(2, prod.getPrecioUnitario());
            ps.setDouble(3, prod.getPrecioDocena());
            ps.setDouble(4, prod.getPrecioMayor());
//            ps.setBlob(5, obtenerBlobDeByteArray(prod.getVistaPrevia()));
            ps.setInt(6, prod.getExistencia());
            ps.setInt(7, prod.getCodigoCategoria());
            ps.setInt(8, prod.getCodigoDistribuidor());
            ps.setString(9, prod.getCodigoProducto());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            cerrarRecursos();
        }
        
        return resp;
    }
    
    //Eliminar
    public void eliminar(String codigo){
        String sql = "delete from productos where codigoProducto = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarRecursos();
        }
    }
    
    //cerrar recursos
    private void cerrarRecursos(){
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
