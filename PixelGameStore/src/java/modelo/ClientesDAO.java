package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    
    public List Listar(){
        String sql = "select * from Clientes";
        List<Clientes> listaClientes = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Clientes cl = new Clientes();
                cl.setCodigoCliente(rs.getInt(1));
                cl.setNITCliente(rs.getString(2));
                cl.setNombresCliente(rs.getString(3));
                cl.setApellidosCliente(rs.getString(4));
                cl.setDireccionCliente(rs.getString(5));
                cl.setTelefonoCliente(rs.getString(6));
                cl.setCorreoCliente(rs.getString(7));
                listaClientes.add(cl);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaClientes;
    }
    
    public int agregar(Clientes cli){
        String sql = "insert into Clientes(NITCliente, nombresCliente, apellidosCliente, direccionCliente, telefonoCliente, correoCliente) values (?,?,?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNITCliente());
            ps.setString(2, cli.getNombresCliente());
            ps.setString(3, cli.getApellidosCliente());
            ps.setString(4, cli.getDireccionCliente());
            ps.setString(5, cli.getTelefonoCliente());
            ps.setString(6, cli.getCorreoCliente());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    
    public Clientes ListarCodigoCliente(int id){
        Clientes cli = new Clientes();
        String sql = "Select * from Clientes where codigoCliente ="+ id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                cli.setCodigoCliente(rs.getInt(1));
                cli.setNITCliente(rs.getString(2));
                cli.setNombresCliente(rs.getString(3));
                cli.setApellidosCliente(rs.getString(4));
                cli.setDireccionCliente(rs.getString(5));
                cli.setTelefonoCliente(rs.getString(6));
                cli.setCorreoCliente(rs.getString(7));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cli;
    }
    
    public int Actualizar(Clientes cli){
        String sql = "Update Clientes set NITCliente = ?, nombresCliente = ?, apellidosCliente=?, direccionCliente=?, telefonoCliente=?, correoCliente=? where codigoCliente = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNITCliente());
            ps.setString(2, cli.getNombresCliente());
            ps.setString(3, cli.getApellidosCliente());
            ps.setString(4, cli.getDireccionCliente());
            ps.setString(5, cli.getTelefonoCliente());
            ps.setString(6, cli.getCorreoCliente());
            ps.setInt(7, cli.getCodigoCliente());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void Eliminar (int id){
        String sql = "delete from Clientes where codigoCliente = "+ id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
