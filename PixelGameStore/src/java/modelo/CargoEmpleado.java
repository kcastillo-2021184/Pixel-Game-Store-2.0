package modelo;

public class CargoEmpleado {
    private int codigoCargoEmpleado;
    private String nombreCargo;
    private String descripcion;
    private String turno;

    public CargoEmpleado() {
    }

    public CargoEmpleado(int codigoCargoEmpleado, String nombreCargo, String descripcion, String turno) {
        this.codigoCargoEmpleado = codigoCargoEmpleado;
        this.nombreCargo = nombreCargo;
        this.descripcion = descripcion;
        this.turno = turno;
    }

    public int getCodigoCargoEmpleado() {
        return codigoCargoEmpleado;
    }

    public void setCodigoCargoEmpleado(int codigoCargoEmpleado) {
        this.codigoCargoEmpleado = codigoCargoEmpleado;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    
}
