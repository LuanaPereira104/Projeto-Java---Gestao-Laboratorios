package gestaolaboratorios.frontend;

import gestaolaboratorios.backend.Coordenador;
import gestaolaboratorios.backend.Investigador;
import gestaolaboratorios.backend.InvestigadorBiotecnologia;
import gestaolaboratorios.backend.InvestigadorEnergia;
import gestaolaboratorios.backend.InvestigadorRobotica;
import gestaolaboratorios.backend.Laboratorio;
import gestaolaboratorios.backend.Projeto;
import gestaolaboratorios.backend.Sistema;
import gestaolaboratorios.backend.Atividade;
import gestaolaboratorios.backend.Administrador;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private Sistema sistema;
    private String ficheiroPersistencia;

    public MenuPrincipal(Sistema sistema, String ficheiroPersistencia) {
        this.sistema = sistema;
        this.ficheiroPersistencia = ficheiroPersistencia;
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n-Sistema de Gestao de Laboratorios-");
            System.out.println("1. Login Investigador");
            System.out.println("2. Login Coordenador");
            System.out.println("3. Login Administrador");
            System.out.println("4. Listar Projetos");
            System.out.println("0. Sair");

            opcao = lerInteiro(sc, "Opcao: ");

            switch (opcao) {
                case 1 -> loginInvestigador(sc);
                case 2 -> loginCoordenador(sc);
                case 3 -> loginAdministrador(sc);
                case 4 -> listarProjetos();
                case 0 -> {
                    sistema.guardarEmFicheiro(ficheiroPersistencia);
                    System.out.println("Dados guardados. A sair do sistema.");
                }
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }


    private int lerInteiro(Scanner sc, String msg) {
    System.out.print(msg);
    while (!sc.hasNextInt()) {
        System.out.println("ERRO: nao é numero!");
        sc.nextLine();
        System.out.print(msg);
    }
    int valor = sc.nextInt();
    sc.nextLine();
    return valor;
}


    private void loginInvestigador(Scanner sc) {
        System.out.println("\n--- Login Investigador ---");
        System.out.println("1. Criar novo Investigador");
        System.out.println("2. Autenticar Investigador existente");
        System.out.println("0. Voltar");

        int opcao = lerInteiro(sc, "Opcao: ");

        switch (opcao) {
            case 1 -> criarInvestigador(sc);
            case 2 -> autenticarInvestigador(sc);
            case 0 -> {}
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void criarInvestigador(Scanner sc) {
    System.out.print("Nome: ");
    String nome = sc.nextLine().trim();

    System.out.print("Email: ");
    String email = sc.nextLine().trim();

    int area = -1;

    while (true) {
        System.out.println("Area científica:");
        System.out.println("1 - Biotecnologia");
        System.out.println("2 - Robotica");
        System.out.println("3 - Energia");

        area = lerInteiro(sc, "Opcao: ");

        if (area >= 1 && area <= 3) {
            break; // opção válida
        }

        System.out.println("Opcao invaalida. Escolha 1, 2 ou 3.");
    }

    Investigador inv = null;

    switch (area) {
        case 1 -> {
            System.out.print("Area de investigacao (ex: Genetica): ");
            String areaInv = sc.nextLine().trim();
            inv = new InvestigadorBiotecnologia(nome, email, areaInv);
        }
        case 2 -> {
            System.out.print("Tipo de sistema (ex: Drones): ");
            String tipoSis = sc.nextLine().trim();
            inv = new InvestigadorRobotica(nome, email, tipoSis);
        }
        case 3 -> {
            System.out.print("Fonte energetica (ex: Eolica): ");
            String fonte = sc.nextLine().trim();
            inv = new InvestigadorEnergia(nome, email, fonte);
        }
    }

    try {
        sistema.adicionarInvestigador(inv);
        System.out.println("Investigador criado: " + inv);
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}


    private void autenticarInvestigador(Scanner sc) {
        listarInvestigadores();
        int id = lerInteiro(sc, "ID do Investigador: ");
        Investigador inv = sistema.procurarInvestigadorPorId(id);
        if (inv == null) {
            System.out.println("Investigador nao encontrado.");
            return;
        }
        menuInvestigadorAutenticado(sc, inv);
    }

    private void menuInvestigadorAutenticado(Scanner sc, Investigador inv) {
        int opcao;
        do {
            System.out.println("\n--- Investigador: " + inv.getNome() + " ---");
            System.out.println("1. Ver dados pessoais");
            System.out.println("2. Editar dados pessoais");
            System.out.println("3. Ver estatisticas");
            System.out.println("0. Logout");

            opcao = lerInteiro(sc, "Opcao: ");

            switch (opcao) {
                case 1 -> System.out.println(inv);
                case 2 -> {
                    System.out.print("Novo nome (enter para manter): ");
                    String novoNome = sc.nextLine().trim();
                    if (!novoNome.isEmpty()) inv.setNome(novoNome);

                    System.out.print("Novo email (enter para manter): ");
                    String novoEmail = sc.nextLine().trim();
                    if (!novoEmail.isEmpty()) inv.setEmail(novoEmail);

                    System.out.println("Dados atualizados: " + inv);
                }
                case 3 -> System.out.println(inv.getNome() + " -> " + inv.estatisticas());
                case 0 -> {}
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }


    private void loginCoordenador(Scanner sc) {
        System.out.println("\n--- Login Coordenador ---");
        System.out.println("1. Criar novo Coordenador");
        System.out.println("2. Autenticar Coordenador existente");
        System.out.println("0. Voltar");

        int opcao = lerInteiro(sc, "Opcao: ");

        switch (opcao) {
            case 1 -> criarCoordenador(sc);
            case 2 -> autenticarCoordenador(sc);
            case 0 -> {}
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void criarCoordenador(Scanner sc) {
    System.out.print("Nome: ");
    String nome = sc.nextLine().trim();

    System.out.print("Email: ");
    String email = sc.nextLine().trim();

    int area = -1;

    while (true) {
        System.out.println("Area cientifica:");
        System.out.println("1 - Biotecnologia");
        System.out.println("2 - Robotica");
        System.out.println("3 - Energia");

        area = lerInteiro(sc, "Opçao: ");

        if (area >= 1 && area <= 3) {
            break; // válido → sai do ciclo
        }

        System.out.println("Opcao invalida. Escolha 1, 2 ou 3.\n");
    }

    String areaCientifica = switch (area) {
        case 1 -> "Biotecnologia";
        case 2 -> "Robotica";
        case 3 -> "Energia";
        default -> "";
    };

    Coordenador c = new Coordenador(nome, email, areaCientifica);

    try {
        sistema.adicionarCoordenador(c);
        System.out.println("Coordenador criado: " + c);
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}


    private void autenticarCoordenador(Scanner sc) {
        listarCoordenadores();
        int id = lerInteiro(sc, "ID do Coordenador: ");
        Coordenador c = sistema.procurarCoordenadorPorId(id);
        if (c == null) {
            System.out.println("Coordenador nao encontrado.");
            return;
        }
        menuCoordenadorAutenticado(sc, c);
    }

    private void menuCoordenadorAutenticado(Scanner sc, Coordenador coord) {
        int opcao;
        do {
            System.out.println("\n--- Coordenador: " + coord.getNome() + " ---");
            System.out.println("1. Criar Projeto");
            System.out.println("2. Adicionar Investigador a Projeto");
            System.out.println("3. Registar Atividade");
            System.out.println("4. Alterar Estado de Projeto");
            System.out.println("5. Listar Projetos que gere");
            System.out.println("0. Logout");

            opcao = lerInteiro(sc, "Opcao: ");

            switch (opcao) {
                case 1 -> criarProjeto(sc, coord);
                case 2 -> adicionarInvestigadorProjeto(sc);
                case 3 -> registarAtividade(sc);
                case 4 -> alterarEstadoProjeto(sc);
                case 5 -> coord.listarProjetos();
                case 0 -> {}
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void criarProjeto(Scanner sc, Coordenador coord) {
    System.out.print("Titulo: ");
    String titulo = sc.nextLine().trim();

    System.out.print("Descricao: ");
    String descricao = sc.nextLine().trim();

    int areaOpcao = -1;

    while (true) {
        System.out.println("Area científica:");
        System.out.println("1 - Biotecnologia");
        System.out.println("2 - Robotica");
        System.out.println("3 - Energia");

        areaOpcao = lerInteiro(sc, "Opcao: ");

        if (areaOpcao >= 1 && areaOpcao <= 3) {
            break;
        }

        System.out.println("Opcao invalida. Escolha 1, 2 ou 3.");
    }

    String area = switch (areaOpcao) {
        case 1 -> "Biotecnologia";
        case 2 -> "Robotica";
        case 3 -> "Energia";
        default -> "Desconhecida"; // nunca acontece, mas fica seguro
    };

    Projeto p = new Projeto(titulo, descricao, area);
    sistema.adicionarProjeto(p);
    coord.adicionarProjeto(p);

    System.out.println("Projeto criado: " + p);
}


    private void adicionarInvestigadorProjeto(Scanner sc) {
        if (sistema.getProjetos().isEmpty() || sistema.getInvestigadores().isEmpty()) {
            System.out.println("Necessario ter projetos e investigadores.");
            return;
        }

        listarProjetos();
        int idP = lerInteiro(sc, "ID do Projeto: ");
        Projeto escolhido = sistema.procurarProjetoPorId(idP);
        if (escolhido == null) {
            System.out.println("Projeto nao encontrado.");
            return;
        }

        listarInvestigadores();
        int idI = lerInteiro(sc, "ID do Investigador: ");
        Investigador invEscolhido = sistema.procurarInvestigadorPorId(idI);
        if (invEscolhido == null) {
            System.out.println("Investigador nao encontrado.");
            return;
        }

        escolhido.adicionarInvestigador(invEscolhido);
        System.out.println("Investigador adicionado ao projeto.");
    }

    private void registarAtividade(Scanner sc) {
        if (sistema.getProjetos().isEmpty() || sistema.getInvestigadores().isEmpty()) {
            System.out.println("Necessario ter projetos e investigadores.");
            return;
        }

        listarProjetos();
        int idP = lerInteiro(sc, "ID do Projeto: ");
        Projeto projeto = sistema.procurarProjetoPorId(idP);
        if (projeto == null) {
            System.out.println("Projeto nao encontrado.");
            return;
        }

        listarInvestigadores();
        int idI = lerInteiro(sc, "ID do Investigador: ");
        Investigador investigador = sistema.procurarInvestigadorPorId(idI);
        if (investigador == null) {
            System.out.println("Investigador nao encontrado.");
            return;
        }

        System.out.print("Tipo de atividade: ");
        String tipo = sc.nextLine().trim();
        int duracao = lerInteiro(sc, "Duracao (h): ");
        if (duracao <= 0) {
            System.out.println("Duracao invalida.");
            return;
        }

        Atividade a = new Atividade(tipo, duracao, projeto, investigador);
        System.out.println("Atividade registada: " + a);
    }

    private void alterarEstadoProjeto(Scanner sc) {
        if (sistema.getProjetos().isEmpty()) {
            System.out.println("Sem projetos.");
            return;
        }

        listarProjetos();
        int idP = lerInteiro(sc, "ID do Projeto: ");
        Projeto projeto = sistema.procurarProjetoPorId(idP);
        if (projeto == null) {
            System.out.println("Projeto nao encontrado.");
            return;
        }

        System.out.print("Novo estado (em curso/concluido/suspenso): ");
        String estado = sc.nextLine().trim();
        projeto.alterarEstado(estado);
        System.out.println("Estado atualizado: " + projeto);
    }


    private void loginAdministrador(Scanner sc) {
        System.out.println("\n--- Login Administrador ---");
        System.out.println("1. Criar novo Administrador");
        System.out.println("2. Autenticar Administrador existente");
        System.out.println("0. Voltar");

        int opcao = lerInteiro(sc, "Opcao: ");

        switch (opcao) {
            case 1 -> criarAdministrador(sc);
            case 2 -> autenticarAdministrador(sc);
            case 0 -> {}
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void criarAdministrador(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        Administrador a = new Administrador(nome, email);
        sistema.adicionarAdministrador(a);
        System.out.println("Administrador criado: " + a);
    }

    private void autenticarAdministrador(Scanner sc) {
        listarAdministradores();
        int id = lerInteiro(sc, "ID do Administrador: ");
        Administrador a = sistema.procurarAdministradorPorId(id);
        if (a == null) {
            System.out.println("Administrador nao encontrado.");
            return;
        }
        menuAdministradorAutenticado(sc, a);
    }

    private void menuAdministradorAutenticado(Scanner sc, Administrador adm) {
        int opcao;
        do {
            System.out.println("\n--- Administrador: " + adm.getNome() + " ---");
            System.out.println("1. Criar Laboratorio");
            System.out.println("2. Listar Laboratorios");
            System.out.println("3. Associar Laboratório ao Administrador");
            System.out.println("4. Remover Investigador");
            System.out.println("5. Remover Coordenador");
            System.out.println("0. Logout");

            opcao = lerInteiro(sc, "Opcao: ");

            switch (opcao) {
                case 1 -> criarLaboratorio(sc);
                case 2 -> listarLaboratorios();
                case 3 -> associarLaboratorioAdministrador(sc, adm);
                case 4 -> removerInvestigador(sc);
                case 5 -> removerCoordenador(sc);
                case 0 -> {}
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void criarLaboratorio(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();
        System.out.print("Localizacao: ");
        String local = sc.nextLine().trim();

        Laboratorio lab = new Laboratorio(nome, local);
        sistema.adicionarLaboratorio(lab);
        System.out.println("Laboratorio criado: " + lab);
    }

    private void associarLaboratorioAdministrador(Scanner sc, Administrador adm) {
        if (sistema.getLaboratorios().isEmpty()) {
            System.out.println("Sem laboratorios registados.");
            return;
        }
        listarLaboratorios();
        int id = lerInteiro(sc, "ID do Laboratorio: ");
        Laboratorio lab = sistema.procurarLaboratorioPorId(id);
        if (lab == null) {
            System.out.println("Laboratorio nao encontrado.");
            return;
        }
        adm.adicionarLaboratorio(lab);
        System.out.println("Laboratorio associado ao administrador.");
    }

    private void removerInvestigador(Scanner sc) {
        listarInvestigadores();
        int id = lerInteiro(sc, "ID do Investigador a remover: ");
        boolean removido = sistema.removerInvestigador(id);
        if (removido) {
            System.out.println("Investigador removido.");
        } else {
            System.out.println("Investigador nao encontrado.");
        }
    }

    private void removerCoordenador(Scanner sc) {
        listarCoordenadores();
        int id = lerInteiro(sc, "ID do Coordenador a remover: ");
        boolean removido = sistema.removerCoordenador(id);
        if (removido) {
            System.out.println("Coordenador removido.");
        } else {
            System.out.println("Coordenador nao encontrado.");
        }
    }


    private void listarInvestigadores() {
        List<Investigador> lista = sistema.getInvestigadores();
        if (lista.isEmpty()) {
            System.out.println("Sem investigadores registados.");
        } else {
            for (Investigador inv : lista) {
                System.out.println(inv.getId() + " - " + inv);
            }
        }
    }

    private void listarProjetos() {
        List<Projeto> lista = sistema.getProjetos();
        if (lista.isEmpty()) {
            System.out.println("Sem projetos registados.");
        } else {
            for (Projeto p : lista) {
                System.out.println(p.estatisticas());
            }
        }
    }

    private void listarLaboratorios() {
        List<Laboratorio> lista = sistema.getLaboratorios();
        if (lista.isEmpty()) {
            System.out.println("Sem laboratorios registados.");
        } else {
            for (Laboratorio lab : lista) {
                System.out.println(lab.getId() + " - " + lab);
            }
        }
    }

    private void listarCoordenadores() {
        List<Coordenador> lista = sistema.getCoordenadores();
        if (lista.isEmpty()) {
            System.out.println("Sem coordenadores registados.");
        } else {
            for (Coordenador c : lista) {
                System.out.println(c.getId() + " - " + c);
            }
        }
    }

    private void listarAdministradores() {
        List<Administrador> lista = sistema.getAdministradores();
        if (lista.isEmpty()) {
            System.out.println("Sem administradores registados.");
        } else {
            for (Administrador a : lista) {
                System.out.println(a.getId() + " - " + a);
            }
        }
    }
}