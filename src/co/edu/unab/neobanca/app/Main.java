package co.edu.unab.neobanca.app;

import co.edu.unab.neobanca.productos.CuentaAhorros;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        NumberFormat moneda =
                NumberFormat.getCurrencyInstance(
                        Locale.forLanguageTag("es-CO")
                );

        System.out.println("=== NEO-BANCA 2026 ===");

        CuentaAhorros cuenta = new CuentaAhorros(
                "AH-001",
                new BigDecimal("0.005")
        );

        System.out.println(
                "Número de producto: "
                        + cuenta.getNumeroProducto()
        );

        System.out.println(
                "Saldo inicial: "
                        + moneda.format(cuenta.consultarSaldo())
        );

        cuenta.consignar(
                new BigDecimal("1000000"),
                "Depósito inicial"
        );

        System.out.println(
                "Saldo después de consignar: "
                        + moneda.format(cuenta.consultarSaldo())
        );

        BigDecimal intereses =
                cuenta.abonarInteresesMensuales();

        System.out.println(
                "Intereses abonados: "
                        + moneda.format(intereses)
        );

        System.out.println(
                "Saldo final: "
                        + moneda.format(cuenta.consultarSaldo())
        );

        System.out.println(
                "Estado: "
                        + cuenta.getEstado()
        );
    }
}
