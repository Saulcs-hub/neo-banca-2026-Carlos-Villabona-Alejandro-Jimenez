package co.edu.unab.neobanca.productos;

import co.edu.unab.neobanca.componentes.SeguroVida;

import java.math.BigDecimal;

public class PrestamoPersonal extends ProductoCredito {

    private final int numeroCuotas;
    private final BigDecimal valorCuota;

    // Composición: el seguro pertenece al préstamo.
    private final SeguroVida seguroVida;

    public PrestamoPersonal(
            String numeroProducto,
            BigDecimal montoAprobado,
            BigDecimal tasaInteresAnual,
            int numeroCuotas,
            BigDecimal valorCuota,
            String numeroPoliza) {

        super(
                numeroProducto,
                montoAprobado,
                tasaInteresAnual
        );

        if (numeroCuotas <= 0) {
            throw new IllegalArgumentException(
                    "El número de cuotas debe ser mayor que cero."
            );
        }

        if (valorCuota == null
                || valorCuota.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "El valor de la cuota debe ser mayor que cero."
            );
        }

        this.numeroCuotas = numeroCuotas;
        this.valorCuota = valorCuota;

        this.seguroVida = new SeguroVida(
                numeroPoliza,
                montoAprobado
        );

        depositar(
                montoAprobado,
                "Desembolso inicial del préstamo"
        );
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public BigDecimal getValorCuota() {
        return valorCuota;
    }

    public SeguroVida getSeguroVida() {
        return seguroVida;
    }

    public void pagarCuota() {

        BigDecimal deudaActual =
                getDeudaActual();

        if (deudaActual.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException(
                    "El préstamo ya se encuentra pagado."
            );
        }

        BigDecimal montoAPagar =
                valorCuota.min(deudaActual);

        retirar(
                montoAPagar,
                "Pago de cuota del préstamo"
        );
    }

    public void saldarPorSiniestro() {

        BigDecimal deudaActual =
                getDeudaActual();

        BigDecimal cobertura =
                seguroVida.activarCobertura(
                        deudaActual
                );

        if (cobertura.compareTo(BigDecimal.ZERO) > 0) {

            retirar(
                    cobertura,
                    "Pago del seguro por siniestro"
            );
        }
    }
}