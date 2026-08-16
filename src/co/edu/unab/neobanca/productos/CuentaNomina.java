package co.edu.unab.neobanca.productos;

import co.edu.unab.neobanca.componentes.ConvenioEmpresarial;

import java.math.BigDecimal;

public class CuentaNomina extends CuentaBancaria {

    private int puntosAcumulados;

    private ConvenioEmpresarial convenio;

    public CuentaNomina(
            String numeroProducto,
            ConvenioEmpresarial convenio) {

        super(numeroProducto);

        this.puntosAcumulados = 0;
        this.convenio = convenio;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public ConvenioEmpresarial getConvenio() {
        return convenio;
    }

    public void recibirPagoDeNomina(BigDecimal monto) {

        consignar(
                monto,
                "Pago de nómina"
        );
    }

    public void desvincularConvenio() {
        this.convenio = null;
    }
}