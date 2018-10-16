package POJOS;

public class Progreso {
    private Integer idProgreso;
    private String Peso;
    private String Talla;
    private String mc;
    private String fechaRegistro;
    private Integer idCliente;

    public Progreso(Integer idProgreso, String Peso, String Talla, String mc, String fechaRegistro, Integer idCliente) {
        this.idProgreso = idProgreso;
        this.Peso = Peso;
        this.Talla = Talla;
        this.mc = mc;
        this.fechaRegistro = fechaRegistro;
        this.idCliente = idCliente;
    }

    public Progreso() {
    }

    public Integer getIdProgreso() {
        return idProgreso;
    }

    public void setIdProgreso(Integer idProgreso) {
        this.idProgreso = idProgreso;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String Peso) {
        this.Peso = Peso;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String Talla) {
        this.Talla = Talla;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
