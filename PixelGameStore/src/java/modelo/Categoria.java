package modelo;

public class Categoria {
    
    private int codigoCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private String localizacionCategoria;

    public Categoria() {
    }

    public Categoria(int codigoCategoria, String nombreCategoria, String descripcionCategoria, String localizacionCategoria) {
        this.codigoCategoria = codigoCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.localizacionCategoria = localizacionCategoria;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public String getLocalizacionCategoria() {
        return localizacionCategoria;
    }

    public void setLocalizacionCategoria(String localizacionCategoria) {
        this.localizacionCategoria = localizacionCategoria;
    }
    
    
    
}
