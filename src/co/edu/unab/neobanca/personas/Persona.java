package co.edu.unab.neobanca.personas;

public abstract class Persona {

    private final String identificacion;
    private String nombre;
    private String email;

    public Persona(
            String identificacion,
            String nombre,
            String email) {

        if (identificacion == null || identificacion.isBlank()) {
            throw new IllegalArgumentException(
                    "La identificación es obligatoria."
            );
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio."
            );
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "El correo electrónico es obligatorio."
            );
        }

        this.identificacion = identificacion;
        this.nombre = nombre;
        this.email = email;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio."
            );
        }

        this.nombre = nombre;
    }

    public void setEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "El correo electrónico es obligatorio."
            );
        }

        this.email = email;
    }

    public abstract String getRolEnElBanco();
}