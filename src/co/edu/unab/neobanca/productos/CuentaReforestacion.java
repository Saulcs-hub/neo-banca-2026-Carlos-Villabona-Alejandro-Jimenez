package co.edu.unab.neobanca.productos;

import co.edu.unab.neobanca.componentes.CertificadoEcologico;
import co.edu.unab.neobanca.contratos.ProductoSostenible;

import java.math.BigDecimal;

public class CuentaReforestacion
        extends CuentaBancaria
        implements ProductoSostenible {

    private final int arbolesFinanciados;

    /*
     * Supuesto académico para la simulación.
     * La minuta no establece una fórmula de conversión.
     */
    private static final BigDecimal KG_CO2_POR_ARBOL =
            new BigDecimal("20");

    public CuentaReforestacion(
            String numeroProducto,
            int arbolesFinanciados) {

        super(numeroProducto);

        if (arbolesFinanciados <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de árboles financiados debe ser mayor que cero."
            );
        }

        this.arbolesFinanciados = arbolesFinanciados;
    }

    public int getArbolesFinanciados() {
        return arbolesFinanciados;
    }

    @Override
    public BigDecimal calcularHuellaCarbonoEvitada() {

        return KG_CO2_POR_ARBOL.multiply(
                BigDecimal.valueOf(arbolesFinanciados)
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