package co.edu.unab.neobanca.infraestructura;

public class Computador {

    private final String codigoInventario;
    private boolean asignado;

    public Computador(String codigoInventario) {

        if (codigoInventario == null || codigoInventario.isBlank()) {
            throw new IllegalArgumentException(
                    "El código de inventario es obligatorio."
            );
        }

        this.codigoInventario = codigoInventario;
        this.asignado = false;
    }

    public String getCodigoInventario() {
        return codigoInventario;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void marcarComoAsignado() {
        this.asignado = true;
    }

    public void liberar() {
        this.asignado = false;
    }
}