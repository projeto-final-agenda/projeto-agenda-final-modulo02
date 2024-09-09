package exception;

public class ContatoInvalidoException extends Exception {

    public ContatoInvalidoException() {
        super("O telefone fornecido é inválido.");
    }

    public ContatoInvalidoException(String message) {
        super(message);
    }
}
