/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : TesteCorrecoes.java
 * Autor     : Otávio Augusto
 * Descricao : confere, contra o banco, as correcoes da Aula 47.
 */
import java.util.List;

public class TesteCorrecoes {

    private static int passaram = 0;
    private static int falharam = 0;

    private static void verificar(String correcao, boolean condicao) {
        if (condicao) {
            passaram = passaram + 1;
            System.out.println("OK      " + correcao);
        } else {
            falharam = falharam + 1;
            System.out.println("FALHOU  " + correcao);
        }
    }

    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();

        List<Livro> livros = dao.listarLivros();
        if (livros.isEmpty()) {
            System.out.println("A tabela livros esta vazia: cadastre um livro antes.");
            return;
        }

        // P1: um livro que acabou de sair do banco esta na estante.
        Livro daLista = livros.get(0);
        Livro daBusca = dao.buscarLivroPorId(daLista.getId());
        verificar("P1 livro da listagem nasce disponivel", daLista.estaDisponivel());
        verificar("P1 livro da busca por id nasce disponivel", daBusca != null && daBusca.estaDisponivel());

        // P3: o cadastro chega ao banco. O teste apaga o que criou.
        String titulo = "Livro de teste da Aula 47";
        boolean salvou = dao.adicionarLivro(titulo, "Turma de PS", 2026);
        List<Livro> achados = dao.buscarLivrosPorTitulo(titulo);
        verificar("P3 cadastro valido e salvo", salvou);
        verificar("P3 livro cadastrado aparece na busca por titulo", achados.size() == 1);
        for (Livro achado : achados) {
            dao.excluirLivro(achado.getId());
        }
        verificar("P3 o teste limpou o que criou", dao.buscarLivrosPorTitulo(titulo).isEmpty());

        System.out.println();
        System.out.println(passaram + " passaram, " + falharam + " falharam.");
    }
}
