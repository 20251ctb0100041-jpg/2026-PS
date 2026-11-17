import java.time.Year;

public class ValidadorLivro {

    public static void validar(String titulo, String autor, int ano)
            throws LivroInvalidoException {

        if (titulo == null || titulo.isBlank()) {
            throw new LivroInvalidoException("O título é obrigatório.");
        }

        if (autor == null || autor.isBlank()) {
            throw new LivroInvalidoException("O autor é obrigatório.");
        }

        int anoMaximo = Year.now().getValue() + 1;
        if (ano < 1450 || ano > anoMaximo) {
            throw new LivroInvalidoException(
                "Informe um ano válido entre 1450 e " + anoMaximo + "."
            );
        }
    }
}
