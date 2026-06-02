import java.util.Scanner;

public class CardapioRestaurante {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("     CARDÁPIO ELETRÔNICO - TAVIOSRESTAURANTE ");
        System.out.println("=================================");
        System.out.println("1 - X-Burguer .......... R$ 18,00");
        System.out.println("2 - Pizza .............. R$ 35,00");
        System.out.println("3 - Suco Natural ....... R$ 8,00");
        System.out.println("4 - Café ............... R$ 5,00");
        System.out.println("5 - Bolacha ............ R$ 15,00");
        System.out.println("=================================");

        System.out.print("Escolha uma opção: ");
        int opcao = entrada.nextInt();

        double preco = 0;
        String itemEscolhido = "";

        // Define o nome e o preço baseado na opção
        if (opcao == 1) {
            itemEscolhido = "X-Burguer";
            preco = 18.00;
        } else if (opcao == 2) {
            itemEscolhido = "Pizza";
            preco = 35.00;
        } else if (opcao == 3) {
            itemEscolhido = "Suco Natural";
            preco = 8.00;
        } else if (opcao == 4) {
            itemEscolhido = "Café";
            preco = 5.00;
        } else if (opcao == 5) {
            itemEscolhido = "Bolacha";
            preco = 15.00;
        }

        // Valida se a opção escolhida existe no cardápio
        if (preco == 0) {
            System.out.println("\nOpção inválida. Pedido cancelado.");
        } else {
            System.out.print("Digite a quantidade desejada: ");
            int quantidade = entrada.nextInt();

            // Calcula o valor total
            double valorTotal = preco * quantidade;

            // Exibe o resumo final do pedido
            System.out.println("\n=================================");
            System.out.println("        RESUMO");
            System.out.println("=================================");
            System.out.println("Item: " + itemEscolhido);
            System.out.println("Quantidade: " + quantidade);
            System.out.println("Preço Unitário: R$ " + String.format("%.2f", preco));
            System.out.println("---------------------------------");
            System.out.println("VALOR TOTAL: R$ " + String.format("%.2f", valorTotal));
            System.out.println("=================================");
        }

        entrada.close();
    }
}
