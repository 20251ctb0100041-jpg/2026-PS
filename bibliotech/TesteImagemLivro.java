import java.nio.file.Path;
import java.util.Scanner;

public class TesteImagemLivro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivroDAO dao = new LivroDAO();

        System.out.println("=== TESTE DA PARTE 19 (SALVANDO CAPA E REFERÊNCIA NO MARIADB) ===");
        System.out.print("Digite o ID do livro a ser atualizado: ");

        if (!scanner.hasNextInt()) {
            System.out.println("ID inválido.");
            scanner.close();
            return;
        }

        int id = scanner.nextInt();
        scanner.nextLine();

        Livro livro = dao.buscarLivroPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado para o ID: " + id);
            scanner.close();
            return;
        }

        System.out.println("Livro selecionado: " + livro.getTitulo());
        System.out.print("Informe o caminho da imagem de origem (ex: /tmp/teste.png): ");
        String caminhoOrigemStr = scanner.nextLine().trim();

        try {
            Path origem = Path.of(caminhoOrigemStr);
            String caminhoRelativo = ImagemLivro.salvar(origem, id);

            boolean ok = dao.atualizarCapa(id, caminhoRelativo);
            if (ok) {
                System.out.println("✅ Capa salva com sucesso!");
                System.out.println("Caminho no banco: " + caminhoRelativo);
            } else {
                System.out.println("❌ Falha ao atualizar o banco de dados.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao processar imagem: " + e.getMessage());
        }

        scanner.close();
    }
}