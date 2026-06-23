public class AtividadeMetodos {

    public static void main(String[] args) {
        System.out.println("--- 🧩 Problema 1: Calculadora de Desconto ---");
        System.out.println("Resultado 1: " + calcularDesconto(100, 10)); 
        System.out.println("Resultado 2: " + calcularDesconto(250, 20)); 
        System.out.println("Resultado 3: " + calcularDesconto(500, 15)); 
        System.out.println();

        System.out.println("--- 🧩 Problema 2: Verificador de Maior Valor ---");
        System.out.println("Maior (10, 20): " + maiorNumero(10, 20)); 
        System.out.println("Maior (50, 5): " + maiorNumero(50, 5));   
        System.out.println("Maior (30, 30): " + maiorNumero(30, 30)); 
        System.out.println();

        System.out.println("--- 🧩 Problema 3: Sistema de Frete ---");
        System.out.println("Frete para 0.5kg: R$ " + calcularFrete(0.5)); 
        System.out.println("Frete para 3kg: R$ " + calcularFrete(3));     
        System.out.println("Frete para 8kg: R$ " + calcularFrete(8));     
        System.out.println();

        System.out.println("--- 🧩 Problema 4: Sobrecarga de Soma ---");
        System.out.println("Soma int (5, 3): " + somar(5, 3));         
        System.out.println("Soma double (2.5, 3.5): " + somar(2.5, 3.5)); 
        System.out.println("Soma int (100, 50): " + somar(100, 50));   
        System.out.println();

        System.out.println("--- 🧩 Problema 5: Sistema de Cardápio ---");
        exibirProduto("Refrigerante");            
        exibirProduto("Pizza", 39.90);            
        exibirProduto("Hambúrguer", 22.50);       
    }

    // 🧩 Problema 1 — Calculadora de Desconto
    static double calcularDesconto(double valor, double percentual) {
        double desconto = valor * (percentual / 100);
        return valor - desconto;
    }

    // 🧩 Problema 2 — Verificador de Maior Valor
    static int maiorNumero(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    // 🧩 Problema 3 — Sistema de Frete
    static double calcularFrete(double peso) {
        if (peso <= 1.0) {
            return 10.00;
        } else if (peso <= 5.0) {
            return 20.00;
        } else {
            return 35.00;
        }
    }

    // 🧩 Problema 4 — Sobrecarga de Soma
    static int somar(int a, int b) {
        return a + b;
    }

    static double somar(double a, double b) {
        return a + b;
    }

    // 🧩 Problema 5 — Sistema de Cardápio com Sobrecarga
    static void exibirProduto(String nome) {
        System.out.println("Produto: " + nome);
    }

    static void exibirProduto(String nome, double preco) {
        System.out.println("Produto: " + nome);
        System.out.println("Preço: R$ " + String.format("%.2f", preco));
    }
}