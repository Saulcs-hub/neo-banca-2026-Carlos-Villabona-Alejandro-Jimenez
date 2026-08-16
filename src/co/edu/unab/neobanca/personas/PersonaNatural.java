package co.edu.unab.neobanca.personas;

import java.time.LocalDate;
import java.time.Period;

public class PersonaNatural extends Cliente {

    private final LocalDate fechaNacimiento;
    private String profesion;

    public PersonaNatural(
            String identificacion,
            String nombre,
            String email,
            int puntajeCrediticio,
            LocalDate fechaNacimiento,
            String profesion) {

        super(
                identificacion,
                nombre,
                email,
                puntajeCrediticio
        );

        if (fechaNacimiento == null) {
            throw new IllegalArgumentException(
                    "La fecha de nacimiento es obligatoria."
            );
        }

        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La fecha de nacimiento no puede estar en el futuro."
            );
        }

        if (profesion == null || profesion.isBlank()) {
            throw new IllegalArgumentException(
                    "La profesión es obligatoria."
            );
        }

        this.fechaNacimiento = fechaNacimiento;
        this.profesion = profesion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {

        if (profesion == null || profesion.isBlank()) {
            throw new IllegalArgumentException(
                    "La profesión es obligatoria."
            );
        }

        this.profesion = profesion;
    }

    public int getEdad() {

        return Period.between(
                fechaNacimiento,
                LocalDate.now()
        ).getYears();
    }

    @Override
    public String getRolEnElBanco() {
        return "Cliente - Persona Natural";
    }
}