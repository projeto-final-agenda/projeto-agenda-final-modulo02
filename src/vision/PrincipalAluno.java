package vision;

import controller.ControllerAluno;
import controller.ControllerTurma;
import exception.AlunoNaoEncontradoException;
import exception.ContatoInvalidoException;
import exception.EmailInvalidoException;
import model.Aluno;
import model.Turma;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class PrincipalAluno {
    private Scanner leitura = new Scanner(System.in);
    private ControllerAluno controllerAluno;
    private ControllerTurma controllerTurma;

    public PrincipalAluno(ControllerAluno controllerAluno, ControllerTurma controllerTurma) {
        this.controllerAluno = controllerAluno;
        this.controllerTurma = controllerTurma;
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            TabelaConsole.exibirMenu();
            try {
                opcao = Integer.parseInt(Util.ler(leitura, "Digite a opção:"));

                switch (opcao) {
                    case 1:
                        adicionarAluno();
                        break;
                    case 2:
                        detalharAluno();
                        break;
                    case 3:
                        alterarAluno();
                        break;
                    case 4:
                        removerAluno();
                        break;
                    case 5:
                        listarTodosAlunos();
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

    private void adicionarAluno() {
        try {
            String nome = Util.ler(leitura, "Informe o nome do aluno: ");
            String sobrenome = Util.ler(leitura, "Informe o sobrenome do aluno: ");
            String email = Util.ler(leitura, "Informe o e-mail do aluno: ");
            String telefone = Util.ler(leitura, "Informe o telefone do aluno: ");
            String matricula = Util.ler(leitura, "Digite a matrícula do aluno: ");
            String nomeTurma = Util.ler(leitura, "Digite o nome da turma: ");

            Turma turma = controllerTurma.buscarTurmaPorNome(nomeTurma);
            if (turma == null) {
                Util.erro("Erro: A turma " + nomeTurma + " não existe.");
                return;
            }

            Aluno aluno = new Aluno(nome, sobrenome, telefone, email, matricula, turma);
            controllerAluno.adicionarAluno(aluno);
            System.out.println("Aluno cadastrado com sucesso!!!");
            Util.aguardarContinuacao(leitura);
        } catch (EmailInvalidoException | ContatoInvalidoException e) {
            Util.erro("Erro ao adicionar o aluno: " + e.getMessage());
        } catch (Exception e) {
            Util.erro("Ocorreu um erro ao adicionar o aluno: " + e.getMessage());
        }
    }

    private void detalharAluno() {
        try {
            String matricula = Util.ler(leitura, "Digite o número da matrícula do aluno: ");
            System.out.println("Matrícula fornecida: " + matricula);
            Aluno aluno = controllerAluno.buscarAlunoPorMatricula(matricula);
            System.out.println("Dados do aluno: ");
            System.out.println(aluno.toStringCadastro());
            Util.aguardarContinuacao(leitura);
        } catch (AlunoNaoEncontradoException e) {
            Util.erro("Aluno não encontrado: " + e.getMessage());
        }
    }

    private void alterarAluno() {
        try {
            String matricula = Util.ler(leitura, "Digite a matrícula do aluno que deseja alterar: ");

            Aluno aluno = controllerAluno.buscarAlunoPorMatricula(matricula);

            String novoNome = Util.ler(leitura, "Informe o novo nome do aluno: ");
            String novoSobrenome = Util.ler(leitura, "Informe o novo sobrenome do aluno: ");
            String novoEmail = Util.ler(leitura, "Informe o novo e-mail do aluno: ");
            String novoTelefone = Util.ler(leitura, "Informe o novo telefone do aluno: ");
            String novoNomeTurma = Util.ler(leitura, "Digite o novo nome da turma: ");

            Turma novaTurma = new Turma(novoNomeTurma);

            controllerAluno.alterarCadastroAluno(
                    matricula,
                    novoNome,
                    novoSobrenome,
                    novoTelefone,
                    novoEmail,
                    novaTurma
            );

            System.out.println("Dados do aluno alterados com sucesso!");
            Util.aguardarContinuacao(leitura);
        } catch (AlunoNaoEncontradoException e) {
            Util.erro("Aluno não encontrado: " + e.getMessage());
        } catch (Exception e) {
            Util.erro("Ocorreu um erro ao alterar o aluno: " + e.getMessage());
        }
    }

    private void removerAluno() {
        try {
            String matricula = Util.ler(leitura, "Digite a matrícula do aluno que deseja remover: ");
            controllerAluno.removerAluno(matricula);
            System.out.println("Aluno removido com sucesso!");
            Util.aguardarContinuacao(leitura);
        } catch (Exception e) {
            Util.erro("Ocorreu um erro ao remover o aluno: " + e.getMessage());
        }
    }


    private void listarTodosAlunos() {
        try {
            List<Aluno> alunos = controllerAluno.listarAlunos();
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno cadastrado.");
            } else {
                TabelaConsole.exibirAlunos(alunos);
            }
            Util.aguardarContinuacao(leitura);
        } catch (Exception e) {
            Util.erro("Ocorreu um erro ao listar os alunos: " + e.getMessage());
        }
    }

}
