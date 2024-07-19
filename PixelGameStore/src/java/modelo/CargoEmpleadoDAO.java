package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CargoEmpleadoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public CargoEmpleado validar(String nombreCargo, String descripcion){
        CargoEmpleado cargoempleado = new CargoEmpleado();
        String sql = "select * from nombreCargo = ? and descripcion = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreCargo);
            ps.setString(2, descripcion);
            rs = ps.executeQuery();
            while (rs.next()){
                cargoempleado.setCodigoCargoEmpleado(rs.getInt("codigoCargoEmpleado"));
                cargoempleado.setNombreCargo(rs.getString("nombreCargo"));
                cargoempleado.setDescripcion(rs.getString("descripcion"));
                cargoempleado.setTurno(rs.getString("turno"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cargoempleado;
    }
    
    public List listar(){
        String sql = "Select * from cargoEmpleado";
        List <CargoEmpleado> listaCargoEmpleado = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                CargoEmpleado ce = new CargoEmpleado();
                ce.setCodigoCargoEmpleado(rs.getInt(1));
                ce.setNombreCargo(rs.getString(2));
                ce.setDescripcion(rs.getString(3));
                ce.setTurno(rs.getString(4));
                listaCargoEmpleado.add(ce);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return listaCargoEmpleado;
    }
    
    public int agregar(CargoEmpleado cea){
        String sql = "insert into CargoEmpleado(codigoCargoEmpleado, nombreCargoEmpleado, descripcion, turno) values (?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cea.getNombreCargo());
            ps.setString(2, cea.getDescripcion());
            ps.setString(3, cea.getTurno());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public CargoEmpleado listarCargoEmpleado (int id){
        CargoEmpleado cea = new CargoEmpleado();
        String sql = "Select * from CargoEmpleado where codigoCargoEmpleado ="+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                cea.setNombreCargo(rs.getString(2));
                cea.setDescripcion(rs.getString(3));
                cea.setTurno(rs.getString(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cea;
    }
    
    public int actualizar(CargoEmpleado cea){
        String sql = "Update CargoEmpleado set nombreCargo = ?, descripcion = ?, turno = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cea.getNombreCargo());
            ps.setString(2, cea.getDescripcion());
            ps.setString(3, cea.getTurno());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "delete from empleado where codigoEmpleado="+ id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
