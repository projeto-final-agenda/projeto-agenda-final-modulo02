package vision;

import controller.ControllerTurma;
import exception.TurmaInvalidaException;
import model.Aluno;
import model.Turma;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class PrincipalTurma {
    private Scanner leitura = new Scanner(System.in);
    private ControllerTurma controller;

    public PrincipalTurma(ControllerTurma controllerTurma) {
        this.controller = controllerTurma;
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            TabelaConsole.exibirMenuTurma();
            try {
                opcao = Integer.parseInt(Util.ler(leitura, "Digite a opção:"));

                switch (opcao) {
                    case 1:
                        cadastrarTurma();
                        break;
                    case 2:
                        detalharTurma();
                        break;
                    case 3:
                        alterarTurma();
                        break;
                    case 4:
                        removerTurma();
                        break;
                    case 5:
                        listarAlunosPorTurma();
                        break;
                    case 0:
                        System.out.println("Voltando Menu Principal!!!");
                        return;
                    default:
                        Util.erro("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                Util.erro("Entrada inválida. Por favor, insira um número.");
            } catch (Exception e) {
                Util.erro("Ocorreu um erro: " + e.getMessage());
            }
        }
    }


    private void cadastrarTurma() {
        try {
            String nomeTurma = Util.ler(leitura, "Digite o nome da turma: ");
            String sala = Util.ler(leitura, "Digite a sala: ");
            Turma turma = new Turma(nomeTurma, sala);

            int adicionarMaisDisciplinas = -1;
            while (adicionarMaisDisciplinas != 0) {
                String disciplina = Util.ler(leitura, "Digite o nome da disciplina: ");
                turma.adicionarDisciplina(disciplina);
                adicionarMaisDisciplinas = Integer.parseInt(Util.ler(leitura, "Deseja adicionar outra disciplina? (1 - Sim, 0 - Não): "));
            }

            controller.cadastrarTurma(turma);
            Util.escrever("Turma cadastrada com sucesso!");

        } catch (Exception e) {
            Util.erro("Erro ao cadastrar a turma: " + e.getMessage());
        }
        Util.aguardarContinuacao(leitura);
}


    private void detalharTurma() {
        try {

            String nomeTurma = Util.ler(leitura, "Digite o nome da turma: ");
            Turma turma = controller.buscarTurmaPorNome(nomeTurma);

            if (turma != null) {
                System.out.println("Detalhes da turma:");
                System.out.println(turma.toString());
            } else {
                Util.erro("Turma não encontrada.");
            }
        } catch (Exception e) {
            Util.erro("Ocorreu um erro ao detalhar a turma: " + e.getMessage());
        }
        Util.aguardarContinuacao(leitura);
    }

    private void alterarTurma() {
        try {
            String nomeTurma = Util.ler(leitura, "Digite o nome da turma que deseja alterar: ");
            String opcao = Util.ler(leitura, "Deseja alterar a sala (sala) ou alterar disciplinas (disciplina)? ");

            if (opcao.equalsIgnoreCase("sala")) {
                String novaSala = Util.ler(leitura, "Informe a nova sala da turma: ");
                controller.alterarTurma(nomeTurma, novaSala);
            } else if (opcao.equalsIgnoreCase("disciplina")) {
                String acao = Util.ler(leitura, "Deseja adicionar (a) ou remover (r) uma disciplina? ");
                String disciplina = Util.ler(leitura, "Informe a disciplina: ");

                if (acao.equalsIgnoreCase("a")) {
                    controller.adicionarDisciplina(nomeTurma, disciplina);
                } else if (acao.equalsIgnoreCase("r")) {
                    controller.removerDisciplina(nomeTurma, disciplina);
                } else {
                    Util.erro("Ação inválida. Escolha 'a' para adicionar ou 'r' para remover.");
                }
            } else {
                Util.erro("Opção inválida. Escolha 's' para alterar a sala ou 'd' para disciplinas.");
            }
            Util.aguardarContinuacao(leitura);
        } catch (Exception e) {
            Util.erro("Ocorreu um erro ao alterar a turma: " + e.getMessage());
        }
    }

    private void removerTurma() {
        try {
            String nomeTurma = Util.ler(leitura, "Digite o nome da turma que deseja remover: ");
            controller.removerTurma(nomeTurma);
            Util.escrever("Turma removida com sucesso!");
            Util.aguardarContinuacao(leitura);
        } catch (Exception e) {
            Util.erro("Ocorreu um erro ao remover a turma: " + e.getMessage());
        }
    }

    private void listarAlunosPorTurma() {
        try {
            String nomeTurma = Util.ler(leitura, "Digite o nome da turma: ");
            Turma turma = controller.buscarTurmaPorNome(nomeTurma);

            if (turma != null) {
                List<Aluno> alunos = turma.getAlunos();

                if (alunos.isEmpty()) {
                    System.out.println("Nenhum aluno matriculado na turma " + nomeTurma + ".");
                } else {
                    System.out.println("Alunos matriculados na turma " + nomeTurma + ":");
                    TabelaConsole.exibirAlunos(alunos);
                }
            } else {
                Util.erro("Turma não encontrada.");
            }
            Util.aguardarContinuacao(leitura);
        } catch (Exception e) {
            Util.erro("Ocorreu um erro ao listar os alunos da turma: " + e.getMessage());
        }
    }
}
