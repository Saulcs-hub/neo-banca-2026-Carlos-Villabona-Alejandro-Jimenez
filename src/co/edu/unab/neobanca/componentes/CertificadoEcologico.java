package co.edu.unab.neobanca.componentes;

import java.math.BigDecimal;

public class CertificadoEcologico {

    private final String codigo;
    private final BigDecimal kgCO2Evitados;

    public CertificadoEcologico(
            String codigo,
            BigDecimal kgCO2Evitados) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "El código del certificado es obligatorio."
            );
        }

        if (kgCO2Evitados == null
                || kgCO2Evitados.compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Los kg de CO2 evitados no pueden ser negativos."
            );
        }

        this.codigo = codigo;
        this.kgCO2Evitados = kgCO2Evitados;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getKgCO2Evitados() {
        return kgCO2Evitados;
    }
}