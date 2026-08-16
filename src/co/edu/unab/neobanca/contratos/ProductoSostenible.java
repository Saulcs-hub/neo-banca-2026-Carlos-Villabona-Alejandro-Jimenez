package co.edu.unab.neobanca.contratos;

import co.edu.unab.neobanca.componentes.CertificadoEcologico;

import java.math.BigDecimal;

public interface ProductoSostenible {

    BigDecimal HUELLA_MINIMA_KG_CO2 =
            new BigDecimal("1");

    CertificadoEcologico generarCertificadoEcologico();

    BigDecimal calcularHuellaCarbonoEvitada();
}