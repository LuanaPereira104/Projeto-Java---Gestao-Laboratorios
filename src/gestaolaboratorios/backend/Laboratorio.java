package gestaolaboratorios.backend;

import gestaolaboratorios.backend.Projeto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Laboratorio implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 0;

    private int id;
    private String nome;
    private String localizacao;
    private List<Investigador> investigadores;
    private List<Projeto> projetos;

    public Laboratorio(String nome, String localizacao) {
        this.id = ++contador;
        this.nome = nome;
        this.localizacao = localizacao;
        this.investigadores = new ArrayList<>();
        this.projetos = new ArrayList<>();
    }

    public static void atualizarContador(int valorMax) {
        contador = valorMax;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getLocalizacao() { return localizacao; }

    public void adicionarInvestigador(Investigador inv) {
        if (!investigadores.contains(inv)) {
            investigadores.add(inv);
        }
    }

    public void adicionarProjeto(Projeto p) {
        if (!projetos.contains(p)) {
            projetos.add(p);
        }
    }

    public int getNumeroProjetosAtivos() {
        int count = 0;
        for (Projeto p : projetos) {
            if ("em curso".equalsIgnoreCase(p.getEstado())) {
                count++;
            }
        }
        return count;
    }

    public void listarInvestigadores() {
        if (investigadores.isEmpty()) {
            System.out.println("Sem investigadores registados.");
        } else {
            for (Investigador inv : investigadores) {
                System.out.println(inv);
            }
        }
    }

    public void listarProjetos() {
        if (projetos.isEmpty()) {
            System.out.println("Sem projetos registados.");
        } else {
            for (Projeto p : projetos) {
                System.out.println(p);
            }
        }
    }

    @Override
    public String toString() {
        return "Laboratorio " + id + " - " + nome + " (" + localizacao + ")" +
               " | Projetos ativos: " + getNumeroProjetosAtivos();
    }
}