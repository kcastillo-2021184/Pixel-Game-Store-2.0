package modelo;

public class TipoUsuario {

    private int codigoTIpoUsuario;
    private String tipoUsuario;

    public TipoUsuario() {
    }

    public TipoUsuario(int codigoTIpoUsuario, String tipoUsuario) {
        this.codigoTIpoUsuario = codigoTIpoUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public int getCodigoTIpoUsuario() {
        return codigoTIpoUsuario;
    }

    public void setCodigoTIpoUsuario(int codigoTIpoUsuario) {
        this.codigoTIpoUsuario = codigoTIpoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
    
}
