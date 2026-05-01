package gestaolaboratorios.backend;

import gestaolaboratorios.backend.Projeto;
import gestaolaboratorios.backend.Atividade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Investigador implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 0;

    private int id;
    private String nome;
    private String email;
    private String areaEspecializacao;

    private List<Projeto> projetos;
    private int horasTotais;
    private int atividadesRealizadas;

    public Investigador(String nome, String email, String areaEspecializacao) {
        this.id = ++contador;
        this.nome = nome;
        this.areaEspecializacao = areaEspecializacao;
        this.projetos = new ArrayList<>();
        setEmail(email);
    }

    public static void atualizarContador(int valorMax) {
        contador = valorMax;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getAreaEspecializacao() { return areaEspecializacao; }

    public void setNome(String nome) { this.nome = nome; } 
    
    public void setEmail(String email) {
    if (email == null) {
        throw new IllegalArgumentException("Email invalido: nao pode ser nulo");
    }

    int pos = email.indexOf("@");

    if (pos <= 0 || pos == email.length() - 1) {
        throw new IllegalArgumentException("Email invalido: deve ter texto antes e depois de '@'");
    }

    this.email = email;
}

    public void adicionarProjeto(Projeto p) {
        if (!projetos.contains(p)) {
            projetos.add(p);
        }
    }

    public void registarAtividade(Atividade a) {
        atividadesRealizadas++;
        horasTotais += a.getDuracao();
    }

    public String estatisticas() {
        return "Projetos: " + projetos.size() +
               ", Horas: " + horasTotais +
               ", Atividades: " + atividadesRealizadas;
    }

    @Override
    public String toString() {
        return "Investigador " + id + " - " + nome + " (" + areaEspecializacao + ")";
    }
}