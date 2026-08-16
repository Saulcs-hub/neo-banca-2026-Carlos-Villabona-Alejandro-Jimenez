package co.edu.unab.neobanca.componentes;

public class ConvenioEmpresarial {

    private final String codigoConvenio;
    private boolean vigente;

    public ConvenioEmpresarial(String codigoConvenio) {

        if (codigoConvenio == null || codigoConvenio.isBlank()) {
            throw new IllegalArgumentException(
                    "El código del convenio es obligatorio."
            );
        }

        this.codigoConvenio = codigoConvenio;
        this.vigente = true;
    }

    public String getCodigoConvenio() {
        return codigoConvenio;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void disolver() {
        this.vigente = false;
    }
}