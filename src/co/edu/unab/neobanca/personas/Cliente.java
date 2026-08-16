package co.edu.unab.neobanca.personas;

import co.edu.unab.neobanca.productos.ProductoFinanciero;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente extends Persona {

    private int puntajeCrediticio;

    private final List<ProductoFinanciero> productos;
    private final List<Beneficiario> beneficiarios;

    public Cliente(
            String identificacion,
            String nombre,
            String email,
            int puntajeCrediticio) {

        super(
                identificacion,
                nombre,
                email
        );

        if (puntajeCrediticio < 0) {
            throw new IllegalArgumentException(
                    "El puntaje crediticio no puede ser negativo."
            );
        }

        this.puntajeCrediticio = puntajeCrediticio;
        this.productos = new ArrayList<>();
        this.beneficiarios = new ArrayList<>();
    }

    public int getPuntajeCrediticio() {
        return puntajeCrediticio;
    }

    public void setPuntajeCrediticio(int puntajeCrediticio) {

        if (puntajeCrediticio < 0) {
            throw new IllegalArgumentException(
                    "El puntaje crediticio no puede ser negativo."
            );
        }

        this.puntajeCrediticio = puntajeCrediticio;
    }

    public boolean esSujetoDeCredito() {

        return puntajeCrediticio > 0;
    }

    public void vincularProducto(
            ProductoFinanciero producto) {

        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto financiero es obligatorio."
            );
        }

        if (!productos.contains(producto)) {
            productos.add(producto);
        }
    }

    public List<ProductoFinanciero> getProductos() {

        return List.copyOf(productos);
    }

    public void registrarBeneficiario(
            Beneficiario beneficiario) {

        if (beneficiario == null) {
            throw new IllegalArgumentException(
                    "El beneficiario es obligatorio."
            );
        }

        beneficiarios.add(beneficiario);
    }

    public List<Beneficiario> getBeneficiarios() {

        return List.copyOf(beneficiarios);
    }
}