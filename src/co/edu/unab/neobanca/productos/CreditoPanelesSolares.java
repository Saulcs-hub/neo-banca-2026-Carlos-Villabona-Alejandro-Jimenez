package co.edu.unab.neobanca.productos;

import co.edu.unab.neobanca.componentes.CertificadoEcologico;
import co.edu.unab.neobanca.contratos.ProductoSostenible;

import java.math.BigDecimal;

public class CreditoPanelesSolares
        extends ProductoCredito
        implements ProductoSostenible {

    private final BigDecimal kilovatiosPicoInstalados;

    /*
     * Supuesto académico para la simulación.
     * La minuta no establece una fórmula de conversión.
     */
    private static final BigDecimal KG_CO2_POR_KWP =
            new BigDecimal("500");

    public CreditoPanelesSolares(
            String numeroProducto,
            BigDecimal montoAprobado,
            BigDecimal tasaInteresAnual,
            BigDecimal kilovatiosPicoInstalados) {

        super(
                numeroProducto,
                montoAprobado,
                tasaInteresAnual
        );

        if (kilovatiosPicoInstalados == null
                || kilovatiosPicoInstalados.compareTo(
                BigDecimal.ZERO
        ) <= 0) {

            throw new IllegalArgumentException(
                    "Los kilovatios pico instalados deben ser mayores que cero."
            );
        }

        this.kilovatiosPicoInstalados =
                kilovatiosPicoInstalados;
    }

    public BigDecimal getKilovatiosPicoInstalados() {
        return kilovatiosPicoInstalados;
    }

    @Override
    public BigDecimal calcularHuellaCarbonoEvitada() {

        return kilovatiosPicoInstalados.multiply(
                KG_CO2_POR_KWP
        );
    }

    @Override
    public CertificadoEcologico generarCertificadoEcologico() {

        BigDecimal huella =
                calcularHuellaCarbonoEvitada();

        if (huella.compareTo(HUELLA_MINIMA_KG_CO2) < 0) {
            throw new IllegalStateException(
                    "La huella evitada no alcanza el mínimo requerido."
            );
        }

        return new CertificadoEcologico(
                "ECO-" + getNumeroProducto(),
                huella
        );
    }
}