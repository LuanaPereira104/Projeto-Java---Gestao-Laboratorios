package gestaolaboratorios.backend;

public class InvestigadorEnergia extends Investigador {

    private static final long serialVersionUID = 1L;
    private String fonteEnergetica;

    public InvestigadorEnergia(String nome, String email, String fonteEnergetica) {
        super(nome, email, "Energia");
        this.fonteEnergetica = fonteEnergetica;
    }

    public String getFonteEnergetica() { return fonteEnergetica; }
    public void setFonteEnergetica(String fonteEnergetica) { this.fonteEnergetica = fonteEnergetica; }

    @Override
    public String toString() {
        return super.toString() + " | Fonte: " + fonteEnergetica;
    }
}
