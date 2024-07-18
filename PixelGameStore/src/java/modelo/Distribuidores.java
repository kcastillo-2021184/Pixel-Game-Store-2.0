
package modelo;

public class Distribuidores {
    private int codigoDistribuidor;
    private String nombreDistribuidor;
    private String direccionDistribuidor;
    private String codigoPostal;
    private String paginaWeb;

    public Distribuidores() {
    }

    public Distribuidores(int codigoDistribuidor, String nombreDistribuidor, String direccionDistribuidor, String codigoPostal, String paginaWeb) {
        this.codigoDistribuidor = codigoDistribuidor;
        this.nombreDistribuidor = nombreDistribuidor;
        this.direccionDistribuidor = direccionDistribuidor;
        this.codigoPostal = codigoPostal;
        this.paginaWeb = paginaWeb;
    }

    public int getCodigoDistribuidor() {
        return codigoDistribuidor;
    }

    public void setCodigoDistribuidor(int codigoDistribuidor) {
        this.codigoDistribuidor = codigoDistribuidor;
    }

    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    public String getDireccionDistribuidor() {
        return direccionDistribuidor;
    }

    public void setDireccionDistribuidor(String direccionDistribuidor) {
        this.direccionDistribuidor = direccionDistribuidor;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }
    
    
}
