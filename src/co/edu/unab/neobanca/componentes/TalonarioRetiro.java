package co.edu.unab.neobanca.componentes;

public class TalonarioRetiro {

    private final String numeroSerie;
    private boolean vigente;

    public TalonarioRetiro(String numeroSerie) {

        if (numeroSerie == null || numeroSerie.isBlank()) {
            throw new IllegalArgumentException(
                    "El número de serie del talonario es obligatorio."
            );
        }

        this.numeroSerie = numeroSerie;
        this.vigente = true;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void invalidar() {
        this.vigente = false;
    }
}