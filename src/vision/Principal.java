package vision;

import controller.ControllerAluno;
import controller.ControllerNota;
import controller.ControllerTurma;
import model.Aluno;
import util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ControllerAluno controllerAluno;
    private ControllerTurma controllerTurma;
    private ControllerNota controllerNota;
    private List<Aluno> alunos;

    public Principal() {
        alunos = new ArrayList<>();
        controllerTurma = new ControllerTurma();
        controllerAluno = new ControllerAluno(controllerTurma, alunos);
        controllerNota = new ControllerNota(alunos);
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            TabelaConsole.exibirMenuPrincipal();
            try {
                opcao = Integer.parseInt(Util.ler(leitura, "Digite a opção:"));

                switch (opcao) {
                    case 1:
                        menuTurma();
                        break;
                    case 2:
                        menuAluno();
                        break;
                    case 3:
                        menuNotas();
                        break;
                    case 4:
                        buscarNotaAluno();
                        break;
                    case 0:
                        System.out.println("Saindo....");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
    }

    private void menuTurma() {
        PrincipalTurma principalTurma = new PrincipalTurma(controllerTurma);
        principalTurma.menu();
    }

    private void menuAluno() {
        PrincipalAluno principalAluno = new PrincipalAluno(controllerAluno, controllerTurma);
        principalAluno.menu();
    }

    private void menuNotas() {
        PrincipalNotas principalNotas = new PrincipalNotas(controllerNota, alunos);
        principalNotas.menu();
    }

    private void buscarNotaAluno() {
        PrincipalNotas principalNotas = new PrincipalNotas(controllerNota, alunos);
        principalNotas.listarNotas();
    }
}