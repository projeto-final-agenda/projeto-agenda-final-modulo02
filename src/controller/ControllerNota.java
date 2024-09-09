package controller;

import exception.AlunoNaoEncontradoException;
import model.Aluno;
import model.Nota;

import java.util.ArrayList;
import java.util.List;

public class ControllerNota {
    private List<Aluno> alunos;
    private List<Nota> notas;

    public ControllerNota(List<Aluno> alunos) {
        this.notas = new ArrayList<>();
        this.alunos = alunos;
    }


    public void lancarNota(String matricula, Nota nota) throws AlunoNaoEncontradoException {
        Aluno aluno = encontrarAluno(matricula);
        if (aluno == null) {
            throw new AlunoNaoEncontradoException();
        }
        aluno.adicionarNota(nota);
    }

    private Aluno encontrarAluno(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }


    public void alterarNota(String matricula,int index, Nota novaNota) throws AlunoNaoEncontradoException, IndexOutOfBoundsException {
        Aluno aluno = encontrarAluno(matricula);

        if (aluno != null) {
            aluno.getNotas().set(index, novaNota);
        } else {
            throw new AlunoNaoEncontradoException("Aluno não encontrado.");
        }
    }



    public void removerNota(String matricula, int index) throws AlunoNaoEncontradoException {
        Aluno aluno = encontrarAluno(matricula);
        if (aluno != null) {
            if (index >= 0 && index < aluno.getNotas().size()) {
                aluno.getNotas().remove(index);
            } else {
                throw new IndexOutOfBoundsException("Índice de nota inválido.");
            }
        } else {
            throw new AlunoNaoEncontradoException();
        }
    }


    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    public List<Aluno> getTodosAlunos() {
        return new ArrayList<>(alunos);
    }

    public void mostrarNotas(String matricula) throws AlunoNaoEncontradoException {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            List<Nota> notas = aluno.getNotas();
            if (notas.isEmpty()) {
                System.out.println("Nenhuma nota encontrada para o aluno.");
            } else {
                for (Nota nota : notas) {
                    System.out.println(nota);
                }
            }
        } else {
            throw new AlunoNaoEncontradoException("Aluno com matrícula " + matricula + " não encontrado.");
        }
    }
}
