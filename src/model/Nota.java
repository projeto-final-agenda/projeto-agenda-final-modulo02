package model;

public class Nota {
    private double notaProva1;
    private double notaProva2;
    private double notaProjeto;
    private String disciplina;

    public Nota(double notaProva1, double notaProva2, double notaProjeto, String disciplina) {
        this.notaProva1 = notaProva1;
        this.notaProva2 = notaProva2;
        this.notaProjeto = notaProjeto;
        this.disciplina = disciplina;
    }

    public double getNotaProva1() { return notaProva1; }
    public double getNotaProva2() { return notaProva2; }
    public double getNotaProjeto() { return notaProjeto; }
    public String getDisciplina() { return disciplina; }

    public double calcularMedia() {
        return (notaProva1 + notaProva2 + notaProjeto) / 3.0;
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

    @Override
    public String toString() {
        return String.format(
                """
                ╔════════════════════════════════════════╗
                ║               NOTAS DO ALUNO           ║
                ╠════════════════════════════════════════╣
                   Disciplina:   %s
                   Nota Prova 1: %.2f
                   Nota Prova 2: %.2f
                   Nota Projeto: %.2f
                   Média:        %.2f
                   Status:       %s
                ╚════════════════════════════════════════╝
                """,
                disciplina, notaProva1, notaProva2, notaProjeto, calcularMedia(), getStatus()
        );
    }
}
