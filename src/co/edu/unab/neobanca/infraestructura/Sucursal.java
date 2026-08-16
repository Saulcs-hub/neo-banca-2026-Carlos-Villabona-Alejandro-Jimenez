package co.edu.unab.neobanca.infraestructura;

import co.edu.unab.neobanca.personas.Cajero;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {

    private final String codigo;
    private boolean activa;

    private final List<Cajero> cajeros;
    private final List<CajeroAutomatico> cajerosAutomaticos;

    public Sucursal(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "El código de la sucursal es obligatorio."
            );
        }

        this.codigo = codigo;
        this.activa = true;

        this.cajeros = new ArrayList<>();
        this.cajerosAutomaticos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isActiva() {
        return activa;
    }

    public List<Cajero> getCajeros() {
        return List.copyOf(cajeros);
    }

    public List<CajeroAutomatico> getCajerosAutomaticos() {
        return List.copyOf(cajerosAutomaticos);
    }

    public void vincularCajero(Cajero cajero) {

        validarSucursalActiva();

        if (cajero == null) {
            throw new IllegalArgumentException(
                    "El cajero es obligatorio."
            );
        }

        if (!cajeros.contains(cajero)) {

            cajeros.add(cajero);
            cajero.asignarSucursal(this);
        }
    }

    public void vincularCajeroAutomatico(
            CajeroAutomatico cajeroAutomatico) {

        validarSucursalActiva();

        if (cajeroAutomatico == null) {
            throw new IllegalArgumentException(
                    "El cajero automático es obligatorio."
            );
        }

        if (!cajerosAutomaticos.contains(cajeroAutomatico)) {
            cajerosAutomaticos.add(cajeroAutomatico);
        }
    }

    public void cerrar(Sucursal destino) {

        if (destino == null) {
            throw new IllegalArgumentException(
                    "Debe especificarse una sucursal de destino."
            );
        }

        if (destino == this) {
            throw new IllegalArgumentException(
                    "La sucursal de destino debe ser diferente."
            );
        }

        if (!destino.isActiva()) {
            throw new IllegalStateException(
                    "La sucursal de destino debe estar activa."
            );
        }

        for (Cajero cajero : cajeros) {
            destino.vincularCajero(cajero);
        }

        for (CajeroAutomatico cajeroAutomatico
                : cajerosAutomaticos) {

            destino.vincularCajeroAutomatico(
                    cajeroAutomatico
            );
        }

        cajeros.clear();
        cajerosAutomaticos.clear();

        this.activa = false;
    }

    private void validarSucursalActiva() {

        if (!activa) {
            throw new IllegalStateException(
                    "La sucursal se encuentra cerrada."
            );
        }
    }
}