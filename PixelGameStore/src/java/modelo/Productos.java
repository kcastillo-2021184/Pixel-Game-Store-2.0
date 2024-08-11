
package modelo;

import java.util.Base64;


public class Productos {
    private String codigoProducto, descripcionProducto, vistaPreviaCadena;
    private double precioUnitario, precioDocena, precioMayor;
    private byte[] vistaPrevia;
    private int existencia, codigoCategoria, codigoDistribuidor;

    public Productos() {
    }

    public Productos(String codigoProducto, String descripcionProducto, double precioUnitario, double precioDocena, double precioMayor, byte[] vistaPrevia, int existencia, int codigoCategoria, int codigoDistribuidor) {
        this.codigoProducto = codigoProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioUnitario = precioUnitario;
        this.precioDocena = precioDocena;
        this.precioMayor = precioMayor;
        this.vistaPrevia = vistaPrevia;
        this.existencia = existencia;
        this.codigoCategoria = codigoCategoria;
        this.codigoDistribuidor = codigoDistribuidor;
    }

    public Productos(String codigoProducto, String descripcionProducto, double precioUnitario, double precioDocena, double precioMayor, String vistaPreviaCadena,int existencia, int codigoCategoria, int codigoDistribuidor) {
        this.codigoProducto = codigoProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioUnitario = precioUnitario;
        this.precioDocena = precioDocena;
        this.precioMayor = precioMayor;
        this.vistaPreviaCadena = vistaPreviaCadena;
        this.existencia = existencia;
        this.codigoCategoria = codigoCategoria;
        this.codigoDistribuidor = codigoDistribuidor;
    }
    
    
    
    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioDocena() {
        return precioDocena;
    }

    public void setPrecioDocena(double precioDocena) {
        this.precioDocena = precioDocena;
    }

    public double getPrecioMayor() {
        return precioMayor;
    }

    public void setPrecioMayor(double precioMayor) {
        this.precioMayor = precioMayor;
    }

    public byte[] getVistaPrevia() {
        return vistaPrevia;
    }

    public void setVistaPrevia(byte[] vistaPrevia) {
        this.vistaPrevia = vistaPrevia;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public int getCodigoDistribuidor() {
        return codigoDistribuidor;
    }

    public void setCodigoDistribuidor(int codigoDistribuidor) {
        this.codigoDistribuidor = codigoDistribuidor;
    }
    
    public String getVistaPreviaBase() {
        if (vistaPrevia != null) {
            return Base64.getEncoder().encodeToString(vistaPrevia);
        }
        return null;
    }
    
    public void setVistaPreviaCadena(String vistaPreviaCadena){
        this.vistaPreviaCadena = vistaPreviaCadena;
    }
}
