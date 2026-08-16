package co.edu.unab.neobanca.productos;

import java.math.BigDecimal;

public class CuentaAhorros extends CuentaBancaria {

    private final BigDecimal tasaInteresMensual;

    public CuentaAhorros(
            String numeroProducto,
            BigDecimal tasaInteresMensual) {

        super(numeroProducto);

        if (tasaInteresMensual == null
                || tasaInteresMensual.compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "La tasa de interés mensual no puede ser negativa."
            );
        }

        this.tasaInteresMensual = tasaInteresMensual;
    }

    public BigDecimal getTasaInteresMensual() {
        return tasaInteresMensual;
    }

    public BigDecimal abonarInteresesMensuales() {

        BigDecimal intereses =
                consultarSaldo().multiply(tasaInteresMensual);

        if (intereses.compareTo(BigDecimal.ZERO) > 0) {
            depositar(
                    intereses,
                    "Abono de intereses mensuales"
            );
        }

        return intereses;
    }
}