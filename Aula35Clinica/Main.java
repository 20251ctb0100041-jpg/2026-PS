import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Produto> produtos = new ArrayList<>();

    // Tarefa 3: O main fica responsável apenas pelo menu e chamadas
    public static void main(String[] args) {
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n=== SISTEMA DE PRODUTOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar preço");
            System.out.println("4 - Remover");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> alterarPreco();
                case 4 -> remover();
                case 5 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // Tarefa 3: Método cadastrar
    static void cadastrar() {
        System.out.print("Código: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        // Tarefa 6: Impede o cadastro de produtos com o mesmo código
        if (buscarPorCodigo(codigo) != null) {
            System.out.println("Erro: Já existe um produto com este código!");
            return;
        }

        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        System.out.print("Preço: ");
        double preco = teclado.nextDouble();
        teclado.nextLine();

        produtos.add(new Produto(codigo, nome, preco));
        System.out.println("Produto cadastrado com sucesso!");
    }

    // Tarefa 3: Método listar (usando o toString())
    static void listar() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Produtos ---");
        for (Produto p : produtos) {
            // Tarefa 4: Exibe o produto usando toString()
            System.out.println(p);
        }
    }

    // Tarefa 2: Método de busca reutilizável
    static Produto buscarPorCodigo(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    // Tarefa 3: Método alterarPreco
    static void alterarPreco() {
        System.out.print("Código do produto: ");
        int codigo = teclado.nextInt();

        // Tarefa 2: Reutilizando o buscarPorCodigo()
        Produto produto = buscarPorCodigo(codigo);

        // Tarefa 6: Informa quando não é encontrado e continua funcionando
        if (produto != null) {
            System.out.print("Novo preço: ");
            double novoPreco = teclado.nextDouble();
            teclado.nextLine();

            produto.alterarPreco(novoPreco);
            System.out.println("Preço alterado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Tarefa 3: Método remover
    static void remover() {
        System.out.print("Código do produto para remover: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        // Tarefa 2 e 6: Remoção sem apresentar erros (usando removeIf)
        boolean removido = produtos.removeIf(p -> p.getCodigo() == codigo);

        if (removido) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}