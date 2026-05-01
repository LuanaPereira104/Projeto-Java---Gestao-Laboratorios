package gestaolaboratorios.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 0;

    private int id;
    private String nome;
    private String email;
    private List<Laboratorio> laboratoriosGeridos;

    public Administrador(String nome, String email) {
        this.id = ++contador;
        this.nome = nome;
        setEmail(email);
        this.laboratoriosGeridos = new ArrayList<>();
    }

    public static void atualizarContador(int valorMax) {
        contador = valorMax;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }

    public void adicionarLaboratorio(Laboratorio lab) {
        if (!laboratoriosGeridos.contains(lab)) {
            laboratoriosGeridos.add(lab);
        }
    }

    public void listarLaboratorios() {
        if (laboratoriosGeridos.isEmpty()) {
            System.out.println("Este administrador não gere laboratórios.");
        } else {
            for (Laboratorio lab : laboratoriosGeridos) {
                System.out.println(lab);
            }
        }
    }
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
    @Override
    public String toString() {
        return "Administrador " + id + " - " + nome +
               " | Email: " + email +
               " | Nº Laboratorios: " + laboratoriosGeridos.size();
    }
}