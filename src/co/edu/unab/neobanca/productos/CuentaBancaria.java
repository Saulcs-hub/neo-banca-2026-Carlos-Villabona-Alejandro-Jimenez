package co.edu.unab.neobanca.productos;

import java.math.BigDecimal;

public abstract class CuentaBancaria
        extends ProductoFinanciero {

    public CuentaBancaria(String numeroProducto) {
        super(numeroProducto);
    }

    public void consignar(
            BigDecimal monto,
            String descripcion) {

        depositar(monto, descripcion);
    }

    public void retirarEfectivo(
            BigDecimal monto,
            String descripcion) {

        retirar(monto, descripcion);
    }
}