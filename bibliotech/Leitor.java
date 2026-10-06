/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : Leitor.java
 * Autor     : Otavio Augusto
 * Descricao : Leitor E UM TIPO DE Usuario: herda nome, matricula e ent
 */
public class Leitor extends Usuario {

    // So o que a caixa Leitor acrescenta. Nome e matricula ja vem de Usuario
    private int limiteEmprestimos;
    private int livrosEmMaos;   // nao estava na caixa: o codigo pediu

    public Leitor(String nome, String matricula, int limiteEmprestimos) {
        super(nome, matricula);
        this.limiteEmprestimos = limiteEmprestimos;
        this.livrosEmMaos = 0;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public int getLivrosEmMaos() {
        return livrosEmMaos;
    }

    // OPERACAO DA CAIXA: podePegarEmprestado().
    public boolean podePegarEmprestado() {
        return livrosEmMaos < limiteEmprestimos;
    }

    // Os dois metodos que o emprestimo vai usar na Aula 38.
    public void pegouLivro() {
        this.livrosEmMaos = this.livrosEmMaos + 1;
    }

    public void devolveuLivro() {
        this.livrosEmMaos = this.livrosEmMaos - 1;
    }

    @Override
    public String toString() {
        // Linha 43 alterada para usar getNome()
        return "Leitor " + super.toString() + " - "
                + livrosEmMaos + " de " + limiteEmprestimos + " livros";
    }
}