package co.edu.unab.neobanca.productos;

import co.edu.unab.neobanca.componentes.BandaMagnetica;
import co.edu.unab.neobanca.componentes.ChipSeguridad;
import co.edu.unab.neobanca.contratos.AcumulablePuntos;

import java.math.BigDecimal;

public class TarjetaCredito
        extends ProductoCredito
        implements AcumulablePuntos {

    private final int diaDeCorte;

    private int puntosAcumulados;

    // Composición
    private final ChipSeguridad chipSeguridad;
    private final BandaMagnetica bandaMagnetica;

    public TarjetaCredito(
            String numeroProducto,
            BigDecimal montoAprobado,
            BigDecimal tasaInteresAnual,
            int diaDeCorte,
            String numeroSerieChip,
            String codigoTrack) {

        super(
                numeroProducto,
                montoAprobado,
                tasaInteresAnual
        );

        if (diaDeCorte < 1 || diaDeCorte > 31) {
            throw new IllegalArgumentException(
                    "El día de corte debe estar entre 1 y 31."
            );
        }

        this.diaDeCorte = diaDeCorte;
        this.puntosAcumulados = 0;

        // La tarjeta crea sus propios componentes.
        this.chipSeguridad =
                new ChipSeguridad(numeroSerieChip);

        this.bandaMagnetica =
                new BandaMagnetica(codigoTrack);
    }

    public int getDiaDeCorte() {
        return diaDeCorte;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public ChipSeguridad getChipSeguridad() {
        return chipSeguridad;
    }

    public BandaMagnetica getBandaMagnetica() {
        return bandaMagnetica;
    }

    public void realizarConsumo(
            BigDecimal monto,
            String comercio) {

        if (comercio == null || comercio.isBlank()) {
            throw new IllegalArgumentException(
                    "El comercio es obligatorio."
            );
        }

        depositar(
                monto,
                "Consumo en " + comercio
        );

        acumularPuntos(monto);
    }

    public void realizarPago(BigDecimal monto) {

        retirar(
                monto,
                "Pago de tarjeta de crédito"
        );
    }

    @Override
    protected void validarDeposito(BigDecimal monto) {

        super.validarDeposito(monto);

        BigDecimal nuevaDeuda =
                getDeudaActual().add(monto);

        if (nuevaDeuda.compareTo(getMontoAprobado()) > 0) {
            throw new IllegalArgumentException(
                    "El consumo supera el cupo disponible."
            );
        }
    }

    @Override
    public int acumularPuntos(BigDecimal monto) {

        if (monto == null
                || monto.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero."
            );
        }

        int nuevosPuntos =
                monto.divideToIntegralValue(
                        MONTO_POR_PUNTO
                ).intValue();

        puntosAcumulados += nuevosPuntos;

        return nuevosPuntos;
    }

    @Override
    public boolean redimirPuntos(int puntos) {

        if (puntos <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de puntos debe ser mayor que cero."
            );
        }

        if (puntos > puntosAcumulados) {
            return false;
        }

        puntosAcumulados -= puntos;

        return true;
    }

    public void cancelar() {

        chipSeguridad.destruir();
        bandaMagnetica.destruir();

        cerrar();
    }
}