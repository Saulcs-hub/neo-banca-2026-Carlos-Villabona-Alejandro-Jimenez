package co.edu.unab.neobanca.core;

import co.edu.unab.neobanca.infraestructura.Sucursal;
import co.edu.unab.neobanca.personas.Cliente;
import co.edu.unab.neobanca.personas.Empleado;
import co.edu.unab.neobanca.productos.ProductoFinanciero;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private final String nombre;

    private final List<Cliente> clientes;
    private final List<Empleado> empleados;
    private final List<Sucursal> sucursales;
    private final List<ProductoFinanciero> productos;

    public Banco(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del banco es obligatorio."
            );
        }

        this.nombre = nombre;

        this.clientes = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.sucursales = new ArrayList<>();
        this.productos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cliente> getClientes() {
        return List.copyOf(clientes);
    }

    public List<Empleado> getEmpleados() {
        return List.copyOf(empleados);
    }

    public List<Sucursal> getSucursales() {
        return List.copyOf(sucursales);
    }

    public List<ProductoFinanciero> getProductos() {
        return List.copyOf(productos);
    }

    public void registrarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente es obligatorio."
            );
        }

        if (!clientes.contains(cliente)) {
            clientes.add(cliente);
        }
    }

    public void contratarEmpleado(Empleado empleado) {

        if (empleado == null) {
            throw new IllegalArgumentException(
                    "El empleado es obligatorio."
            );
        }

        if (!empleados.contains(empleado)) {
            empleados.add(empleado);
        }
    }

    public void agregarSucursal(Sucursal sucursal) {

        if (sucursal == null) {
            throw new IllegalArgumentException(
                    "La sucursal es obligatoria."
            );
        }

        if (!sucursales.contains(sucursal)) {
            sucursales.add(sucursal);
        }
    }

    public void abrirProducto(
            Cliente cliente,
            ProductoFinanciero producto) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente es obligatorio."
            );
        }

        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto financiero es obligatorio."
            );
        }

        if (!clientes.contains(cliente)) {
            throw new IllegalStateException(
                    "El cliente debe estar registrado en el banco."
            );
        }

        if (!productos.contains(producto)) {

            productos.add(producto);
            cliente.vincularProducto(producto);
        }
    }
}