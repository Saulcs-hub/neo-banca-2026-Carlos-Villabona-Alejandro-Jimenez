package co.edu.unab.neobanca.app;

// Core
import co.edu.unab.neobanca.core.Banco;

// Componentes
import co.edu.unab.neobanca.componentes.ConvenioEmpresarial;

// Contratos
import co.edu.unab.neobanca.contratos.ProductoSostenible;

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
import co.edu.unab.neobanca.productos.CreditoPanelesSolares;
import co.edu.unab.neobanca.productos.CuentaAhorros;
import co.edu.unab.neobanca.productos.CuentaNomina;
import co.edu.unab.neobanca.productos.CuentaReforestacion;
import co.edu.unab.neobanca.productos.PrestamoPersonal;
import co.edu.unab.neobanca.productos.TarjetaCredito;

// Java
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Main {

    private static final NumberFormat MONEDA =
            NumberFormat.getCurrencyInstance(
                    Locale.forLanguageTag("es-CO")
            );

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("        NEO-BANCA 2026");
        System.out.println("================================");

        probarCuentaAhorros();
        probarCuentaNomina();
        probarPrestamoPersonal();
        probarTarjetaCredito();

        probarPersonasYBeneficiarios();
        probarEmpleadosEInfraestructura();

        probarProductosSostenibles();
        probarIntegracionBanco();

        System.out.println();
        System.out.println("================================");
        System.out.println("     FIN DE PRUEBAS NEO-BANCA");
        System.out.println("================================");
    }

    // =========================================================
    // UTILIDAD
    // =========================================================

    private static void titulo(String texto) {

        System.out.println();
        System.out.println("=== " + texto + " ===");
    }


    // =========================================================
    // CUENTA DE AHORROS
    // =========================================================

    private static void probarCuentaAhorros() {

        titulo("PRUEBA CUENTA DE AHORROS");

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
                        + MONEDA.format(
                        cuenta.consultarSaldo()
                )
        );

        cuenta.consignar(
                new BigDecimal("1000000"),
                "Depósito inicial"
        );

        System.out.println(
                "Saldo después de consignar: "
                        + MONEDA.format(
                        cuenta.consultarSaldo()
                )
        );

        BigDecimal intereses =
                cuenta.abonarInteresesMensuales();

        System.out.println(
                "Intereses abonados: "
                        + MONEDA.format(intereses)
        );

        System.out.println(
                "Saldo final: "
                        + MONEDA.format(
                        cuenta.consultarSaldo()
                )
        );

        System.out.println(
                "Estado: "
                        + cuenta.getEstado()
        );


        // -----------------------------------------------------
        // HISTORIAL DE TRANSACCIONES
        // -----------------------------------------------------

        System.out.println();
        System.out.println(
                "--- HISTORIAL DE TRANSACCIONES ---"
        );

        for (var transaccion
                : cuenta.getTransacciones()) {

            System.out.println(
                    transaccion.getTipo()
                            + " | "
                            + MONEDA.format(
                            transaccion.getMonto()
                    )
                            + " | "
                            + transaccion.getDescripcion()
            );

            System.out.println(
                    "  Conservar hasta mínimo: "
                            + transaccion
                            .getFechaMinimaEliminacion()
            );
        }


        // -----------------------------------------------------
        // CICLO DE VIDA DEL TALONARIO
        // -----------------------------------------------------

        System.out.println();
        System.out.println(
                "--- CICLO DE VIDA DEL TALONARIO ---"
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
    }


    // =========================================================
    // CUENTA DE NÓMINA
    // =========================================================

    private static void probarCuentaNomina() {

        titulo("PRUEBA CUENTA DE NÓMINA");

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
                        + MONEDA.format(
                        cuentaNomina.consultarSaldo()
                )
        );

        System.out.println(
                "Convenio: "
                        + cuentaNomina
                        .getConvenio()
                        .getCodigoConvenio()
        );

        System.out.println(
                "Puntos acumulados: "
                        + cuentaNomina
                        .getPuntosAcumulados()
        );

        boolean redencion =
                cuentaNomina.redimirPuntos(500);

        System.out.println(
                "¿Redención exitosa?: "
                        + redencion
        );

        System.out.println(
                "Puntos restantes: "
                        + cuentaNomina
                        .getPuntosAcumulados()
        );

        cuentaNomina.desvincularConvenio();

        System.out.println(
                "¿Tiene convenio después de desvincular?: "
                        + (cuentaNomina.getConvenio() != null)
        );

        System.out.println(
                "Saldo después de desvincular convenio: "
                        + MONEDA.format(
                        cuentaNomina.consultarSaldo()
                )
        );
    }


    // =========================================================
    // PRÉSTAMO PERSONAL
    // =========================================================

    private static void probarPrestamoPersonal() {

        titulo("PRUEBA PRÉSTAMO PERSONAL");

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
                        + MONEDA.format(
                        prestamo.getMontoAprobado()
                )
        );

        System.out.println(
                "Deuda actual: "
                        + MONEDA.format(
                        prestamo.getDeudaActual()
                )
        );

        System.out.println(
                "Número de cuotas: "
                        + prestamo.getNumeroCuotas()
        );

        System.out.println(
                "Valor cuota: "
                        + MONEDA.format(
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
                        + MONEDA.format(
                        prestamo.getDeudaActual()
                )
        );

        prestamo.saldarPorSiniestro();

        System.out.println(
                "Deuda después de activar el seguro: "
                        + MONEDA.format(
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


    // =========================================================
    // TARJETA DE CRÉDITO
    // =========================================================

    private static void probarTarjetaCredito() {

        titulo("PRUEBA TARJETA DE CRÉDITO");

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
                        + MONEDA.format(
                        tarjeta.getMontoAprobado()
                )
        );

        System.out.println(
                "Deuda inicial: "
                        + MONEDA.format(
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
                        + MONEDA.format(
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
                        + MONEDA.format(
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


        // -----------------------------------------------------
        // VALIDACIÓN DE CUPO
        // -----------------------------------------------------

        System.out.println();
        System.out.println(
                "--- VALIDACIÓN DE LÍMITE DE CUPO ---"
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
                        + MONEDA.format(
                        tarjetaLimite.getDeudaActual()
                )
        );
    }


    // =========================================================
    // PERSONAS, CLIENTES Y BENEFICIARIOS
    // =========================================================

    private static void probarPersonasYBeneficiarios() {

        titulo("PRUEBA PERSONAS Y CLIENTES");

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


        // -----------------------------------------------------
        // VINCULACIÓN DE PRODUCTO
        // -----------------------------------------------------

        CuentaAhorros cuentaCliente =
                new CuentaAhorros(
                        "AH-CLI-001",
                        new BigDecimal("0.004"),
                        "TAL-CLI-001"
                );

        cliente.vincularProducto(
                cuentaCliente
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


        // -----------------------------------------------------
        // PERSONA JURÍDICA
        // -----------------------------------------------------

        System.out.println();
        System.out.println(
                "--- PERSONA JURÍDICA ---"
        );

        PersonaJuridica empresa =
                new PersonaJuridica(
                        "PJ-001",
                        "NeoTech Colombia",
                        "contacto@neotech.com",
                        820,
                        "900123456-7",
                        "NeoTech Colombia S.A.S.",
                        cliente
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
                "Rol: "
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


        // -----------------------------------------------------
        // BENEFICIARIOS
        // -----------------------------------------------------

        System.out.println();
        System.out.println(
                "--- BENEFICIARIOS ---"
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
                            + beneficiario
                            .getTelefonoContacto()
            );
        }
    }


    // =========================================================
    // EMPLEADOS E INFRAESTRUCTURA
    // =========================================================

    private static void probarEmpleadosEInfraestructura() {

        titulo("PRUEBA EMPLEADOS E INFRAESTRUCTURA");

        Sucursal sucursalCentro =
                new Sucursal(
                        "SUC-001"
                );

        Computador computador =
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

        sucursalCentro.vincularCajero(
                cajero
        );

        cajero.asignarComputador(
                computador
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
                        + computador.isAsignado()
        );

        cajero.devolverComputador();

        System.out.println(
                "Empleado tiene computador: "
                        + (cajero
                        .getComputadorAsignado()
                        != null)
        );

        System.out.println(
                "Computador sigue asignado: "
                        + computador.isAsignado()
        );

        System.out.println(
                "Computador disponible en inventario: "
                        + computador.getCodigoInventario()
        );


        // -----------------------------------------------------
        // ASESOR EXTERNO
        // -----------------------------------------------------

        System.out.println();
        System.out.println(
                "--- ASESOR EXTERNO ---"
        );

        AsesorExterno asesor =
                new AsesorExterno(
                        "1099555666",
                        "Laura Gómez",
                        "laura.gomez@neobanca.com",
                        "EMP-ASE-001",
                        "Bucaramanga - Zona Norte"
                );

        PersonaNatural clienteAsesor =
                new PersonaNatural(
                        "1098000001",
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

        PersonaJuridica empresaAsesor =
                new PersonaJuridica(
                        "PJ-ASE-001",
                        "NeoTech Colombia",
                        "contacto@neotech.com",
                        820,
                        "900123456-7",
                        "NeoTech Colombia S.A.S.",
                        clienteAsesor
                );

        asesor.captarCliente(
                clienteAsesor
        );

        asesor.captarCliente(
                empresaAsesor
        );

        System.out.println(
                "Empleado: "
                        + asesor.getNombre()
        );

        System.out.println(
                "Zona geográfica: "
                        + asesor.getZonaGeografica()
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
                            + clienteCaptado
                            .getRolEnElBanco()
            );
        }


        // -----------------------------------------------------
        // CAJERO AUTOMÁTICO
        // -----------------------------------------------------

        System.out.println();
        System.out.println(
                "--- CAJERO AUTOMÁTICO ---"
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
                        + MONEDA.format(
                        atm.getEfectivoDisponible()
                )
        );

        atm.dispensar(
                new BigDecimal("1500000")
        );

        System.out.println(
                "Efectivo después de dispensar: "
                        + MONEDA.format(
                        atm.getEfectivoDisponible()
                )
        );


        // -----------------------------------------------------
        // CIERRE Y REASIGNACIÓN
        // -----------------------------------------------------

        Sucursal sucursalNorte =
                new Sucursal(
                        "SUC-002"
                );

        sucursalCentro.cerrar(
                sucursalNorte
        );

        System.out.println();
        System.out.println(
                "--- REASIGNACIÓN POR CIERRE ---"
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
                "Cajeros en Norte: "
                        + sucursalNorte
                        .getCajeros()
                        .size()
        );

        System.out.println(
                "ATM en Norte: "
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
                "Efectivo ATM después de reasignación: "
                        + MONEDA.format(
                        atm.getEfectivoDisponible()
                )
        );
    }


    // =========================================================
    // PRODUCTOS SOSTENIBLES
    // =========================================================

    private static void probarProductosSostenibles() {

        titulo("PRUEBA PRODUCTOS SOSTENIBLES");

        CuentaReforestacion cuentaVerde =
                new CuentaReforestacion(
                        "REF-001",
                        100
                );

        CreditoPanelesSolares creditoSolar =
                new CreditoPanelesSolares(
                        "SOL-001",
                        new BigDecimal("30000000"),
                        new BigDecimal("0.12"),
                        new BigDecimal("8")
                );

        System.out.println(
                "Árboles financiados: "
                        + cuentaVerde
                        .getArbolesFinanciados()
        );

        System.out.println(
                "CO2 evitado por reforestación: "
                        + cuentaVerde
                        .calcularHuellaCarbonoEvitada()
                        + " kg"
        );

        System.out.println(
                "kWp instalados: "
                        + creditoSolar
                        .getKilovatiosPicoInstalados()
        );

        System.out.println(
                "CO2 evitado por paneles solares: "
                        + creditoSolar
                        .calcularHuellaCarbonoEvitada()
                        + " kg"
        );

        ProductoSostenible[] productosSostenibles = {
                cuentaVerde,
                creditoSolar
        };

        System.out.println();
        System.out.println(
                "--- CERTIFICADOS ECOLÓGICOS ---"
        );

        for (ProductoSostenible producto
                : productosSostenibles) {

            var certificado =
                    producto
                            .generarCertificadoEcologico();

            System.out.println(
                    "Certificado: "
                            + certificado.getCodigo()
                            + " | CO2 evitado: "
                            + certificado
                            .getKgCO2Evitados()
                            + " kg"
            );
        }
    }


    // =========================================================
    // INTEGRACIÓN DEL BANCO
    // =========================================================

    private static void probarIntegracionBanco() {

        titulo("PRUEBA INTEGRACIÓN DEL BANCO");

        Banco banco =
                new Banco(
                        "Neo-Banca 2026"
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

        PersonaJuridica empresa =
                new PersonaJuridica(
                        "PJ-BANCO-001",
                        "NeoTech Colombia",
                        "contacto@neotech.com",
                        820,
                        "900123456-7",
                        "NeoTech Colombia S.A.S.",
                        cliente
                );

        Cajero cajero =
                new Cajero(
                        "1099111222",
                        "Juan Pérez",
                        "juan.perez@neobanca.com",
                        "EMP-CAJ-001",
                        5
                );

        AsesorExterno asesor =
                new AsesorExterno(
                        "1099555666",
                        "Laura Gómez",
                        "laura.gomez@neobanca.com",
                        "EMP-ASE-001",
                        "Bucaramanga - Zona Norte"
                );

        Sucursal sucursalCentro =
                new Sucursal(
                        "SUC-BANCO-001"
                );

        Sucursal sucursalNorte =
                new Sucursal(
                        "SUC-BANCO-002"
                );

        banco.registrarCliente(
                cliente
        );

        banco.registrarCliente(
                empresa
        );

        banco.contratarEmpleado(
                cajero
        );

        banco.contratarEmpleado(
                asesor
        );

        banco.agregarSucursal(
                sucursalCentro
        );

        banco.agregarSucursal(
                sucursalNorte
        );

        CuentaAhorros cuenta =
                new CuentaAhorros(
                        "AH-INT-001",
                        new BigDecimal("0.004"),
                        "TAL-INT-001"
                );

        banco.abrirProducto(
                cliente,
                cuenta
        );

        System.out.println(
                "Banco: "
                        + banco.getNombre()
        );

        System.out.println(
                "Clientes registrados: "
                        + banco
                        .getClientes()
                        .size()
        );

        System.out.println(
                "Empleados registrados: "
                        + banco
                        .getEmpleados()
                        .size()
        );

        System.out.println(
                "Sucursales registradas: "
                        + banco
                        .getSucursales()
                        .size()
        );

        System.out.println(
                "Productos abiertos por el banco: "
                        + banco
                        .getProductos()
                        .size()
        );

        System.out.println(
                "Productos de Carlos: "
                        + cliente
                        .getProductos()
                        .size()
        );
    }
}