package co.edu.unab.neobanca.productos;

import co.edu.unab.neobanca.componentes.TalonarioRetiro;

import java.math.BigDecimal;

public class CuentaAhorros extends CuentaBancaria {

    private final BigDecimal tasaInteresMensual;
    private final TalonarioRetiro talonarioRetiro;

    public CuentaAhorros(
            String numeroProducto,
            BigDecimal tasaInteresMensual,
            String numeroSerieTalonario) {

        super(numeroProducto);

        if (tasaInteresMensual == null
                || tasaInteresMensual.compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "La tasa de interés mensual no puede ser negativa."
            );
        }

        this.tasaInteresMensual = tasaInteresMensual;

        this.talonarioRetiro =
                new TalonarioRetiro(numeroSerieTalonario);
    }

    public BigDecimal getTasaInteresMensual() {
        return tasaInteresMensual;
    }

    public TalonarioRetiro getTalonarioRetiro() {
        return talonarioRetiro;
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



    @Override
    protected void alCerrar() {

        talonarioRetiro.invalidar();
    }
}