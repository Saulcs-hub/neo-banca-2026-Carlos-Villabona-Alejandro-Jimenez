package co.edu.unab.neobanca.personas;

import java.util.ArrayList;
import java.util.List;

public class AsesorExterno extends Empleado {

    private String zonaGeografica;

    private final List<Cliente> clientesCaptados;

    public AsesorExterno(
            String identificacion,
            String nombre,
            String email,
            String idCorporativo,
            String zonaGeografica) {

        super(
                identificacion,
                nombre,
                email,
                idCorporativo
        );

        if (zonaGeografica == null || zonaGeografica.isBlank()) {
            throw new IllegalArgumentException(
                    "La zona geográfica es obligatoria."
            );
        }

        this.zonaGeografica = zonaGeografica;
        this.clientesCaptados = new ArrayList<>();
    }

    public String getZonaGeografica() {
        return zonaGeografica;
    }

    public void setZonaGeografica(String zonaGeografica) {

        if (zonaGeografica == null || zonaGeografica.isBlank()) {
            throw new IllegalArgumentException(
                    "La zona geográfica es obligatoria."
            );
        }

        this.zonaGeografica = zonaGeografica;
    }

    public void captarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente es obligatorio."
            );
        }

        if (!clientesCaptados.contains(cliente)) {
            clientesCaptados.add(cliente);
        }
    }

    public List<Cliente> getClientesCaptados() {
        return List.copyOf(clientesCaptados);
    }

    @Override
    public String describirPuestoDeTrabajo() {
        return "Captación y acompañamiento de clientes en una zona geográfica.";
    }

    @Override
    public String getRolEnElBanco() {
        return "Empleado - Asesor Externo";
    }
}