package modelo;

public class TelefonoDistribuidor {
    private int codigoTelefonoDistribuidor;
    private String numeroPrincipal;
    private String numeroSecundario;
    private String observaciones;
    private int codigoDistribuidor;
    
    public TelefonoDistribuidor() {
    }

    public TelefonoDistribuidor(int codigoTelefonoDistribuidor, String numeroPrincipal, String numeroSecundario, String observaciones, int codigoDistribuidor) {
        this.codigoTelefonoDistribuidor = codigoTelefonoDistribuidor;
        this.numeroPrincipal = numeroPrincipal;
        this.numeroSecundario = numeroSecundario;
        this.observaciones = observaciones;
        this.codigoDistribuidor = codigoDistribuidor;
    }

    public int getCodigoTelefonoDistribuidor() {
        return codigoTelefonoDistribuidor;
    }

    public void setCodigoTelefonoDistribuidor(int codigoTelefonoDistribuidor) {
        this.codigoTelefonoDistribuidor = codigoTelefonoDistribuidor;
    }

    public String getNumeroPrincipal() {
        return numeroPrincipal;
    }

    public void setNumeroPrincipal(String numeroPrincipal) {
        this.numeroPrincipal = numeroPrincipal;
    }

    public String getNumeroSecundario() {
        return numeroSecundario;
    }

    public void setNumeroSecundario(String numeroSecundario) {
        this.numeroSecundario = numeroSecundario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getCodigoDistribuidor() {
        return codigoDistribuidor;
    }

    public void setCodigoDistribuidor(int codigoDistribuidor) {
        this.codigoDistribuidor = codigoDistribuidor;
    }
}
