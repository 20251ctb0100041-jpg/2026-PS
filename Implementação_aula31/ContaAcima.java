package Implementação_aula31;

public class ContaAcima {
    static int contarAcima(int[] numeros, int limite) {
        int quantidade = 0;
        for (int n : numeros) { 
            if (n > limite) quantidade++;
        }
        return quantidade;
    }
}
