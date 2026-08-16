package co.edu.unab.neobanca.personas;

public class Beneficiario {

    private String nombre;
    private String parentesco;
    private String telefonoContacto;

    public Beneficiario(
            String nombre,
            String parentesco,
            String telefonoContacto) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del beneficiario es obligatorio."
            );
        }

        if (parentesco == null || parentesco.isBlank()) {
            throw new IllegalArgumentException(
                    "El parentesco es obligatorio."
            );
        }

        if (telefonoContacto == null || telefonoContacto.isBlank()) {
            throw new IllegalArgumentException(
                    "El teléfono de contacto es obligatorio."
            );
        }

        this.nombre = nombre;
        this.parentesco = parentesco;
        this.telefonoContacto = telefonoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getParentesco() {
        return parentesco;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }
}