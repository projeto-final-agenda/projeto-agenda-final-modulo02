package controller;

import exception.AlunoNaoEncontradoException;
import exception.ContatoInvalidoException;
import exception.EmailInvalidoException;
import exception.TurmaInvalidaException;
import model.Aluno;
import model.Turma;

import java.util.ArrayList;
import java.util.List;

public class ControllerAluno {
    private List<Aluno> alunos;
    private ControllerTurma controllerTurma;

    public ControllerAluno(ControllerTurma controllerTurma, List<Aluno> alunos) {
        this.alunos = alunos;
        this.controllerTurma = controllerTurma;
    }

    public ControllerAluno(ControllerTurma controllerTurma) {
        alunos = new ArrayList<>();
        this.controllerTurma = controllerTurma;
    }

    public void adicionarAluno(Aluno aluno) {
        try {
            Turma turma = controllerTurma.buscarTurmaPorNome(aluno.getNomeTurma().getNomeTurma());
            if (turma == null) {
                System.out.println("Erro: A turma " + aluno.getNomeTurma().getNomeTurma() + " não existe.");
                return;
            }

            if (buscarAlunoPorMatricula(aluno.getMatricula()) != null) {
                System.out.println("Erro: Matrícula indisponível. Cadastre outro número.");
                return;
            }

            turma.adicionaAluno(aluno);
            alunos.add(aluno);
            System.out.println("Aluno " + aluno.getNome() + " adicionado com sucesso.");
        } catch (AlunoNaoEncontradoException e) {
            alunos.add(aluno);
            aluno.getNomeTurma().adicionaAluno(aluno);
        }
    }

    public void removerAluno(String matricula) {
        try {
            Aluno aluno = buscarAlunoPorMatricula(matricula);
            if (aluno != null) {
                alunos.remove(aluno);
                aluno.getNomeTurma().removerAluno(aluno);
            } else {
                System.out.println("Erro: Aluno não encontrado.");
            }
        } catch (AlunoNaoEncontradoException e) {
            System.out.println("Erro ao remover aluno: " + e.getMessage());
        }
    }


    public Aluno buscarAlunoPorMatricula(String matricula) throws AlunoNaoEncontradoException {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        throw new AlunoNaoEncontradoException("Aluno com matrícula: " + matricula + " não encontrado");
    }


    public List<Aluno> listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado");
        } else {
            System.out.println("Lista de todos alunos");
        }
        return alunos;
    }

    public void alterarCadastroAluno(String matricula, String novoNome,
                                     String novoSobrenome, String novoTelefone, String novoEmail, Turma novaTurma) {
        try {
            Aluno aluno = buscarAlunoPorMatricula(matricula);
            aluno.setNome(novoNome);
            aluno.setSobrenome(novoSobrenome);
            aluno.setTelefone(novoTelefone);
            aluno.setEmail(novoEmail);
            aluno.setNomeTurma(novaTurma);
        } catch (AlunoNaoEncontradoException | EmailInvalidoException | ContatoInvalidoException e) {
            System.out.println("Erro ao alterar aluno: " + e.getMessage());
        }
    }
}