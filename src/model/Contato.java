package model;

import exception.ContatoInvalidoException;
import exception.EmailInvalidoException;
import util.ValidarEmail;
import util.ValidarContato;

public class Contato {
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;

    public Contato(String nome, String sobrenome, String telefone, String email)
            throws EmailInvalidoException, ContatoInvalidoException {
        this.nome = nome;
        this.sobrenome = sobrenome;
        setTelefone(telefone);
        setEmail(email);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) { this.nome = nome; }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailInvalidoException {
        String emailInvalido = ValidarEmail.validarEmailObrigatorio(email);
        if (emailInvalido != null) {
            throw new EmailInvalidoException(emailInvalido);
        }
        if (ValidarEmail.validarEmail(email)) {
            this.email = email;
        } else {
            throw new EmailInvalidoException();
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws ContatoInvalidoException {
        String contatoInvalido = ValidarContato.validarContatoObrigatorio(telefone);
        if (contatoInvalido != null) {
            throw new ContatoInvalidoException(contatoInvalido);
        }
        if (ValidarContato.validarContato(telefone)) {
            this.telefone = telefone;
        } else {
            throw new ContatoInvalidoException();
        }
    }

    @Override
    public String toString() {
        return String.format(
                """
                ╔════════════════════════════════════════╗
                ║            DADOS DO CONTATO            ║
                ╠════════════════════════════════════════╣
                   Nome:     %s %s                      
                   Telefone: %s                          
                   E-mail:   %s                          
                ╚════════════════════════════════════════╝
                """,
                nome, sobrenome, telefone, email
        );
    }
}
