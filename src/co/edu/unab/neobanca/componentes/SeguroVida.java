package co.edu.unab.neobanca.componentes;

import java.math.BigDecimal;

public class SeguroVida {

    private final String numeroPoliza;
    private final BigDecimal valorAsegurado;
    private boolean activo;

    public SeguroVida(
            String numeroPoliza,
            BigDecimal valorAsegurado) {

        if (numeroPoliza == null || numeroPoliza.isBlank()) {
            throw new IllegalArgumentException(
                    "El número de póliza es obligatorio."
            );
        }

        if (valorAsegurado == null
                || valorAsegurado.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "El valor asegurado debe ser mayor que cero."
            );
        }

        this.numeroPoliza = numeroPoliza;
        this.valorAsegurado = valorAsegurado;
        this.activo = true;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public BigDecimal getValorAsegurado() {
        return valorAsegurado;
    }

    public boolean isActivo() {
        return activo;
    }

    public BigDecimal activarCobertura(BigDecimal deuda) {

        if (!activo) {
            throw new IllegalStateException(
                    "El seguro no se encuentra activo."
            );
        }

        if (deuda == null
                || deuda.compareTo(BigDecimal.ZERO) <= 0) {

            return BigDecimal.ZERO;
        }

        return deuda.min(valorAsegurado);
    }
}