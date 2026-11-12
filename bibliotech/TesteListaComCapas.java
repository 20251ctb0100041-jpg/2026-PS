import java.io.File;
import java.util.List;

public class TesteListaComCapas {
    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();
        List<Livro> livros = dao.listarLivros();

        System.out.println("=========================================================================================");
        System.out.println("=== VERIFICAÇÃO DE APRESENTAÇÃO DAS CAPAS NA TABELA (PARTE 20) ===");
        System.out.println("=========================================================================================");
        System.out.printf("%-5s | %-25s | %-25s | %-6s | %-25s%n", "ID", "TÍTULO", "AUTOR", "ANO", "ESTADO DA CAPA");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (Livro l : livros) {
            String caminho = l.getCapa();
            String estado;
            if (caminho != null && !caminho.isBlank()) {
                File arq = new File(caminho);
                estado = arq.isFile() ? " [OK] Miniatura 55x75 (" + caminho + ")" : " [Sem capa] (Arquivo ausente)";
            } else {
                estado = " [Sem capa]";
            }

            System.out.printf("%-5d | %-25s | %-25s | %-6d | %-25s%n",
                    l.getId(),
                    resumir(l.getTitulo(), 25),
                    resumir(l.getAutor(), 25),
                    l.getAno(),
                    estado);
        }
        System.out.println("=========================================================================================");
        System.out.println("Total de registros listados: " + livros.size());
    }

    private static String resumir(String t, int max) {
        if (t == null) return "";
        return t.length() > max ? t.substring(0, max - 3) + "..." : t;
    }
}
