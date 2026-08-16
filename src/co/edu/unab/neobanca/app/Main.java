package co.edu.unab.neobanca.app;

// Componentes
import co.edu.unab.neobanca.componentes.ConvenioEmpresarial;

// Infraestructura
import co.edu.unab.neobanca.infraestructura.CajeroAutomatico;
import co.edu.unab.neobanca.infraestructura.Computador;
import co.edu.unab.neobanca.infraestructura.Sucursal;

// Personas
import co.edu.unab.neobanca.personas.AsesorExterno;
import co.edu.unab.neobanca.personas.Beneficiario;
import co.edu.unab.neobanca.personas.Cajero;
import co.edu.unab.neobanca.personas.Cliente;
import co.edu.unab.neobanca.personas.PersonaJuridica;
import co.edu.unab.neobanca.personas.PersonaNatural;

// Productos
import co.edu.unab.neobanca.productos.CuentaAhorros;
import co.edu.unab.neobanca.productos.CuentaNomina;
import co.edu.unab.neobanca.productos.PrestamoPersonal;
import co.edu.unab.neobanca.productos.TarjetaCredito;

// Java
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        NumberFormat moneda =
                NumberFormat.getCurrencyInstance(
                        Locale.forLanguageTag("es-CO")
                );

        System.out.println("=== NEO-BANCA 2026 ===");


        // =========================================================
        // PRUEBA CUENTA DE AHORROS
        // =========================================================

        System.out.println();
        System.out.println("=== PRUEBA CUENTA DE AHORROS ===");

        CuentaAhorros cuenta =
                new CuentaAhorros(
                        "AH-001",
                        new BigDecimal("0.005"),
                        "TAL-001"
                );

        System.out.println(
                "Número de producto: "
                        + cuenta.getNumeroProducto()
        );

        System.out.println(
                "Saldo inicial: "
                        + moneda.format(
                        cuenta.consultarSaldo()
                )
        );

        cuenta.consignar(
                new BigDecimal("1000000"),
                "Depósito inicial"
        );

        System.out.println(
                "Saldo después de consignar: "
                        + moneda.format(
                        cuenta.consultarSaldo()
                )
        );

        BigDecimal intereses =
                cuenta.abonarInteresesMensuales();

        System.out.println(
                "Intereses abonados: "
                        + moneda.format(intereses)
        );

        System.out.println(
                "Saldo final: "
                        + moneda.format(
                        cuenta.consultarSaldo()
                )
        );

        System.out.println(
                "Estado: "
                        + cuenta.getEstado()
        );


        // =========================================================
        // PRUEBA CUENTA DE NÓMINA
        // =========================================================

        System.out.println();
        System.out.println("=== PRUEBA CUENTA DE NÓMINA ===");

        ConvenioEmpresarial convenio =
                new ConvenioEmpresarial(
                        "CONV-001"
                );

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


        // =========================================================
        // PRUEBA PRÉSTAMO PERSONAL
        // =========================================================

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


        // =========================================================
        // PRUEBA TARJETA DE CRÉDITO
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA TARJETA DE CRÉDITO ==="
        );

        TarjetaCredito tarjeta =
                new TarjetaCredito(
                        "TC-001",
                        new BigDecimal("5000000"),
                        new BigDecimal("0.24"),
                        15,
                        "CHIP-001",
                        "TRACK-001"
                );

        System.out.println(
                "Tarjeta: "
                        + tarjeta.getNumeroProducto()
        );

        System.out.println(
                "Cupo aprobado: "
                        + moneda.format(
                        tarjeta.getMontoAprobado()
                )
        );

        System.out.println(
                "Deuda inicial: "
                        + moneda.format(
                        tarjeta.getDeudaActual()
                )
        );

        System.out.println(
                "Día de corte: "
                        + tarjeta.getDiaDeCorte()
        );

        System.out.println(
                "Chip operativo: "
                        + tarjeta
                        .getChipSeguridad()
                        .isOperativo()
        );

        System.out.println(
                "Banda activa: "
                        + tarjeta
                        .getBandaMagnetica()
                        .isActiva()
        );

        tarjeta.realizarConsumo(
                new BigDecimal("1200000"),
                "Mercado"
        );

        System.out.println(
                "Deuda después del consumo: "
                        + moneda.format(
                        tarjeta.getDeudaActual()
                )
        );

        System.out.println(
                "Puntos acumulados: "
                        + tarjeta.getPuntosAcumulados()
        );

        tarjeta.realizarPago(
                new BigDecimal("200000")
        );

        System.out.println(
                "Deuda después del pago: "
                        + moneda.format(
                        tarjeta.getDeudaActual()
                )
        );

        tarjeta.cancelar();

        System.out.println(
                "Estado después de cancelar: "
                        + tarjeta.getEstado()
        );

        System.out.println(
                "Chip operativo después de cancelar: "
                        + tarjeta
                        .getChipSeguridad()
                        .isOperativo()
        );

        System.out.println(
                "Banda activa después de cancelar: "
                        + tarjeta
                        .getBandaMagnetica()
                        .isActiva()
        );


        // =========================================================
        // PRUEBA LÍMITE DE CUPO
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA LÍMITE DE CUPO ==="
        );

        TarjetaCredito tarjetaLimite =
                new TarjetaCredito(
                        "TC-002",
                        new BigDecimal("5000000"),
                        new BigDecimal("0.24"),
                        20,
                        "CHIP-002",
                        "TRACK-002"
                );

        try {

            tarjetaLimite.realizarConsumo(
                    new BigDecimal("6000000"),
                    "Tecnología"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Validación correcta: "
                            + e.getMessage()
            );
        }

        System.out.println(
                "Deuda después del intento: "
                        + moneda.format(
                        tarjetaLimite.getDeudaActual()
                )
        );


        // =========================================================
        // PRUEBA CICLO DE VIDA TALONARIO
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA TALONARIO DE RETIRO ==="
        );

        System.out.println(
                "Talonario: "
                        + cuenta
                        .getTalonarioRetiro()
                        .getNumeroSerie()
        );

        System.out.println(
                "Talonario vigente antes del cierre: "
                        + cuenta
                        .getTalonarioRetiro()
                        .isVigente()
        );

        cuenta.cerrar();

        System.out.println(
                "Estado después de cerrar la cuenta: "
                        + cuenta.getEstado()
        );

        System.out.println(
                "Talonario vigente después del cierre: "
                        + cuenta
                        .getTalonarioRetiro()
                        .isVigente()
        );


        // =========================================================
        // PRUEBA PERSONA NATURAL
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA PERSONA NATURAL ==="
        );

        PersonaNatural cliente =
                new PersonaNatural(
                        "1098765432",
                        "Carlos Villabona",
                        "carlos@email.com",
                        750,
                        LocalDate.of(
                                2003,
                                5,
                                10
                        ),
                        "Ingeniero de Sistemas"
                );

        System.out.println(
                "Identificación: "
                        + cliente.getIdentificacion()
        );

        System.out.println(
                "Nombre: "
                        + cliente.getNombre()
        );

        System.out.println(
                "Email: "
                        + cliente.getEmail()
        );

        System.out.println(
                "Edad: "
                        + cliente.getEdad()
        );

        System.out.println(
                "Profesión: "
                        + cliente.getProfesion()
        );

        System.out.println(
                "Puntaje crediticio: "
                        + cliente.getPuntajeCrediticio()
        );

        System.out.println(
                "Rol en el banco: "
                        + cliente.getRolEnElBanco()
        );

        cliente.vincularProducto(
                cuenta
        );

        System.out.println(
                "Cantidad de productos vinculados: "
                        + cliente
                        .getProductos()
                        .size()
        );

        System.out.println(
                "Producto vinculado: "
                        + cliente
                        .getProductos()
                        .get(0)
                        .getNumeroProducto()
        );


        // =========================================================
        // PRUEBA PERSONA JURÍDICA
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA PERSONA JURÍDICA ==="
        );

        PersonaJuridica empresa =
                new PersonaJuridica(
                        "EMP-001",
                        "NeoTech Colombia",
                        "contacto@neotech.com",
                        820,
                        "900123456-7",
                        "NeoTech Colombia S.A.S.",
                        cliente
                );

        System.out.println(
                "Identificación: "
                        + empresa.getIdentificacion()
        );

        System.out.println(
                "Razón social: "
                        + empresa.getRazonSocial()
        );

        System.out.println(
                "NIT: "
                        + empresa.getNit()
        );

        System.out.println(
                "Puntaje crediticio: "
                        + empresa.getPuntajeCrediticio()
        );

        System.out.println(
                "Rol en el banco: "
                        + empresa.getRolEnElBanco()
        );

        System.out.println(
                "Representante legal: "
                        + empresa
                        .getRepresentanteLegal()
                        .getNombre()
        );

        PersonaNatural nuevoRepresentante =
                new PersonaNatural(
                        "1099999999",
                        "Alejandro Jimenez",
                        "alejandro@email.com",
                        720,
                        LocalDate.of(
                                2002,
                                8,
                                20
                        ),
                        "Administrador de Empresas"
                );

        empresa.cambiarRepresentanteLegal(
                nuevoRepresentante
        );

        System.out.println(
                "Nuevo representante legal: "
                        + empresa
                        .getRepresentanteLegal()
                        .getNombre()
        );


        // =========================================================
        // PRUEBA BENEFICIARIOS
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA BENEFICIARIOS ==="
        );

        Beneficiario beneficiario1 =
                new Beneficiario(
                        "María Villabona",
                        "Madre",
                        "3001234567"
                );

        Beneficiario beneficiario2 =
                new Beneficiario(
                        "Laura Villabona",
                        "Hermana",
                        "3019876543"
                );

        cliente.registrarBeneficiario(
                beneficiario1
        );

        cliente.registrarBeneficiario(
                beneficiario2
        );

        System.out.println(
                "Cantidad de beneficiarios: "
                        + cliente
                        .getBeneficiarios()
                        .size()
        );

        for (Beneficiario beneficiario
                : cliente.getBeneficiarios()) {

            System.out.println(
                    "- "
                            + beneficiario.getNombre()
                            + " | "
                            + beneficiario.getParentesco()
                            + " | "
                            + beneficiario.getTelefonoContacto()
            );
        }


        // =========================================================
        // PRUEBA EMPLEADO CAJERO
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA EMPLEADO CAJERO ==="
        );

        Sucursal sucursalCentro =
                new Sucursal(
                        "SUC-001"
                );

        Computador computadorCaja =
                new Computador(
                        "PC-CAJA-001"
                );

        Cajero cajero =
                new Cajero(
                        "1099111222",
                        "Juan Pérez",
                        "juan.perez@neobanca.com",
                        "EMP-CAJ-001",
                        5
                );

        /*
         * Usamos la sucursal para gestionar la relación.
         * Sucursal conoce al cajero y el cajero conoce su sucursal.
         */
        sucursalCentro.vincularCajero(
                cajero
        );

        cajero.asignarComputador(
                computadorCaja
        );

        System.out.println(
                "Empleado: "
                        + cajero.getNombre()
        );

        System.out.println(
                "ID corporativo: "
                        + cajero.getIdCorporativo()
        );

        System.out.println(
                "Rol: "
                        + cajero.getRolEnElBanco()
        );

        System.out.println(
                "Puesto: "
                        + cajero.describirPuestoDeTrabajo()
        );

        System.out.println(
                "Cubículo: "
                        + cajero.getNumeroCubiculo()
        );

        System.out.println(
                "Sucursal: "
                        + cajero
                        .getSucursalAsignada()
                        .getCodigo()
        );

        System.out.println(
                "Computador: "
                        + cajero
                        .getComputadorAsignado()
                        .getCodigoInventario()
        );

        System.out.println(
                "Computador asignado: "
                        + computadorCaja.isAsignado()
        );

        cajero.devolverComputador();

        System.out.println(
                "Empleado tiene computador: "
                        + (cajero.getComputadorAsignado() != null)
        );

        System.out.println(
                "Computador sigue asignado: "
                        + computadorCaja.isAsignado()
        );

        System.out.println(
                "Código del computador que sigue en inventario: "
                        + computadorCaja.getCodigoInventario()
        );


        // =========================================================
        // PRUEBA ASESOR EXTERNO
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA ASESOR EXTERNO ==="
        );

        AsesorExterno asesor =
                new AsesorExterno(
                        "1099555666",
                        "Laura Gómez",
                        "laura.gomez@neobanca.com",
                        "EMP-ASE-001",
                        "Bucaramanga - Zona Norte"
                );

        System.out.println(
                "Empleado: "
                        + asesor.getNombre()
        );

        System.out.println(
                "ID corporativo: "
                        + asesor.getIdCorporativo()
        );

        System.out.println(
                "Rol: "
                        + asesor.getRolEnElBanco()
        );

        System.out.println(
                "Zona geográfica: "
                        + asesor.getZonaGeografica()
        );

        System.out.println(
                "Puesto: "
                        + asesor.describirPuestoDeTrabajo()
        );

        asesor.captarCliente(
                cliente
        );

        asesor.captarCliente(
                empresa
        );

        System.out.println(
                "Clientes captados: "
                        + asesor
                        .getClientesCaptados()
                        .size()
        );

        for (Cliente clienteCaptado
                : asesor.getClientesCaptados()) {

            System.out.println(
                    "- "
                            + clienteCaptado.getNombre()
                            + " | "
                            + clienteCaptado.getRolEnElBanco()
            );
        }


        // =========================================================
        // PRUEBA INFRAESTRUCTURA Y REASIGNACIÓN
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA INFRAESTRUCTURA Y REASIGNACIÓN ==="
        );

        CajeroAutomatico atm =
                new CajeroAutomatico(
                        "ATM-001"
                );

        atm.recargar(
                new BigDecimal("10000000")
        );

        sucursalCentro.vincularCajeroAutomatico(
                atm
        );

        System.out.println(
                "Sucursal actual: "
                        + sucursalCentro.getCodigo()
        );

        System.out.println(
                "Cajeros vinculados: "
                        + sucursalCentro
                        .getCajeros()
                        .size()
        );

        System.out.println(
                "ATM vinculados: "
                        + sucursalCentro
                        .getCajerosAutomaticos()
                        .size()
        );

        System.out.println(
                "Efectivo ATM: "
                        + moneda.format(
                        atm.getEfectivoDisponible()
                )
        );

        atm.dispensar(
                new BigDecimal("1500000")
        );

        System.out.println(
                "Efectivo ATM después de dispensar: "
                        + moneda.format(
                        atm.getEfectivoDisponible()
                )
        );

        Sucursal sucursalNorte =
                new Sucursal(
                        "SUC-002"
                );

        sucursalCentro.cerrar(
                sucursalNorte
        );

        System.out.println();
        System.out.println(
                "--- ESTADO DESPUÉS DEL CIERRE ---"
        );

        System.out.println(
                "Sucursal Centro activa: "
                        + sucursalCentro.isActiva()
        );

        System.out.println(
                "Cajeros restantes en Centro: "
                        + sucursalCentro
                        .getCajeros()
                        .size()
        );

        System.out.println(
                "ATM restantes en Centro: "
                        + sucursalCentro
                        .getCajerosAutomaticos()
                        .size()
        );

        System.out.println(
                "Cajeros en Sucursal Norte: "
                        + sucursalNorte
                        .getCajeros()
                        .size()
        );

        System.out.println(
                "ATM en Sucursal Norte: "
                        + sucursalNorte
                        .getCajerosAutomaticos()
                        .size()
        );

        System.out.println(
                "Nueva sucursal del cajero: "
                        + cajero
                        .getSucursalAsignada()
                        .getCodigo()
        );

        System.out.println(
                "Efectivo del ATM después de reasignarlo: "
                        + moneda.format(
                        atm.getEfectivoDisponible()
                )
        );


        // =========================================================
        // FIN DE PRUEBAS
        // =========================================================

        System.out.println();
        System.out.println(
                "=== FIN DE PRUEBAS NEO-BANCA 2026 ==="
        );
    }
}