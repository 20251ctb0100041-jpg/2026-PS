import java.util.List;
import java.util.Scanner;

public class TesteBuscarPorTitulo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivroDAO dao = new LivroDAO();

        System.out.println("=== TESTE DE BUSCA POR TÍTULO (PARTE 18) ===");
        System.out.print("Digite o termo de busca: ");
        String termo = scanner.nextLine().trim();

        if (termo.isEmpty()) {
            System.out.println("Busca cancelada (campo vazio).");
            scanner.close();
            return;
        }

        List<Livro> resultados = dao.buscarLivrosPorTitulo(termo);

        System.out.println("\n--- RESULTADOS DA PESQUISA ---");
        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o termo: \"" + termo + "\"");
        } else {
            for (Livro l : resultados) {
                System.out.println("ID: " + l.getId() + " | Título: " + l.getTitulo() + " | Autor: " + l.getAutor() + " | Ano: " + l.getAno());
            }
            System.out.println("Total de livros encontrados: " + resultados.size());
        }
        scanner.close();
    }
}
