package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmailDistribuidorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    
    public List <EmailDistribuidor> listar(){
    String sql = "select * from emailDistribuidor";
    List<EmailDistribuidor> listaEmailDistribuidor = new ArrayList<>();
    try{
    con = cn.Conexion();
    ps = con.prepareStatement(sql);
    rs = ps.executeQuery();
    while(rs.next()){
    EmailDistribuidor ed = new EmailDistribuidor();
    ed.setCodigoEmailDistribuidor(rs.getInt("codigoEmailDistribuidor"));
    ed.setEmailDistribuidor(rs.getString("emailDistribuidor"));
    ed.setDescripcion(rs.getString("descripcion"));
    ed.setHorarioDeAtencion(rs.getString("horarioDeAtencion"));
    ed.setCodigoDistribuidor(rs.getInt("codigoDistribuidor"));     
    listaEmailDistribuidor.add(ed);
    }
    }catch(Exception e){
    e.printStackTrace();
    }
    
    return listaEmailDistribuidor;
    }
    
    
    public int agregar(EmailDistribuidor Email){
    String sql = "insert into EmailDistribuidor (emailDistribuidor , descripcion, horarioDeAtencion, codigoDistribuidor) values (?,?,?,?)";
    try{
    con = cn.Conexion();
    ps = con.prepareStatement(sql);
    ps.setString(1, Email.getEmailDistribuidor());
    ps.setString(2, Email.getDescripcion());
    ps.setString(3, Email.getHorarioDeAtencion());
    ps.setInt(4, Email.getCodigoDistribuidor());
    ps.executeUpdate();
    }catch(Exception e){
    e.printStackTrace();
    }
    
    return resp;
    }
    
    public EmailDistribuidor listarCodigoEmailDistribuidor (int id){
    EmailDistribuidor Email = new EmailDistribuidor();
    String sql = "Select * from EmailDistribuidor where codigoEmailDistribuidor ="+id;
    try{
    con = cn.Conexion();
    ps = con.prepareStatement(sql);
    rs = ps.executeQuery();
    
    while(rs.next()){
    Email.setCodigoEmailDistribuidor(rs.getInt("codigoEmailDistribuidor"));
    Email.setEmailDistribuidor(rs.getString("emailDistribuidor"));
    Email.setDescripcion(rs.getString("descripcion"));
    Email.setHorarioDeAtencion(rs.getString("horarioDeAtencion"));
    Email.setCodigoDistribuidor(rs.getInt("codigoDistribuidor"));
        
    }
    
    }catch(Exception e){
    e.printStackTrace();
    }
    return Email;
    }
    
    public int actualizar (EmailDistribuidor Email){
    
    String sql = "Update EmailDistribuidor set EmailDistribuidor = ?, Descripcion = ?, HorarioDeAtencion = ? where codigoEmailDistribuidor = ?";
    
    try{
    con = cn.Conexion();
    ps = con.prepareStatement(sql);
    ps.setString(1, Email.getEmailDistribuidor());
    ps.setString(2, Email.getDescripcion());
    ps.setString(3, Email.getHorarioDeAtencion());
    ps.setInt(4, Email.getCodigoEmailDistribuidor());
    ps.executeUpdate();
    }catch(Exception e){
    e.printStackTrace();
    }
    return resp;
    
    }
    
    
    public void eliminar (int id){
    String sql = "delete from EmailDistribuidor where codigoEmailDistribuidor ="+ id;
    try{
    con = cn.Conexion();
    ps = con.prepareStatement(sql);
    ps.executeUpdate();
    }catch(Exception e)
    {e.printStackTrace();}
    
    }
    
}