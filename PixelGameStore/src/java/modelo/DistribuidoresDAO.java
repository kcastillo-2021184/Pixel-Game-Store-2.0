
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Distribuidores;

public class DistribuidoresDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List <Distribuidores> listar(){
        String sql = "Select * from distribuidores";
        List<Distribuidores> listaDistribuidores = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Distribuidores distribuidor = new Distribuidores();
                distribuidor.setCodigoDistribuidor(rs.getInt("codigoDistribuidor"));
                distribuidor.setNombreDistribuidor(rs.getString("nombreDistribuidor"));
                distribuidor.setDireccionDistribuidor(rs.getString("direccionDistribuidor"));
                distribuidor.setCodigoPostal(rs.getString("codigoPostal"));
                distribuidor.setPaginaWeb(rs.getString("paginaWeb"));
                listaDistribuidores.add(distribuidor);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDistribuidores;
    }
    
    public int agregar(Distribuidores distribuidor){
        String sql = "Insert into distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values(?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, distribuidor.getNombreDistribuidor());
            ps.setString(2, distribuidor.getDireccionDistribuidor());
            ps.setString(3, distribuidor.getCodigoPostal());
            ps.setString(4, distribuidor.getPaginaWeb());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public Distribuidores listarCodigoDistribuidor(int id){
        Distribuidores distribuidor = new Distribuidores();
        String sql = "select * from distribuidores where codigoDistribuidor = " + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                distribuidor.setCodigoDistribuidor(rs.getInt("codigoDistribuidor"));
                distribuidor.setNombreDistribuidor(rs.getString("nombreDistribuidor"));
                distribuidor.setDireccionDistribuidor(rs.getString("direccionDistribuidor"));
                distribuidor.setCodigoPostal(rs.getString("codigoPostal"));
                distribuidor.setPaginaWeb(rs.getString("paginaWeb"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return distribuidor;
    }
 
    public int actualizar(Distribuidores distribuidor){
        String sql = "update distribuidores "
                + "set nombreDistribuidor = ?, "
                + "direccionDistribuidor = ?, "
                + "codigoPostal = ?, "
                + "paginaWeb = ? "
                + "where codigoDistribuidor = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, distribuidor.getNombreDistribuidor());
            ps.setString(2, distribuidor.getDireccionDistribuidor());
            ps.setString(3, distribuidor.getCodigoPostal());
            ps.setString(4, distribuidor.getPaginaWeb());
            ps.setInt(5, distribuidor.getCodigoDistribuidor());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "delete from distribuidores where codigoDistribuidor =" +id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
