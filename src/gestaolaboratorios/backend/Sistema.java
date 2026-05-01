package gestaolaboratorios.backend;

import gestaolaboratorios.backend.Atividade;
import gestaolaboratorios.backend.Administrador;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Investigador> investigadores;
    private List<Projeto> projetos;
    private List<Laboratorio> laboratorios;
    private List<Coordenador> coordenadores;
    private List<Administrador> administradores;

    public Sistema() {
        this.investigadores = new ArrayList<>();
        this.projetos = new ArrayList<>();
        this.laboratorios = new ArrayList<>();
        this.coordenadores = new ArrayList<>();
        this.administradores = new ArrayList<>();
    }

    public List<Investigador> getInvestigadores() { return investigadores; }
    public List<Projeto> getProjetos() { return projetos; }
    public List<Laboratorio> getLaboratorios() { return laboratorios; }
    public List<Coordenador> getCoordenadores() { return coordenadores; }
    public List<Administrador> getAdministradores() { return administradores; }

    public void adicionarInvestigador(Investigador inv) {
    for (Investigador i : investigadores) {
        if (i.getNome().equalsIgnoreCase(inv.getNome()) &&
            i.getEmail().equalsIgnoreCase(inv.getEmail())) {
            throw new IllegalArgumentException("Ja existe um investigador com este nome e email.");
        }
    }
    investigadores.add(inv);
}
    public void adicionarProjeto(Projeto p) { projetos.add(p); }
    public void adicionarLaboratorio(Laboratorio l) { laboratorios.add(l); }
    public void adicionarCoordenador(Coordenador c) {
    for (Coordenador x : coordenadores) {
        if (x.getNome().equalsIgnoreCase(c.getNome()) &&
            x.getEmail().equalsIgnoreCase(c.getEmail())) {
            throw new IllegalArgumentException("Ja existe um coordenador com este nome e email.");
        }
    }
    coordenadores.add(c);
}
    public void adicionarAdministrador(Administrador a) {
    for (Administrador x : administradores) {
        if (x.getNome().equalsIgnoreCase(a.getNome()) &&
            x.getEmail().equalsIgnoreCase(a.getEmail())) {
            throw new IllegalArgumentException("Ja existe um administrador com este nome e email.");
        }
    }
    administradores.add(a);
}

    public Investigador procurarInvestigadorPorId(int id) {
        for (Investigador i : investigadores)
            if (i.getId() == id) return i;
        return null;
    }

    public Projeto procurarProjetoPorId(int id) {
        for (Projeto p : projetos)
            if (p.getId() == id) return p;
        return null;
    }

    public Laboratorio procurarLaboratorioPorId(int id) {
        for (Laboratorio l : laboratorios)
            if (l.getId() == id) return l;
        return null;
    }

    public Coordenador procurarCoordenadorPorId(int id) {
        for (Coordenador c : coordenadores)
            if (c.getId() == id) return c;
        return null;
    }

    public Administrador procurarAdministradorPorId(int id) {
        for (Administrador a : administradores)
            if (a.getId() == id) return a;
        return null;
    }

    public boolean removerInvestigador(int id) {
        return investigadores.removeIf(i -> i.getId() == id);
    }

    public boolean removerProjeto(int id) {
        return projetos.removeIf(p -> p.getId() == id);
    }

    public boolean removerLaboratorio(int id) {
        return laboratorios.removeIf(l -> l.getId() == id);
    }

    public boolean removerCoordenador(int id) {
        return coordenadores.removeIf(c -> c.getId() == id);
    }

    public boolean removerAdministrador(int id) {
        return administradores.removeIf(a -> a.getId() == id);
    }

    public static Sistema carregarDeFicheiro(String nomeFicheiro) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
            Sistema s = (Sistema) ois.readObject();
            s.atualizarContadores();
            return s;
        } catch (IOException | ClassNotFoundException e) {
            // Se não existir ficheiro, devolve sistema vazio
            return new Sistema();
        }
    }

    public void guardarEmFicheiro(String nomeFicheiro) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFicheiro))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Erro ao guardar ficheiro: " + e.getMessage());
        }
    }

    private void atualizarContadores() {
        int maxInv = 0, maxProj = 0, maxLab = 0, maxCoord = 0, maxAdm = 0, maxAtiv = 0;

        for (Investigador i : investigadores)
            if (i.getId() > maxInv) maxInv = i.getId();
        for (Projeto p : projetos)
            if (p.getId() > maxProj) maxProj = p.getId();
        for (Laboratorio l : laboratorios)
            if (l.getId() > maxLab) maxLab = l.getId();
        for (Coordenador c : coordenadores)
            if (c.getId() > maxCoord) maxCoord = c.getId();
        for (Administrador a : administradores)
            if (a.getId() > maxAdm) maxAdm = a.getId();
        for (Projeto p : projetos)
            for (Atividade at : p.getAtividades())
                if (at.getId() > maxAtiv) maxAtiv = at.getId();

        Investigador.atualizarContador(maxInv);
        Projeto.atualizarContador(maxProj);
        Laboratorio.atualizarContador(maxLab);
        Coordenador.atualizarContador(maxCoord);
        Administrador.atualizarContador(maxAdm);
        Atividade.atualizarContador(maxAtiv);
    }
}