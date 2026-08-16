package co.edu.unab.neobanca.productos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class ProductoFinanciero {

    private final String numeroProducto;

    // Caja negra: solamente esta clase controla el saldo.
    private BigDecimal saldo;
    private final List<Transaccion> transacciones;

    private EstadoProducto estado;

    public ProductoFinanciero(String numeroProducto) {

        this.numeroProducto = numeroProducto;
        this.saldo = BigDecimal.ZERO;
        this.estado = EstadoProducto.ACTIVO;
        this.transacciones = new ArrayList<>();
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

        registrarTransaccion(
                TipoTransaccion.DEPOSITO,
                monto,
                descripcion
        );
    }

    public final void retirar(
            BigDecimal monto,
            String descripcion) {

        validarProductoActivo();
        validarRetiro(monto);

        saldo = saldo.subtract(monto);

        registrarTransaccion(
                TipoTransaccion.RETIRO,
                monto,
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

        if (estado == EstadoProducto.CERRADO) {
            return;
        }

        estado = EstadoProducto.CERRADO;

        alCerrar();
    }

    protected void alCerrar() {
        // Hook para comportamientos específicos de las subclases.
    }
    private void registrarTransaccion(
            TipoTransaccion tipo,
            BigDecimal monto,
            String descripcion) {

        Transaccion transaccion =
                new Transaccion(
                        tipo,
                        monto,
                        descripcion
                );

        transacciones.add(transaccion);
    }

    public final List<Transaccion> getTransacciones() {
        return List.copyOf(transacciones);
    }
}