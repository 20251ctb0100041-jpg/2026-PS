import java.util.ArrayList;

public class Aula29 {
    public static void main(String[] args) {
        System.out.println(" Médias: " + calcularMedia(new double[]{7, 8, 9}) + " | " + calcularMedia(new double[]{6, 6, 6, 6}));
        
        System.out.println(" Aprovados: " + contarAprovados(new double[]{7, 4, 9, 6}));

        System.out.println("\n Catálogo:");
        ArrayList<String> lista = new ArrayList<>();
        adicionarProduto(lista, "Pizza"); adicionarProduto(lista, "Suco");
        listarProdutos(lista);
        
        System.out.println("\n Maiores: " + maiorValor(new int[]{3, 9, 5}) + " | " + maiorValor(12, 7));
        
        System.out.println("\n Boletins:");
        exibirBoletim(new double[]{7, 5, 9, 6});
        System.out.println("-");
        exibirBoletim(new double[]{4, 3, 5});
    }

    // 1 Média
    static double calcularMedia(double[] notas) {
        double total = 0;
        for (double n : notas) total += n;
        return total / notas.length;
    }

    // 2 Contador
    static int contarAprovados(double[] notas) {
        int qtd = 0;
        for (double n : notas) if (n >= 6.0) qtd++;
        return qtd;
    }

    // 3 ArrayList
    static void adicionarProduto(ArrayList<String> lista, String nome) {
        lista.add(nome);
    }
    static void listarProdutos(ArrayList<String> lista) {
        for (int i = 0; i < lista.size(); i++) System.out.println((i + 1) + " - " + lista.get(i));
    }

    // 4 Sobrecarga
    static int maiorValor(int[] valores) {
        int maior = valores[0];
        for (int v : valores) if (v > maior) maior = v;
        return maior;
    }
    static int maiorValor(int a, int b) {
        return (a > b) ? a : b;
    }

    // 5 Integrador
    static void exibirBoletim(double[] notas) {
        double media = calcularMedia(notas);
        System.out.println("Média: " + media);
        System.out.println("Aprovados: " + contarAprovados(notas));
        System.out.println("Situação: " + ((media >= 6.0) ? "APROVADA" : "EM RECUPERAÇÃO"));
    }
    }
