package gestaolaboratorios.backend;

public class InvestigadorBiotecnologia extends Investigador {

    private static final long serialVersionUID = 1L;
    private String areaInvestigacao;

    public InvestigadorBiotecnologia(String nome, String email, String areaInvestigacao) {
        super(nome, email, "Biotecnologia");
        this.areaInvestigacao = areaInvestigacao;
    }

    public String getAreaInvestigacao() { return areaInvestigacao; }
    public void setAreaInvestigacao(String areaInvestigacao) { this.areaInvestigacao = areaInvestigacao; }

    @Override
    public String toString() {
        return super.toString() + " | Area: " + areaInvestigacao;
    }
}
