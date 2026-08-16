package co.edu.unab.neobanca.personas;

import co.edu.unab.neobanca.infraestructura.Computador;

public abstract class Empleado extends Persona {

    private final String idCorporativo;

    private Computador computadorAsignado;

    public Empleado(
            String identificacion,
            String nombre,
            String email,
            String idCorporativo) {

        super(
                identificacion,
                nombre,
                email
        );

        if (idCorporativo == null || idCorporativo.isBlank()) {
            throw new IllegalArgumentException(
                    "El ID corporativo es obligatorio."
            );
        }

        this.idCorporativo = idCorporativo;
        this.computadorAsignado = null;
    }

    public String getIdCorporativo() {
        return idCorporativo;
    }

    public Computador getComputadorAsignado() {
        return computadorAsignado;
    }

    public void asignarComputador(Computador computador) {

        if (computador == null) {
            throw new IllegalArgumentException(
                    "El computador es obligatorio."
            );
        }

        if (computador.isAsignado()) {
            throw new IllegalStateException(
                    "El computador ya está asignado."
            );
        }

        if (this.computadorAsignado != null) {
            this.computadorAsignado.liberar();
        }

        this.computadorAsignado = computador;
        computador.marcarComoAsignado();
    }

    public void devolverComputador() {

        if (computadorAsignado != null) {

            computadorAsignado.liberar();
            computadorAsignado = null;
        }
    }

    public abstract String describirPuestoDeTrabajo();
}