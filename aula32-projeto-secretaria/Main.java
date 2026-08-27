/* 
* Disciplina    : 2026-PS
* Estudante     : Otávio Augusto Machado Ott
* Data          : 2026.08.13
* Projeto       : aula32-projeto-secretaria
* Arquivo       : Main.java
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Aluno> lista = new ArrayList<Aluno>();

        while (true) {
            System.out.println("=====================================");
            System.out.println("        SECRETARIA DO CAMPUS - por Otávio");
            System.out.println("=====================================");
            System.out.println("[1] Cadastrar aluno");
            System.out.println("[2] Listar alunos");
            System.out.println("[3] Buscar aluno");
            System.out.println("[4] Atualizar aluno");
            System.out.println("[5] Remover aluno");
            System.out.println("[6] Relatorio");
            System.out.println("[7] Buscar por nome");
            System.out.println("[0] Sair");
            System.out.print("Sua escolha: ");

            String opcao = teclado.nextLine().trim();

            if (opcao.equals("0")) {
                System.out.println("Secretaria fechada. Ate a proxima!");
                break;
            } else if (opcao.equals("1")) {
                cadastrar(lista, teclado);
            } else if (opcao.equals("2")) {
                listar(lista);
            } else if (opcao.equals("3")) {
                buscar(lista, teclado);
            } else if (opcao.equals("4")) {
                atualizar(lista, teclado);
            } else if (opcao.equals("5")) {
                remover(lista, teclado);
            } else if (opcao.equals("6")) {
                relatorio(lista, teclado);
            } else if (opcao.equals("7")) {
                buscarPorNomeBalcao(lista, teclado);
            } else {
                System.out.println("Opcao invalida! Vale 0, 1, 2, 3, 4, 5, 6 ou 7.");
            }
        }

        teclado.close();
    }

    // Le os dados no balcao, carimba a ficha e guarda no gaveteiro.
    static void cadastrar(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Nome: ");
        String nome = teclado.nextLine().trim();

        System.out.print("Matricula: ");
        String matricula = teclado.nextLine().trim();

        // MATRICULA UNICA: busca ANTES de inserir. Se ja existe, desiste.
        // A mesma busca de novo: quarta vez que ela trabalha para voce.
        Aluno existente = buscarPorMatricula(lista, matricula);
        if (existente != null) {
            System.out.println("Ja existe ficha com a matricula " + matricula + "!");
            return; // sai do metodo agora; nao cadastra
        }

        System.out.print("Curso: ");
        String curso = teclado.nextLine().trim();

        System.out.print("Cidade: ");
        String cidade = teclado.nextLine().trim();

        // new carimba a ficha; add guarda no gaveteiro. Sao duas acoes.
        Aluno novo = new Aluno(nome, matricula, curso, cidade);
        lista.add(novo);

        System.out.println("Ficha de " + novo.getNome() + " arquivada!");
    }

    // Percorre o gaveteiro e imprime ficha por ficha
    static void listar(ArrayList<Aluno> lista) {
        if (lista.size() == 0) {
            System.out.println("Nenhuma ficha no gaveteiro ainda.");
            return;
        }

        System.out.println("--- FICHAS NO GAVETEIRO: " + lista.size() + " ---");

        for (int i = 0; i < lista.size(); i++) {
            Aluno a = lista.get(i);
            System.out.println(a); // a impressao chama o toString sozinha
        }
    }

    // O CORACAO DO SISTEMA: devolve a ficha achada, ou null se nao existir.
    // Escrito uma vez, usado quatro vezes ate o fim do projeto.
    static Aluno buscarPorMatricula(ArrayList<Aluno> lista, String matricula) {
        for (int i = 0; i < lista.size(); i++) {
            Aluno a = lista.get(i);

            if (a.getMatricula().equals(matricula)) {
                return a;
            }
        }
        return null; // percorreu tudo e nao achou
    }

    // O balcao pergunta a matricula e usa a busca para responder.
    static void buscar(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Matricula procurada: ");
        String matricula = teclado.nextLine().trim();
        Aluno a = buscarPorMatricula(lista, matricula);

        // GUARDA: confere o null ANTES de usar o resultado.
        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
        } else {
            System.out.println("Achei: " + a);
        }
    }

    // Atualizar reusa a busca: escrever uma vez, chamar quantas vezes precisar.
    static void atualizar(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Matricula da ficha a atualizar: ");
        String matricula = teclado.nextLine().trim();
        Aluno a = buscarPorMatricula(lista, matricula);

        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.print("Novo curso de " + a.getNome() + ": ");
        String novoCurso = teclado.nextLine().trim();

        // a variavel a segura a MESMA ficha que esta na lista: mudar por
        // aqui muda o que a listagem mostra depois. Nao precisa reinserir.
        a.setCurso(novoCurso);
        System.out.println("Ficha atualizada: " + a);
    }

    // Acao destrutiva pede confirmacao. Padrao de sistema de verdade.
    static void remover(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Matricula da ficha a remover: ");
        String matricula = teclado.nextLine().trim();
        Aluno a = buscarPorMatricula(lista, matricula);

        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.print("Tem certeza que remove " + a.getNome() + "? (s/n): ");
        String resposta = teclado.nextLine().trim();

        if (resposta.equalsIgnoreCase("s")) {
            lista.remove(a); // remove ESTA ficha (a mesma referencia achada)
            System.out.println("Ficha removida.");
        } else {
            System.out.println("Remocao cancelada.");
        }
    }

    // RELATORIO: o padrao preparar -> percorrer -> usar, da Aula 29.
    static void relatorio(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.println("--- RELATORIO DA SECRETARIA ---");
        System.out.println("Total de fichas: " + lista.size());
        System.out.print("Contar alunos de qual curso? ");
        String curso = teclado.nextLine().trim();

        int contador = 0;                        // preparar (ANTES do for)
        for (int i = 0; i < lista.size(); i++) { // percorrer
            Aluno a = lista.get(i);
            if (a.getCurso().equals(curso)) {
                contador = contador + 1;
            }
        }
        System.out.println("Alunos de " + curso + ": " + contador); // usar
    }

    // Devolve a primeira ficha encontrada pelo nome, ou null se nao existir.
    static Aluno buscarPorNome(ArrayList<Aluno> lista, String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Aluno a = lista.get(i);
            if (a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        return null; // percorreu tudo e nao achou
    }

    // Acao do menu [7]
    static void buscarPorNomeBalcao(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Nome procurado: ");
        String nome = teclado.nextLine().trim();
        Aluno a = buscarPorNome(lista, nome);

        if (a == null) {
            System.out.println("Nenhuma ficha com o nome " + nome + ".");
        } else {
            System.out.println("Achei: " + a);
        }
    }
}