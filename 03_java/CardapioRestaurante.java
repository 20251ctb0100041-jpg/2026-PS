import java.util.Random;
import java.util.Scanner;

public class CardapioRestaurante {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Random random = new Random();

        double valorTotalGeral = 0;
        String resumoPedido = "";
        
        // Este laço controla o fluxo geral 
        boolean sistemaAtivo = true;
        while (sistemaAtivo) {
            
            boolean continuarComprando = true;
            while (continuarComprando) {
                System.out.println("===========================");
                System.out.println("    FAST FOOD IFPR   ");
                System.out.println("===========================");
                System.out.println("1 - X-Burguer");
                System.out.println("2 - Pizza");
                System.out.println("3 - Bolacha");
                System.out.println("4 - Refrigerante");
                System.out.println("5 - Sorvete");
                System.out.println("6 - Lasanha");
                System.out.println("7 - Finalizar Pedido");
                System.out.println();

                System.out.print("Escolha: ");
                int opcao = entrada.nextInt();

                if (opcao == 7) {
                    break; 
                }

                double preco = 0;
                String itemEscolhido = "";

                if (opcao == 1) {
                    itemEscolhido = "X-Burguer";
                    preco = 18.00;
                } else if (opcao == 2) {
                    itemEscolhido = "Pizza";
                    preco = 35.00;
                } else if (opcao == 3) {
                    itemEscolhido = "Bolacha";
                    preco = 15.00;
                } else if (opcao == 4) {
                    itemEscolhido = "Refrigerante";
                    preco = 8.00;
                } else if (opcao == 5) {
                    itemEscolhido = "Sorvete";
                    preco = 7.00;
                } else if (opcao == 6) {
                    itemEscolhido = "Lasanha";
                    preco = 20.00;
                } else {
                    System.out.println("\nOpção inválida! Tente novamente.\n");
                    continue;
                }

                System.out.print("\nQuantidade: ");
                int quantidade = entrada.nextInt();

                double valorItem = preco * quantidade;
                
                // total 
                valorTotalGeral += valorItem;
                resumoPedido += quantidade + "x " + itemEscolhido + " ..... R$ " + String.format("%.2f", valorItem) + "\n";

                System.out.println("\nItem adicionado ao pedido!\n");

                System.out.println("Deseja continuar comprando?");
                System.out.println("1 - Sim");
                System.out.println("2 - Finalizar");
                System.out.println();
                System.out.print("Escolha: ");
                int escolhaContinuar = entrada.nextInt();
                System.out.println("\n===========================");

                if (escolhaContinuar == 2) {
                    continuarComprando = false;
                }
            }

            // --- RESUMO DO PEDIDO ---
            System.out.println("===========================");
            System.out.println("RESUMO DO PEDIDO");
            System.out.println("===========================");
            if (resumoPedido.equals("")) {
                System.out.println("Nenhum item foi adicionado.");
            } else {
                System.out.print(resumoPedido);
            }
            System.out.println("\nTOTAL: R$ " + String.format("%.2f", valorTotalGeral));
            System.out.println();

            // --- FORMA DE PAGAMENTO ---
            System.out.println("Forma de pagamento:\n");
            System.out.println("1 - Dinheiro");
            System.out.println("2 - Cartão");
            System.out.println("3 - PIX");
            System.out.println("4 - Voltar e alterar o pedido"); 
            System.out.println();
            System.out.print("Escolha: ");
            int formaPagamento = entrada.nextInt();

            if (formaPagamento == 4) {
                System.out.println("\nVoltando ao menu principal... Seus itens atuais foram salvos!\n");
                // Não desliga o sistemaAtivo, fazendo o código dar a volta e reabrir o menu principal
                continue; 
            }

           
            System.out.println("\nPagamento realizado com sucesso!");
            
            int numeroPedido = random.nextInt(900) + 100; 
            System.out.println("\nPedido Nº " + numeroPedido);
            
            System.out.println("\nAguarde a chamada do seu pedido.");
            
        sistemaAtivo = false;
        }

        entrada.close();
    }
}