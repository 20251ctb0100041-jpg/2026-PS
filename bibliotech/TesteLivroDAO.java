public class TesteLivroDAO {

    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();

        // Exclui o livro id 3 no banco de dados
        boolean resultado = dao.excluirLivro(3);

        System.out.println("Livro 3 excluído: " + resultado);
    }
}