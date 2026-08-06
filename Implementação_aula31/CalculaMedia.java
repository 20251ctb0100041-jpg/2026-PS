package Implementação_aula31; 

public class CalculaMedia {
    static double calculaMedia(int[] numeros) {
        double soma = 0;
        for (int i = 0; i < numeros.length; i++) {
            soma += numeros[i];
        }
        return numeros.length > 0 ? soma / numeros.length : 0;
    }
}
