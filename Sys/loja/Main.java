package Sys.loja; 

public class Main {
    public static void main(String[] args) {
        // Criando os três produtos do sistema
        Produto produto1 = new Produto("Mouse", 80.00, 10);
        Produto produto2 = new Produto("Teclado Mecânico", 250.00, 5);
        Produto produto3 = new Produto("Monitor 24'", 1200.00, 3);

        System.out.println("--- Estado Inicial do Produto 1 ---");
        exibirDados(produto1);

        // Movimentando o estoque do Produto 1
        System.out.println("\n🔹 Atualizando estoque do Produto 1...");
        produto1.adicionarEstoque(5);
        boolean removido = produto1.removerEstoque(3);
        System.out.println("Remoção de 3 unidades realizada? " + (removido ? "Sim" : "Não"));

        System.out.println("\n--- Estado do Produto 1 Após Movimentação ---");
        exibirDados(produto1);

        // Testando as regras de validação (Proteção do Objeto)
        System.out.println("\n⚠️ Testando alteração inválida (Preço negativo)...");
        produto2.setPreco(-50.00); 
        System.out.println("Preço atual do Produto 2: R$ " + produto2.getPreco());

        System.out.println("\n✅ Testando alteração válida (Atualizando preço)...");
        produto2.setPreco(220.00);
        System.out.println("Preço atual do Produto 2: R$ " + produto2.getPreco());

        // Resumo final do estoque
        System.out.println("\n=================================");
        System.out.println("       RESUMO DO ESTOQUE         ");
        System.out.println("=================================");
        
        System.out.println("\n[Item 1]");
        exibirDados(produto1);
        
        System.out.println("\n[Item 2]");
        exibirDados(produto2);
        
        System.out.println("\n[Item 3]");
        exibirDados(produto3);
    }

    // Mostra as informações do produto formatadas de um jeito limpo
    public static void exibirDados(Produto produto) {
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Preço unitário: R$ " + String.format("%.2f", produto.getPreco()));
        System.out.println("Quantidade em estoque: " + produto.getQuantidade());
        System.out.println("Valor total investido: R$ " + String.format("%.2f", produto.calcularValorEmEstoque()));
    }
}
