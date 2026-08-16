package co.edu.unab.neobanca.componentes;

public class BandaMagnetica {

    private final String codigoTrack;
    private boolean activa;

    public BandaMagnetica(String codigoTrack) {

        if (codigoTrack == null || codigoTrack.isBlank()) {
            throw new IllegalArgumentException(
                    "El código de la banda magnética es obligatorio."
            );
        }

        this.codigoTrack = codigoTrack;
        this.activa = true;
    }

    public String getCodigoTrack() {
        return codigoTrack;
    }

    public boolean isActiva() {
        return activa;
    }

    public void destruir() {
        this.activa = false;
    }
}