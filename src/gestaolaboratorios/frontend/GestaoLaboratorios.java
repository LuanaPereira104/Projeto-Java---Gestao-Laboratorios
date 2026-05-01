package gestaolaboratorios.frontend;

import gestaolaboratorios.backend.Sistema;

public class GestaoLaboratorios {

    public static void main(String[] args) {
        String ficheiro = "sistema.dat";
        Sistema sistema = Sistema.carregarDeFicheiro(ficheiro);

        MenuPrincipal menu = new MenuPrincipal(sistema, ficheiro);
        menu.iniciar();
    }
}
