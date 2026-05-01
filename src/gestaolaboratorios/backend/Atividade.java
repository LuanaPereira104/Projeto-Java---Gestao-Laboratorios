package gestaolaboratorios.backend;

import java.io.Serializable;
import java.time.LocalDate;

public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 0;

    private int id;
    private String tipo;
    private LocalDate data;
    private int duracao;

    private Projeto projetoAssociado;
    private Investigador investigadorResponsavel;

    public Atividade(String tipo, int duracao, Projeto projeto, Investigador investigador) {
        this.id = ++contador;
        this.tipo = tipo;
        this.duracao = duracao;
        this.data = LocalDate.now();
        this.projetoAssociado = projeto;
        this.investigadorResponsavel = investigador;

        investigador.registarAtividade(this);
        projeto.adicionarAtividade(this);
    }

    public static void atualizarContador(int valorMax) {
        contador = valorMax;
    }

    public int getId() { return id; }
    public int getDuracao() { return duracao; }

    @Override
    public String toString() {
        return "Atividade " + id + " - " + tipo +
               " | Projeto: " + projetoAssociado.getTitulo() +
               " | Investigador: " + investigadorResponsavel.getNome() +
               " | Data: " + data +
               " | Duracao: " + duracao + "h";
    }
}