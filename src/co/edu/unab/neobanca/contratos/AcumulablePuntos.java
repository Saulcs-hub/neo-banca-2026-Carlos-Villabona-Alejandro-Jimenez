package co.edu.unab.neobanca.contratos;

import java.math.BigDecimal;

public interface AcumulablePuntos {

    BigDecimal MONTO_POR_PUNTO =
            new BigDecimal("1000");

    int acumularPuntos(BigDecimal monto);

    boolean redimirPuntos(int puntos);
}