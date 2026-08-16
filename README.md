# 🏦 Neo-Banca 2026

Proyecto académico desarrollado en **Java** para modelar el dominio de una banca moderna mediante los principios de **Programación Orientada a Objetos (POO)** y diagramas **UML**.

## Información académica

**Proyecto:** Neo-Banca 2026  
**Materia:** Arquitectura de Software  
**Programa:** Ingeniería de Sistemas  
**Universidad:** Universidad Autónoma de Bucaramanga — UNAB

**Autores:**
- Carlos Saúl Villabona Pinilla
- Edwar Alejandro Jiménez Ríos

El sistema representa diferentes productos financieros, clientes, empleados, sucursales, infraestructura bancaria, componentes asociados a los productos, contratos mediante interfaces y reglas específicas de ciclo de vida.

## 📌 Objetivo del proyecto

Diseñar e implementar un modelo orientado a objetos para **Neo-Banca 2026**, aplicando correctamente:

- Abstracción.
- Encapsulamiento.
- Herencia.
- Polimorfismo.
- Interfaces.
- Composición.
- Agregación.
- Asociaciones.
- Multiplicidades.
- Clases abstractas.
- Enumeraciones.
- Manejo de reglas de negocio.
- Gestión de ciclos de vida de objetos.

El proyecto parte de un modelo UML y posteriormente traduce cada elemento del diseño a clases Java.

---

# 🧱 Arquitectura del proyecto

El código está organizado por paquetes según las responsabilidades del dominio.

```text
src/
└── co.edu.unab.neobanca/
    │
    ├── app/
    │   └── Main.java
    │
    ├── core/
    │   └── Banco.java
    │
    ├── personas/
    │   ├── Persona.java
    │   ├── Cliente.java
    │   ├── PersonaNatural.java
    │   ├── PersonaJuridica.java
    │   ├── Beneficiario.java
    │   ├── Empleado.java
    │   ├── Cajero.java
    │   └── AsesorExterno.java
    │
    ├── productos/
    │   ├── ProductoFinanciero.java
    │   ├── EstadoProducto.java
    │   ├── CuentaBancaria.java
    │   ├── CuentaAhorros.java
    │   ├── CuentaNomina.java
    │   ├── ProductoCredito.java
    │   ├── PrestamoPersonal.java
    │   ├── TarjetaCredito.java
    │   ├── CuentaReforestacion.java
    │   ├── CreditoPanelesSolares.java
    │   ├── TipoTransaccion.java
    │   └── Transaccion.java
    │
    ├── infraestructura/
    │   ├── Sucursal.java
    │   ├── CajeroAutomatico.java
    │   └── Computador.java
    │
    ├── componentes/
    │   ├── ChipSeguridad.java
    │   ├── BandaMagnetica.java
    │   ├── TalonarioRetiro.java
    │   ├── SeguroVida.java
    │   ├── ConvenioEmpresarial.java
    │   └── CertificadoEcologico.java
    │
    └── contratos/
        ├── AcumulablePuntos.java
        └── ProductoSostenible.java