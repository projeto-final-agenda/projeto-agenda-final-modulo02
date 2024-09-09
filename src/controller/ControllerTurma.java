package controller;

import exception.TurmaInvalidaException;
import model.Turma;

import java.util.ArrayList;
import java.util.List;

public class ControllerTurma {
    private List<Turma> turmas;


    public ControllerTurma() {
        turmas = new ArrayList<>();
    }

    public void cadastrarTurma(Turma turma){
        turmas.add(turma);
    }

    public Turma buscarTurmaPorNome(String nomeTurma) {
        for (Turma turma : turmas) {
            if (turma.getNomeTurma().equals(nomeTurma)) {
                return turma;
            }
        }
        return null;
    }

    public void alterarTurma(String nomeTurma, String novaSala) throws TurmaInvalidaException {
        Turma turma = buscarTurmaPorNome(nomeTurma);
        if (turma != null) {
            turma.setSala(novaSala);
            System.out.println("Dados da turma alterados com sucesso!");
        } else {
            throw new TurmaInvalidaException("Turma não encontrada para alteração.");
        }
    }

    public void adicionarDisciplina(String nomeTurma, String disciplina) throws TurmaInvalidaException {
        Turma turma = buscarTurmaPorNome(nomeTurma);
        if (turma != null) {
            turma.adicionarDisciplina(disciplina);
            System.out.println("Disciplina adicionada com sucesso!");
        } else {
            throw new TurmaInvalidaException("Turma não encontrada para adicionar disciplina.");
        }
    }

    public void removerDisciplina(String nomeTurma, String disciplina) throws TurmaInvalidaException {
        Turma turma = buscarTurmaPorNome(nomeTurma);
        if (turma != null) {
            turma.removerDisciplina(disciplina);
            System.out.println("Disciplina removida com sucesso!");
        } else {
            throw new TurmaInvalidaException("Turma não encontrada para remover disciplina.");
        }
    }

    public void removerTurma(String nomeTurma) throws TurmaInvalidaException {
        Turma turma = buscarTurmaPorNome(nomeTurma);
        if (turma != null) {
            turmas.remove(turma);
            System.out.println("Turma removida com sucesso!");
        } else {
            throw new TurmaInvalidaException("Turma não encontrada para remoção.");
        }
    }
}
