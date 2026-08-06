package Implementação_aula31;

public class Main {
    public static void main(String[] args) {
        int[] valores = {8, 3, 10, 5, 12};

        System.out.println(CalculaMedia.calculaMedia(valores));   
        System.out.println(MenorValor.menorValor(valores));       
        System.out.println(MaiorValor.maiorValor(valores));       
        System.out.println(ContaAcima.contarAcima(valores, 6));   
    }
}
