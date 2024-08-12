
package modelo;

import java.util.Date;

public class Factura {
    private int numeroFactura, codigoCliente, codigoEmpleado;
    private String estado;
    private Date fechaFactura;
    private double totalFactura;

    public Factura() {
    }

    public Factura(int numeroFactura, int codigoCliente, int codigoEmpleado, String estado, Date fechaFactura, double totalFactura) {
        this.numeroFactura = numeroFactura;
        this.codigoCliente = codigoCliente;
        this.codigoEmpleado = codigoEmpleado;
        this.estado = estado;
        this.fechaFactura = fechaFactura;
        this.totalFactura = totalFactura;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    

    
    
}
