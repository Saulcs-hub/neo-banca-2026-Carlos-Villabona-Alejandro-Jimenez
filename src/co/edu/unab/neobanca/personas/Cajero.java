package co.edu.unab.neobanca.personas;

import co.edu.unab.neobanca.infraestructura.Sucursal;

public class Cajero extends Empleado {

    private int numeroCubiculo;

    private Sucursal sucursalAsignada;

    public Cajero(
            String identificacion,
            String nombre,
            String email,
            String idCorporativo,
            int numeroCubiculo) {

        super(
                identificacion,
                nombre,
                email,
                idCorporativo
        );

        if (numeroCubiculo <= 0) {
            throw new IllegalArgumentException(
                    "El número de cubículo debe ser mayor que cero."
            );
        }

        this.numeroCubiculo = numeroCubiculo;
        this.sucursalAsignada = null;
    }

    public int getNumeroCubiculo() {
        return numeroCubiculo;
    }

    public Sucursal getSucursalAsignada() {
        return sucursalAsignada;
    }

    public void asignarSucursal(Sucursal sucursal) {

        if (sucursal == null) {
            throw new IllegalArgumentException(
                    "La sucursal es obligatoria."
            );
        }

        if (!sucursal.isActiva()) {
            throw new IllegalStateException(
                    "No se puede asignar una sucursal cerrada."
            );
        }

        this.sucursalAsignada = sucursal;
    }

    public void desvincularSucursal() {
        this.sucursalAsignada = null;
    }

    @Override
    public String describirPuestoDeTrabajo() {
        return "Atención de clientes y operaciones de caja en sucursal.";
    }

    @Override
    public String getRolEnElBanco() {
        return "Empleado - Cajero";
    }
}