package gestaolaboratorios.backend;

import gestaolaboratorios.backend.Coordenador;
import gestaolaboratorios.backend.Investigador;
import gestaolaboratorios.backend.Laboratorio;
import gestaolaboratorios.backend.Atividade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Projeto implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 0;

    private int id;
    private String titulo;
    private String descricao;
    private String areaCientifica;
    private String estado;

    private List<Laboratorio> laboratorios;
    private List<Investigador> equipa;
    private List<Atividade> atividades;
    private Coordenador coordenador;

    public Projeto(String titulo, String descricao, String areaCientifica) {
        this.id = ++contador;
        this.titulo = titulo;
        this.descricao = descricao;
        this.areaCientifica = areaCientifica;
        this.estado = "em curso";
        this.laboratorios = new ArrayList<>();
        this.equipa = new ArrayList<>();
        this.atividades = new ArrayList<>();
    }

    public static void atualizarContador(int valorMax) {
        contador = valorMax;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getAreaCientifica() { return areaCientifica; }
    public String getEstado() { return estado; }
    public Coordenador getCoordenador() { return coordenador; }
    public List<Atividade> getAtividades() { return atividades; }

    public void adicionarLaboratorio(Laboratorio lab) {
        if (!laboratorios.contains(lab)) {
            laboratorios.add(lab);
            lab.adicionarProjeto(this);
        }
    }

    public void adicionarInvestigador(Investigador inv) {
        if (!equipa.contains(inv)) {
            equipa.add(inv);
            inv.adicionarProjeto(this);
        }
    }

    public void adicionarAtividade(Atividade a) {
        if (!atividades.contains(a)) {
            atividades.add(a);
        }
    }

    public void definirCoordenador(Coordenador coord) {
        this.coordenador = coord;
    }

    public void alterarEstado(String novoEstado) {
        if (novoEstado.equalsIgnoreCase("em curso") ||
            novoEstado.equalsIgnoreCase("concluido") ||
            novoEstado.equalsIgnoreCase("suspenso")) {
            this.estado = novoEstado;
        } else {
            System.out.println("Estado invalido. Use: em curso, concluido ou suspenso.");
        }
    }

    public void listarLaboratorios() {
        if (laboratorios.isEmpty()) {
            System.out.println("Sem laboratorios associados.");
        } else {
            for (Laboratorio lab : laboratorios) {
                System.out.println(lab);
            }
        }
    }

    public void listarInvestigadores() {
        if (equipa.isEmpty()) {
            System.out.println("Sem investigadores na equipa.");
        } else {
            for (Investigador inv : equipa) {
                System.out.println(inv);
            }
        }
    }

    public void listarAtividades() {
        if (atividades.isEmpty()) {
            System.out.println("Sem atividades registadas.");
        } else {
            for (Atividade a : atividades) {
                System.out.println(a);
            }
        }
    }

    public String estatisticas() {
        return "Projeto " + id + " - " + titulo +
               " | Investigadores: " + equipa.size() +
               " | Atividades: " + atividades.size() +
               " | Estado: " + estado;
    }

    @Override
    public String toString() {
        return "Projeto " + id + " - " + titulo +
               " (" + areaCientifica + ") | Estado: " + estado +
               (coordenador != null ? " | Coordenador: " + coordenador.getNome() : "");
    }
}