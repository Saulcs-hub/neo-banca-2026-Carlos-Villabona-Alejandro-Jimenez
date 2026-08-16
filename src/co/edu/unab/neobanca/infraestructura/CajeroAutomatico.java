package co.edu.unab.neobanca.infraestructura;

import java.math.BigDecimal;

public class CajeroAutomatico {

    private final String codigo;
    private BigDecimal efectivoDisponible;

    public CajeroAutomatico(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "El código del cajero automático es obligatorio."
            );
        }

        this.codigo = codigo;
        this.efectivoDisponible = BigDecimal.ZERO;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getEfectivoDisponible() {
        return efectivoDisponible;
    }

    public void recargar(BigDecimal monto) {

        validarMonto(monto);

        efectivoDisponible =
                efectivoDisponible.add(monto);
    }

    public void dispensar(BigDecimal monto) {

        validarMonto(monto);

        if (monto.compareTo(efectivoDisponible) > 0) {
            throw new IllegalStateException(
                    "El cajero automático no tiene suficiente efectivo."
            );
        }

        efectivoDisponible =
                efectivoDisponible.subtract(monto);
    }

    private void validarMonto(BigDecimal monto) {

        if (monto == null
                || monto.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero."
            );
        }
    }
}