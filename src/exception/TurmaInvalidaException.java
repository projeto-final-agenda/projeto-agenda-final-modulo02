package exception;

public class TurmaInvalidaException extends Exception{
    public TurmaInvalidaException(String message){
        super(message);
    }

    public TurmaInvalidaException(){
        super("Turma não encontrada");
    }
}
