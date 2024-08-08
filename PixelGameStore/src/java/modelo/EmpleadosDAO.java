package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List listar(){
        String sql = "Select * Empleados";
        List<Empleados> listaEmpleados = new ArrayList();
        try{
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Empleados em = new Empleados();
                em.setCodigoEmpleado(rs.getInt(1));
                em.setNombresEmpleado(rs.getString(2));
                em.setApellidosEmpleado(rs.getString(3));
                em.setSueldo(rs.getDouble(4));
                em.setDireccionEmpleado(rs.getString(5));
                em.setTurno(rs.getString(6));
                em.setCodigoCargoEmpleado(rs.getInt(7));
                listaEmpleados.add(em);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return listaEmpleados;
    }
    
    public int agregar(Empleados emp){
        String sql = "insert into Empleados(codigoEmpleado, nombresEmpleado, apellidosEmpleado, sueldo, direccionEmpleado, codigoCargoEmpleado values (?,?,?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            ps.setInt(1, emp.getCodigoEmpleado());
            ps.setString(2, emp.getNombresEmpleado());
            ps.setString(3, emp.getApellidosEmpleado());
            ps.setDouble(4, emp.getSueldo());
            ps.setString(5, emp.getDireccionEmpleado());
            ps.setInt(6, emp.getCodigoCargoEmpleado());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resp;
    }
    
    public Empleados listarCodigoEmpleado(int id){
        Empleados emp = new Empleados();
        String sql = "Select * from Empleados where codigoEmpleado =" + id;
        try{
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                emp.setNombresEmpleado(rs.getString(2));
                emp.setApellidosEmpleado(rs.getString(3));
                emp.setSueldo(rs.getDouble(4));
                emp.setDireccionEmpleado(rs.getString(5));
                emp.setCodigoCargoEmpleado(rs.getInt(7));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return emp;
    }
    
    public int actualizar(Empleados emp){
        String sql = "Update Empleados set nombresEmpleado = ?, apellidosEmpleado = ?, "
                + "sueldo = ?, direccionEmpleado = ?, codigoCargoEmpleado = ? where codigoEmpleado = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            ps.setString(1, emp.getNombresEmpleado());
            ps.setString(2, emp.getApellidosEmpleado());
            ps.setDouble(3, emp.getSueldo());
            ps.setString(4, emp.getDireccionEmpleado());
            ps.setInt(5, emp.getCodigoCargoEmpleado());
            ps.setInt(6, emp.getCodigoEmpleado());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "delete from Empleados where codigoEmpleado = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
