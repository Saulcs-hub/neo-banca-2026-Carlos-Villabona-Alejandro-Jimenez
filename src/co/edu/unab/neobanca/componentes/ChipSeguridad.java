package co.edu.unab.neobanca.componentes;

public class ChipSeguridad {

    private final String numeroSerie;
    private boolean operativo;

    public ChipSeguridad(String numeroSerie) {

        if (numeroSerie == null || numeroSerie.isBlank()) {
            throw new IllegalArgumentException(
                    "El número de serie del chip es obligatorio."
            );
        }

        this.numeroSerie = numeroSerie;
        this.operativo = true;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public boolean isOperativo() {
        return operativo;
    }

    public void destruir() {
        this.operativo = false;
    }
}