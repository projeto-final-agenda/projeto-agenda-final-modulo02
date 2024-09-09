package vision;

import model.Aluno;
import util.Util;

import java.util.List;

public class TabelaConsole {
    public static void exibirCabecalho() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                       TODOS OS ALUNOS                                                  ║");
        System.out.println("╠════════════╦═════════════════════════════════╦═════════════════════════════╦═══════════════════════════╣");
        System.out.println("║ Matrícula  ║ Nome                            ║ Sobrenome                   ║ E-mail                    ║");
        System.out.println("╠════════════╬═════════════════════════════════╬═════════════════════════════╬═══════════════════════════╣");
    }

    public static void exibirLinha(String matricula, String nome, String sobrenome, String email, boolean ultimo) {
        System.out.printf("║ %-10s ║ %-31s ║ %-27s ║ %-25s ║\n",
                matricula,
                nome,
                sobrenome,
                email);

        if (!ultimo) {
            System.out.println("╠════════════╬═════════════════════════════════╬═════════════════════════════╬═══════════════════════════╣");
        }
    }

    public static void exibirRodape() {
        System.out.println("╚════════════╩═════════════════════════════════╩═════════════════════════════╩═══════════════════════════╝");
    }

    public static void exibirAlunos(List<Aluno> alunos) {
        exibirCabecalho();
        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            exibirLinha(
                    aluno.getMatricula(),
                    aluno.getNome(),
                    aluno.getSobrenome(),
                    aluno.getEmail(),
                    i == alunos.size() - 1
            );
        }
        exibirRodape();
    }

    public static void exibirMenu() {
        String menu =
                "\n╔══════════════════════════════╗\n"
                + "║      CADASTRO DE ALUNOS      ║\n"
                + "╠══════════════════════════════╣\n"
                + "║  1 - Cadastrar Aluno         ║\n"
                + "║  2 - Detalhar Aluno          ║\n"
                + "║  3 - Alterar Aluno           ║\n"
                + "║  4 - Remover Aluno           ║\n"
                + "║  5 - Listar Alunos           ║\n"
                + "╠══════════════════════════════╣\n"
                + "║  0 - Menu Principal          ║\n"
                + "╚══════════════════════════════╝\n";

        System.out.println(menu);
    }

    public static void exibirMenuTurma() {
        String menu =
                "\n╔══════════════════════════════╗\n"
                + "║     CADASTRO DE TURMAS       ║\n"
                + "╠══════════════════════════════╣\n"
                + "║  1 - Cadastrar Turmas        ║\n"
                + "║  2 - Detalhar Turmas         ║\n"
                + "║  3 - Alterar Turmas          ║\n"
                + "║  4 - Remover Turmas          ║\n"
                + "║  5 - Listar Alunos por Turma ║\n"
                + "╠══════════════════════════════╣\n"
                + "║  0 - Menu Principal          ║\n"
                + "╚══════════════════════════════╝\n";

        System.out.println(menu);
    }

    public static void exibirMenuNota() {
        String menu =
                "\n╔══════════════════════════════╗\n"
                + "║       LANÇAR NOTAS           ║\n"
                + "╠══════════════════════════════╣\n"
                + "║  1 - Adicionar Notas         ║\n"
                + "║  2 - Detalhar Notas          ║\n"
                + "║  3 - Alterar Notas           ║\n"
                + "║  4 - Remover Notas           ║\n"
                + "║  5 - Listar APROVADOS        ║\n"
                + "╠══════════════════════════════╣\n"
                + "║  0 - Menu Principal          ║\n"
                + "╚══════════════════════════════╝\n";

        System.out.println(menu);
    }

    public static void exibirMenuPrincipal() {
        String menu =
                "\n╔══════════════════════════════╗\n"
                + "║       ESCOLA TECH            ║\n"
                + "╠══════════════════════════════╣\n"
                + "║  1 - Menu das Turmas         ║\n"
                + "║  2 - Menu dos Alunos         ║\n"
                + "║  3 - Lançar Notas            ║\n"
                + "║  4 - Buscar Nota do Aluno    ║\n"
                + "╠══════════════════════════════╣\n"
                + "║  0 - Sair                    ║\n"
                + "╚══════════════════════════════╝\n";

        System.out.println(menu);
    }
}
