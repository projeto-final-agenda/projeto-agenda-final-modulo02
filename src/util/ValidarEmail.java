package util;

public class ValidarEmail {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }

    public static String validarEmailObrigatorio(String email) {
        if(email == null || email.trim().isEmpty()) {
            return "E-mail é obrigatório";
        }
        return null;
    }
}
