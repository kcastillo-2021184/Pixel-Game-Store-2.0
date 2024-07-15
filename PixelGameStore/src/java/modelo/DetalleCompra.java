package modelo;

public class DetalleCompra {
  
    private int codigoDetalleCompra;
    private float costoUnitario;
    private int cantidad;
    private String codigoProducto;
    private int numeroDocumento;

    public DetalleCompra() {
    }

    public DetalleCompra(int codigoDetalleCompra, float costoUnitario, int cantidad, String codigoProducto, int numeroDocumento) {
        this.codigoDetalleCompra = codigoDetalleCompra;
        this.costoUnitario = costoUnitario;
        this.cantidad = cantidad;
        this.codigoProducto = codigoProducto;
        this.numeroDocumento = numeroDocumento;
    }

    public int getCodigoDetalleCompra() {
        return codigoDetalleCompra;
    }

    public void setCodigoDetalleCompra(int codigoDetalleCompra) {
        this.codigoDetalleCompra = codigoDetalleCompra;
    }

    public float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
        this.costoUnitario = costoUnitario;
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

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
}
