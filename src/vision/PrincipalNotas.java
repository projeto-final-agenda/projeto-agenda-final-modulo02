package vision;

import controller.ControllerAluno;
import controller.ControllerNota;
import exception.AlunoNaoEncontradoException;
import model.Aluno;
import model.Nota;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class PrincipalNotas {
    private Scanner leitura = new Scanner(System.in);
    private ControllerNota controller;
    private List<Aluno> alunos;

    public PrincipalNotas(ControllerNota controllerNota, List<Aluno> alunos) {
        this.controller = controllerNota;
        this.alunos = alunos;
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            TabelaConsole.exibirMenuNota();
            try {
                opcao = Integer.parseInt(Util.ler(leitura, "Digite a opção:"));

                switch (opcao) {
                    case 1:
                        adicionarNota();
                        break;
                    case 2:
                        listarNotas();
                        break;
                    case 3:
                        alterarNota();
                        break;
                    case 4:
                        excluirNota();
                        break;
                    case 5:
                        listarAprovados();
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

    private void adicionarNota() {
        try {
            String matricula = Util.ler(leitura, "Digite a matrícula do aluno: ");
            Aluno aluno = controller.buscarAlunoPorMatricula(matricula);
            if (aluno != null) {
                double notaProva1 = lerNota("Digite a nota da Prova 1: ");
                double notaProva2 = lerNota("Digite a nota da Prova 2: ");
                double notaProjeto = lerNota("Digite a nota do Projeto: ");
                String disciplina = Util.ler(leitura, "Digite a disciplina: ");
                Nota nota = new Nota(notaProva1, notaProva2, notaProjeto, disciplina);
                controller.lancarNota(matricula, nota);
                Util.escrever("Nota adicionada com sucesso!");
            } else {
                Util.erro("Aluno não encontrado.");
            }
            Util.aguardarContinuacao(leitura);
        } catch (Exception e) {
            Util.erro("Erro ao adicionar a nota: " + e.getMessage());
        }
    }

    public void listarNotas() {
        try {
            String matricula = Util.ler(leitura, "Digite a matrícula do aluno:");
            Aluno aluno = encontrarAluno(matricula);
            if (aluno == null) {
                Util.erro("Aluno com matrícula " + matricula + " não encontrado.");
                return;
            }
            listarNotasAluno(aluno);
            Util.aguardarContinuacao(leitura);
        } catch (Exception e) {
            Util.erro("Erro ao listar notas: " + e.getMessage());
        }
    }


    private void listarNotasAluno(Aluno aluno) {
        List<Nota> notas = aluno.getNotas();
        if (notas == null || notas.isEmpty()) {
            Util.erro("Nenhuma nota registrada para este aluno.");
        } else {
            System.out.println("Notas do aluno " + aluno.getNome() + ":");
            for (int i = 0; i < notas.size(); i++) {
                System.out.println(notas.get(i));
                System.out.println();
            }
        }
    }

    private Aluno encontrarAluno(String matricula) {
        return controller.buscarAlunoPorMatricula(matricula);
    }

    private void alterarNota() {
        try {
            String matricula = Util.ler(leitura, "Digite a matrícula do aluno: ");
            Aluno aluno = controller.buscarAlunoPorMatricula(matricula);

            if (aluno != null) {
                listarNotasDoAlunoParaDetalhar(aluno);

                int index = Integer.parseInt(Util.ler(leitura, "Informe o índice da nota a ser alterada: ")) - 1; // Ajuste de índice
                if (index < 0 || index >= aluno.getNotas().size()) {
                    Util.erro("Índice inválido.");
                    return;
                }

                double novaNotaProva1 = lerNota("Informe o novo valor da Prova 1: ");
                double novaNotaProva2 = lerNota("Informe o novo valor da Prova 2: ");
                double novaNotaProjeto = lerNota("Informe o novo valor do Projeto: ");

                Nota notaExistente = aluno.getNotas().get(index);
                Nota novaNota = new Nota(novaNotaProva1, novaNotaProva2, novaNotaProjeto, notaExistente.getDisciplina());

                controller.alterarNota(matricula, index, novaNota);
                Util.escrever("Nota alterada com sucesso!");
                System.out.println("Nota atualizada: ");
                System.out.println(novaNota);
            } else {
                Util.erro("Aluno não encontrado.");
            }
            Util.aguardarContinuacao(leitura);
        } catch (NumberFormatException e) {
            Util.erro("Erro: Entrada inválida. Informe um número.");
        } catch (IndexOutOfBoundsException e) {
            Util.erro(e.getMessage());
        } catch (Exception e) {
            Util.erro("Erro ao alterar a nota: " + e.getMessage());
        }
    }

        private void listarNotasDoAlunoParaDetalhar(Aluno aluno) {
        if (aluno.getNotas().isEmpty()) {
            System.out.println("Nenhuma nota encontrada para o aluno " + aluno.getNome());
        } else {
            System.out.println("Notas do aluno " + aluno.getNome() + ":");
            for (Nota nota : aluno.getNotas()) {
                System.out.println(nota);
            }
        }
        Util.aguardarContinuacao(leitura);
    }



    private void excluirNota() {
        try {
            String matricula = Util.ler(leitura, "Digite a matrícula do aluno: ");
            Aluno aluno = controller.buscarAlunoPorMatricula(matricula);
            if (aluno != null) {
                listarNotasDoAlunoParaDetalhar(aluno);
                int index = Integer.parseInt(Util.ler(leitura, "Informe o índice da nota a ser removida: ")) - 1; // Ajuste para índices baseados em 0
                controller.removerNota(matricula, index);
                Util.escrever("Nota removida com sucesso!");
            } else {
                Util.erro("Aluno não encontrado.");
            }
            Util.aguardarContinuacao(leitura);
        } catch (Exception e) {
            Util.erro("Erro ao remover a nota: " + e.getMessage());
        }
    }

    private double lerNota(String mensagem) {
        double valorNota;
        do {
            valorNota = Double.parseDouble(Util.ler(leitura, mensagem));
            if (valorNota < 0 || valorNota > 10) {
                Util.erro("Nota inválida! O valor deve estar entre 0 e 10.");
            }
        } while (valorNota < 0 || valorNota > 10);
        return valorNota;
    }

    private void listarAprovados() {
        try {
            List<Aluno> alunos = controller.getTodosAlunos();

            if (alunos == null || alunos.isEmpty()) {
                System.out.println("Nenhum aluno cadastrado.");
                return;
            }
            boolean encontrouAprovados = false;
            System.out.println("Alunos aprovados:");
            for (Aluno aluno : alunos) {
                List<Nota> notas = aluno.getNotas();
                if (notas != null && !notas.isEmpty()) {
                    for (Nota nota : notas) {
                        if ("Aprovado".equals(nota.getStatus())) {
                            System.out.println(aluno.toString());
                            System.out.println();
                            encontrouAprovados = true;
                            break;
                        }
                    }
                }
            }
            if (!encontrouAprovados) {
                System.out.println("Nenhum aluno aprovado encontrado.");
            }
            Util.aguardarContinuacao(leitura);
        } catch (Exception e) {
            System.out.println("Erro ao listar alunos aprovados: " + e.getMessage());
        }
    }
}
