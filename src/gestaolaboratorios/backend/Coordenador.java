package gestaolaboratorios.backend;

import gestaolaboratorios.backend.Projeto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coordenador implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contador = 0;

    private int id;
    private String nome;
    private String email;
    private List<Projeto> projetosGeridos;
    private String areaCientifica;

    public Coordenador(String nome, String email, String areaCientifica) {
        this.id = ++contador;
        this.nome = nome;     
        this.projetosGeridos = new ArrayList<>();
        setEmail(email);
        this.areaCientifica = areaCientifica;
    }

    public static void atualizarContador(int valorMax) {
        contador = valorMax;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public int getNumeroProjetos() { return projetosGeridos.size(); }

    public void adicionarProjeto(Projeto p) {
    for (Projeto existente : projetosGeridos) {
        if (existente.getTitulo().equalsIgnoreCase(p.getTitulo())) {
            throw new IllegalArgumentException("Ja existe um projeto com este nome neste coordenador.");
        }
    }

    projetosGeridos.add(p);
    p.definirCoordenador(this);
}

    public void listarProjetos() {
        if (projetosGeridos.isEmpty()) {
            System.out.println("Este coordenador nao gere projetos.");
        } else {
            for (Projeto p : projetosGeridos) {
                System.out.println(p);
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
    public String getAreaCientifica () {
        return areaCientifica;
    }
    
    @Override
public String toString() {
    return "Coordenador " + id + " - " + nome +
           " | Email: " + email +
           " | Area: " + areaCientifica +
           " | N Projetos: " + projetosGeridos.size();
}
} 
