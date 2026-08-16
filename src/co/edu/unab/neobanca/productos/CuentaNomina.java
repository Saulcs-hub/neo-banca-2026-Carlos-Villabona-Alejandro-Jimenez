package co.edu.unab.neobanca.productos;

import co.edu.unab.neobanca.componentes.ConvenioEmpresarial;
import co.edu.unab.neobanca.contratos.AcumulablePuntos;

import java.math.BigDecimal;

public class CuentaNomina
        extends CuentaBancaria
        implements AcumulablePuntos {

    private int puntosAcumulados;

    private ConvenioEmpresarial convenio;

    public CuentaNomina(
            String numeroProducto,
            ConvenioEmpresarial convenio) {

        super(numeroProducto);

        this.puntosAcumulados = 0;
        this.convenio = convenio;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public ConvenioEmpresarial getConvenio() {
        return convenio;
    }

    public void recibirPagoDeNomina(BigDecimal monto) {

        consignar(
                monto,
                "Pago de nómina"
        );

        acumularPuntos(monto);
    }

    public void desvincularConvenio() {
        this.convenio = null;
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
}