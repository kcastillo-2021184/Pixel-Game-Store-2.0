package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    Conexion cn = new Conexion ();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    //CRUD
    
    //LISTAR
    public List listar(){
        String sql = "select * from Categoria";
        List<Categoria> listaCategoria = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs =ps.executeQuery();
            while(rs.next()){
                Categoria cat =new Categoria();
                cat.setCodigoCategoria(rs.getInt(1));
                cat.setNombreCategoria(rs.getString(2));
                cat.setDescripcionCategoria(rs.getString(3));
                cat.setLocalizacionCategoria(rs.getString(4));
                listaCategoria.add(cat);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaCategoria;
    }
    
    //AGREGAR
    public int agregar (Categoria cat){
        String sql  = "insert into Categoria(nombreCategoria,descripcionCategoria,localizacionCategoria) values (?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cat.getNombreCategoria());
            ps.setString(2, cat.getDescripcionCategoria());
            ps.setString(3, cat.getLocalizacionCategoria());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //BUSCAR POR CÓDIGO
    public Categoria listarCodigoCategoria (int id){
        Categoria cat = new Categoria();
        String sql = "select * from Categoria where codigoCategoria = "+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                cat.setNombreCategoria(rs.getString(2));
                cat.setDescripcionCategoria(rs.getString(3));
                cat.setLocalizacionCategoria(rs.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return cat;
    }
    
    //ACTUALIZAR
    public int actualizar(Categoria cat){
        String sql = "update Categora set nombreCategoria =?, descripcionCategoria=?, localizacionCategoria=? where codigoCategoria =?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cat.getNombreCategoria());
            ps.setString(2, cat.getDescripcionCategoria());
            ps.setString(3, cat.getLocalizacionCategoria());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //ELIMINAR
    public void eliminar (int id){
        String sql = "delete from Categoria where codigoCategoria = "+id;
        try{
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
