package co.edu.unab.neobanca.productos;

import java.math.BigDecimal;

public abstract class ProductoFinanciero {

    private final String numeroProducto;

    // Caja negra: solamente esta clase controla el saldo.
    private BigDecimal saldo;

    private EstadoProducto estado;

    public ProductoFinanciero(String numeroProducto) {

        this.numeroProducto = numeroProducto;
        this.saldo = BigDecimal.ZERO;
        this.estado = EstadoProducto.ACTIVO;
    }

    public final String getNumeroProducto() {
        return numeroProducto;
    }

    public final BigDecimal consultarSaldo() {
        return saldo;
    }

    public final EstadoProducto getEstado() {
        return estado;
    }

    public final void depositar(BigDecimal monto, String descripcion) {

        validarProductoActivo();
        validarDeposito(monto);

        saldo = saldo.add(monto);

        System.out.println(
                "Depósito realizado: " +
                        monto +
                        " - " +
                        descripcion
        );
    }

    public final void retirar(BigDecimal monto, String descripcion) {

        validarProductoActivo();
        validarRetiro(monto);

        saldo = saldo.subtract(monto);

        System.out.println(
                "Retiro realizado: " +
                        monto +
                        " - " +
                        descripcion
        );
    }

    protected void validarDeposito(BigDecimal monto) {

        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "El monto del depósito debe ser mayor que cero."
            );
        }
    }

    protected void validarRetiro(BigDecimal monto) {

        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "El monto del retiro debe ser mayor que cero."
            );
        }

        if (saldo.compareTo(monto) < 0) {
            throw new IllegalArgumentException(
                    "Saldo insuficiente."
            );
        }
    }

    private void validarProductoActivo() {

        if (estado != EstadoProducto.ACTIVO) {
            throw new IllegalStateException(
                    "El producto no se encuentra activo."
            );
        }
    }

    public final void cerrar() {
        estado = EstadoProducto.CERRADO;
    }
}