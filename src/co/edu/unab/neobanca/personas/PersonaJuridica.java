package co.edu.unab.neobanca.personas;

public class PersonaJuridica extends Cliente {

    private final String nit;
    private String razonSocial;

    private PersonaNatural representanteLegal;

    public PersonaJuridica(
            String identificacion,
            String nombre,
            String email,
            int puntajeCrediticio,
            String nit,
            String razonSocial,
            PersonaNatural representanteLegal) {

        super(
                identificacion,
                nombre,
                email,
                puntajeCrediticio
        );

        if (nit == null || nit.isBlank()) {
            throw new IllegalArgumentException(
                    "El NIT es obligatorio."
            );
        }

        if (razonSocial == null || razonSocial.isBlank()) {
            throw new IllegalArgumentException(
                    "La razón social es obligatoria."
            );
        }

        if (representanteLegal == null) {
            throw new IllegalArgumentException(
                    "La persona jurídica debe tener un representante legal."
            );
        }

        this.nit = nit;
        this.razonSocial = razonSocial;
        this.representanteLegal = representanteLegal;
    }

    public String getNit() {
        return nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {

        if (razonSocial == null || razonSocial.isBlank()) {
            throw new IllegalArgumentException(
                    "La razón social es obligatoria."
            );
        }

        this.razonSocial = razonSocial;
    }

    public PersonaNatural getRepresentanteLegal() {
        return representanteLegal;
    }

    public void cambiarRepresentanteLegal(
            PersonaNatural nuevoRepresentante) {

        if (nuevoRepresentante == null) {
            throw new IllegalArgumentException(
                    "El representante legal es obligatorio."
            );
        }

        this.representanteLegal = nuevoRepresentante;
    }

    @Override
    public String getRolEnElBanco() {
        return "Cliente - Persona Jurídica";
    }
}