package co.edu.unab.neobanca.infraestructura;

public class Sucursal {

    private final String codigo;
    private boolean activa;

    public Sucursal(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "El código de la sucursal es obligatorio."
            );
        }

        this.codigo = codigo;
        this.activa = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void cerrar() {
        this.activa = false;
    }
}