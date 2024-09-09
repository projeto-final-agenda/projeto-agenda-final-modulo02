package model;

import exception.ContatoInvalidoException;
import exception.EmailInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Contato {
    private String matricula;
    private Turma nomeTurma;
    private List<Nota> notas;

    public Aluno(String nome, String sobrenome, String telefone, String email, String matricula, Turma nomeTurma)
            throws EmailInvalidoException, ContatoInvalidoException {
        super(nome, sobrenome, telefone, email);
        this.matricula = matricula;
        this.nomeTurma = nomeTurma;
        this.notas = new ArrayList<>();
    }

    public List<Nota> getNotas() {return notas;}
    public String getMatricula() {return matricula;}
    public Turma getNomeTurma() {return nomeTurma;}
    public void setNomeTurma(Turma turma) {this.nomeTurma = turma;}

    public double calcularMedia() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        for (Nota nota : notas) {
            soma += nota.calcularMedia();
        }
        return soma / notas.size();
    }

    public String getStatus() {
        double media = calcularMedia();
        if (media >= 7) {
            return "Aprovado";
        } else if (media >= 5) {
            return "Em Recuperação";
        } else {
            return "Reprovado";
        }
    }

    public void adicionarNota(Nota nota) {
        if (notas == null) {
            notas = new ArrayList<>();
        }
        notas.add(nota);
    }

    @Override
    public String toString() {
        return String.format(
                """
                ╔════════════════════════════════════════╗
                ║               DADOS DO ALUNO           ║
                ╠════════════════════════════════════════╣
                   Nome:          %s %s                
                   Telefone:      %s                 
                   E-mail:        %s                 
                   Turma:         %s  
                   Disciplina:    %s              
                ╠════════════════════════════════════════╣
                ║               NOTAS DO ALUNO           ║
                ╠════════════════════════════════════════╣
                   Média:          %.2f             
                   Status:         %s
                ╚════════════════════════════════════════╝
                """,
                super.getNome(), super.getSobrenome(), super.getTelefone(), super.getEmail(),
                nomeTurma != null ? nomeTurma.getNomeTurma() : "Não disponível",
                nomeTurma != null ? nomeTurma.getDisciplinas() : "Não disponível",
                calcularMedia(),
                getStatus()
        );
    }


    public String toStringCadastro() {
        return String.format(
                """
                ╔════════════════════════════════════════╗
                ║               DADOS DO ALUNO           ║
                ╠════════════════════════════════════════╣
                   Nome:          %s %s                
                   Telefone:      %s                 
                   E-mail:        %s                 
                   Matrícula:     %s                 
                   Turma:         %s
                ╚════════════════════════════════════════╝
                """,
                super.getNome(), super.getSobrenome(), super.getTelefone(), super.getEmail(),
                matricula,
                nomeTurma != null ? nomeTurma.getNomeTurma() : "Não disponível"
        );
    }
}
