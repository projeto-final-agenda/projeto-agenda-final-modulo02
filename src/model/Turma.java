package model;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String nomeTurma;
    private String sala;
    private List<String> disciplinas;
    private List<Aluno> alunos;

    public Turma(String nomeTurma, String sala) {
        setNomeTurma(nomeTurma);
        this.sala = sala;
        this.disciplinas = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    public Turma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
        this.disciplinas = new ArrayList<>();
    }

    public String getNomeTurma() { return nomeTurma; }
    public void setNomeTurma(String nomeTurma) {
        if (nomeTurma == null || nomeTurma.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da turma não pode ser vazio.");
        }
        this.nomeTurma = nomeTurma;
    }

    public void setSala(String sala) { this.sala = sala; }
    public List<String> getDisciplinas() { return disciplinas; }
    public List<Aluno> getAlunos() {return new ArrayList<>(alunos); }

    public void adicionarDisciplina(String disciplina) {
        if (disciplina != null && !disciplina.trim().isEmpty()) {
            this.disciplinas.add(disciplina);
        }
    }

    public void removerDisciplina(String disciplina) {
        this.disciplinas.remove(disciplina);
    }

    public void adicionaAluno(Aluno aluno) {
        if (isMatriculaDuplicada(aluno.getMatricula())) {
            System.out.println("Erro: Matrícula já cadastrada na turma.");
            return;
        }
        alunos.add(aluno);
    }

    private boolean isMatriculaDuplicada(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    @Override
    public String toString() {
        return String.format(
                """
                ╔══════════════════════════════════════════════════════════════════╗
                ║                    DADOS DA TURMA                                ║
                ╠══════════════════════════════════════════════════════════════════╣
                   Nome da Turma: %s
                   Sala:          %s
                   Disciplinas:   %s
                   Alunos Matriculados: %d
                ╚══════════════════════════════════════════════════════════════════╝
                """,
                nomeTurma, sala != null ? sala : "Não disponível", disciplinas, alunos.size()
        );
    }
}
