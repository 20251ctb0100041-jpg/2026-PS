import java.util.Scanner;

public class TesteBuscarLivro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivroDAO dao = new LivroDAO();

        System.out.println("=== TESTE DE BUSCA POR ID ===");
        System.out.print("Digite o ID do livro (ou 0 para sair): ");

        if (!scanner.hasNextInt()) {
            System.out.println("Por favor, informe apenas números inteiros!");
            scanner.close();
            return;
        }

        int id = scanner.nextInt();
        if (id == 0) return;

        Livro livro = dao.buscarLivroPorId(id);

        if (livro != null) {
            System.out.println("Livro encontrado: " + livro.getTitulo() + " - " + livro.getAutor());
        } else {
            System.out.println("Nenhum livro encontrado para o ID: " + id);
        }
        scanner.close();
    }
}
