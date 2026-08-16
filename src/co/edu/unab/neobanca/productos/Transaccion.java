package co.edu.unab.neobanca.productos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {

    private final String codigo;
    private final TipoTransaccion tipo;
    private final BigDecimal monto;
    private final String descripcion;
    private final LocalDateTime fecha;

    public Transaccion(
            TipoTransaccion tipo,
            BigDecimal monto,
            String descripcion) {

        if (tipo == null) {
            throw new IllegalArgumentException(
                    "El tipo de transacción es obligatorio."
            );
        }

        if (monto == null
                || monto.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero."
            );
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException(
                    "La descripción es obligatoria."
            );
        }

        this.codigo = UUID.randomUUID().toString();
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public LocalDate getFechaMinimaEliminacion() {

        return fecha
                .toLocalDate()
                .plusYears(10);
    }
}