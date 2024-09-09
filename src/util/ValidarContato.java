package util;

public class ValidarContato {
    public static boolean validarContato(String telefone) {
        String telefoneLimpo = telefone.replaceAll("\\D", "");
        return telefoneLimpo.length() == 10 || telefoneLimpo.length() == 11;
    }

    public static String validarContatoObrigatorio(String telefone) {
        if(telefone == null || telefone.trim().isEmpty()) {
            return "Telefone é obrigatório";
        }
        return null;
    }
}
