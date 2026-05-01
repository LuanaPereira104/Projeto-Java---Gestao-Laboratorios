package gestaolaboratorios.backend;

public class InvestigadorRobotica extends Investigador {

    private static final long serialVersionUID = 1L;
    private String tipoSistema;

    public InvestigadorRobotica(String nome, String email, String tipoSistema) {
        super(nome, email, "Robotica");
        this.tipoSistema = tipoSistema;
    }

    public String getTipoSistema() { return tipoSistema; }
    public void setTipoSistema(String tipoSistema) { this.tipoSistema = tipoSistema; }

    @Override
    public String toString() {
        return super.toString() + " | Sistema: " + tipoSistema;
    }
}
