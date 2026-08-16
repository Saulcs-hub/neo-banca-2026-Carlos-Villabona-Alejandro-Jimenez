package co.edu.unab.neobanca.app;

import co.edu.unab.neobanca.productos.CuentaAhorros;
import co.edu.unab.neobanca.componentes.ConvenioEmpresarial;
import co.edu.unab.neobanca.productos.CuentaNomina;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import co.edu.unab.neobanca.productos.PrestamoPersonal;

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
        System.out.println();
        System.out.println("=== PRUEBA CUENTA DE NÓMINA ===");

        ConvenioEmpresarial convenio =
                new ConvenioEmpresarial("CONV-001");

        CuentaNomina cuentaNomina =
                new CuentaNomina(
                        "NOM-001",
                        convenio
                );

        cuentaNomina.recibirPagoDeNomina(
                new BigDecimal("2500000")
        );

        System.out.println(
                "Cuenta: "
                        + cuentaNomina.getNumeroProducto()
        );

        System.out.println(
                "Saldo: "
                        + moneda.format(
                        cuentaNomina.consultarSaldo()
                )
        );

        System.out.println(
                "Convenio: "
                        + cuentaNomina
                        .getConvenio()
                        .getCodigoConvenio()
        );
        cuentaNomina.desvincularConvenio();

        System.out.println(
                "Saldo después de desvincular convenio: "
                        + moneda.format(
                        cuentaNomina.consultarSaldo()
                )
        );

        System.out.println(
                "¿Tiene convenio?: "
                        + (cuentaNomina.getConvenio() != null)
        );

        System.out.println(
                "Puntos acumulados: "
                        + cuentaNomina.getPuntosAcumulados()
        );

        boolean redencion =
                cuentaNomina.redimirPuntos(500);

        System.out.println(
                "¿Redención exitosa?: "
                        + redencion
        );

        System.out.println(
                "Puntos restantes: "
                        + cuentaNomina.getPuntosAcumulados()
        );

        System.out.println();
        System.out.println(
                "=== PRUEBA PRÉSTAMO PERSONAL ==="
        );

        PrestamoPersonal prestamo =
                new PrestamoPersonal(
                        "PRE-001",
                        new BigDecimal("12000000"),
                        new BigDecimal("0.18"),
                        12,
                        new BigDecimal("1000000"),
                        "POL-001"
                );

        System.out.println(
                "Número de préstamo: "
                        + prestamo.getNumeroProducto()
        );

        System.out.println(
                "Monto aprobado: "
                        + moneda.format(
                        prestamo.getMontoAprobado()
                )
        );

        System.out.println(
                "Deuda actual: "
                        + moneda.format(
                        prestamo.getDeudaActual()
                )
        );

        System.out.println(
                "Número de cuotas: "
                        + prestamo.getNumeroCuotas()
        );

        System.out.println(
                "Valor cuota: "
                        + moneda.format(
                        prestamo.getValorCuota()
                )
        );

        System.out.println(
                "Póliza asociada: "
                        + prestamo
                        .getSeguroVida()
                        .getNumeroPoliza()
        );

        prestamo.pagarCuota();

        System.out.println(
                "Deuda después de pagar una cuota: "
                        + moneda.format(
                        prestamo.getDeudaActual()
                )
        );

        System.out.println();

        prestamo.saldarPorSiniestro();

        System.out.println(
                "Deuda después de activar el seguro: "
                        + moneda.format(
                        prestamo.getDeudaActual()
                )
        );

        try {

            prestamo.pagarCuota();

        } catch (IllegalStateException e) {

            System.out.println(
                    "Validación correcta: "
                            + e.getMessage()
            );
        }
    }
}
