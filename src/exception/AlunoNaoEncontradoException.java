package exception;

public class AlunoNaoEncontradoException extends Exception {
    public AlunoNaoEncontradoException() {
        super("Aluno não encontrado.");
    }

    public AlunoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
