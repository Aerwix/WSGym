package POJOS;

public class telefono {
    private Integer idTelefono;
    private String telefonoCasa;
    private String telefonoCel;
    private Integer idCliente;

    public telefono(Integer idTelefono, String telefonoCasa, String telefonoCel, Integer idCliente) {
        this.idTelefono = idTelefono;
        this.telefonoCasa = telefonoCasa;
        this.telefonoCel = telefonoCel;
        this.idCliente = idCliente;
    }

    public telefono() {
    }

    public Integer getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getTelefonoCel() {
        return telefonoCel;
    }

    public void setTelefonoCel(String telefonoCel) {
        this.telefonoCel = telefonoCel;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    
    
}
