
package modelo;


public class Productos {
    private String codigoProducto;
    private String descripcionProducto;
    private Double precioUnitario;
    private Double precioDocena;
    private Double precioMayor;
    private Byte[] vistaPrevia;
    private int existencia;
    private int codigoCategoria;
    private int codigoDistribuidor;

    public Productos() {
    }

    public Productos(String codigoProducto, String descripcionProducto, Double precioUnitario, Double precioDocena, Double precioMayor, Byte[] vistaPrevia, int existencia, int codigoCategoria, int codigoDistribuidor) {
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

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioDocena() {
        return precioDocena;
    }

    public void setPrecioDocena(Double precioDocena) {
        this.precioDocena = precioDocena;
    }

    public Double getPrecioMayor() {
        return precioMayor;
    }

    public void setPrecioMayor(Double precioMayor) {
        this.precioMayor = precioMayor;
    }

    public Byte[] getVistaPrevia() {
        return vistaPrevia;
    }

    public void setVistaPrevia(Byte[] vistaPrevia) {
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
    
    
}
