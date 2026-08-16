package co.edu.unab.neobanca.productos;

import java.math.BigDecimal;

public abstract class ProductoCredito
        extends ProductoFinanciero {

    private final BigDecimal montoAprobado;
    private final BigDecimal tasaInteresAnual;

    public ProductoCredito(
            String numeroProducto,
            BigDecimal montoAprobado,
            BigDecimal tasaInteresAnual) {

        super(numeroProducto);

        this.montoAprobado = montoAprobado;
        this.tasaInteresAnual = tasaInteresAnual;
    }

    public BigDecimal getMontoAprobado() {
        return montoAprobado;
    }

    public BigDecimal getTasaInteresAnual() {
        return tasaInteresAnual;
    }

    public BigDecimal getDeudaActual() {
        return consultarSaldo();
    }
}