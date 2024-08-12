
package modelo;

public class Articulo {
    private int codigoArticulo;
    private double precioUnitario, subTotal;
    private int numeroFactura, cantidad;
    private String codigoProducto, descripcionProducto;

    public Articulo() {
    }

    public Articulo(int codigoArticulo, double precioUnitario, double subTotal, int numeroFactura, int cantidad, String codigoProducto, String descripcionProducto) {
        this.codigoArticulo = codigoArticulo;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.numeroFactura = numeroFactura;
        this.cantidad = cantidad;
        this.codigoProducto = codigoProducto;
        this.descripcionProducto = descripcionProducto;
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    
    
    
    
}
