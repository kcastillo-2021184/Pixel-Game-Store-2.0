package modelo;


public class EmailDistribuidor {
    
    public int codigoEmailDistribuidor;
    public String emailDistribuidor;
    public String descripcion;
    public String horarioDeAtencion;
    public int codigoDistribuidor;

    public EmailDistribuidor() {
    }

    public EmailDistribuidor(int codigoEmailDistribuidor, String emailDistribuidor, String descripcion, String horarioDeAtencion, int codigoDistribuidor) {
        this.codigoEmailDistribuidor = codigoEmailDistribuidor;
        this.emailDistribuidor = emailDistribuidor;
        this.descripcion = descripcion;
        this.horarioDeAtencion = horarioDeAtencion;
        this.codigoDistribuidor = codigoDistribuidor;
    }

    public int getCodigoEmailDistribuidor() {
        return codigoEmailDistribuidor;
    }

    public void setCodigoEmailDistribuidor(int codigoEmailDistribuidor) {
        this.codigoEmailDistribuidor = codigoEmailDistribuidor;
    }

    public String getEmailDistribuidor() {
        return emailDistribuidor;
    }

    public void setEmailDistribuidor(String emailDistribuidor) {
        this.emailDistribuidor = emailDistribuidor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorarioDeAtencion() {
        return horarioDeAtencion;
    }

    public void setHorarioDeAtencion(String horarioDeAtencion) {
        this.horarioDeAtencion = horarioDeAtencion;
    }

    public int getCodigoDistribuidor() {
        return codigoDistribuidor;
    }

    public void setCodigoDistribuidor(int codigoDistribuidor) {
        this.codigoDistribuidor = codigoDistribuidor;
    }
    
    
        
    
    
}
