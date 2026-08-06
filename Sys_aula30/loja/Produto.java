package Sys_aula30.loja;

public class Produto {
    private String nome;
    private double preco;
    private int quantidade;

    // Construtor 
    public Produto(String nome, double preco, int quantidade) {
        setNome(nome);
        setPreco(preco);
        setQuantidade(quantidade);
    }

    // Getters 
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // Setters 
    public void setNome(String nome) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome.trim();
        }
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        }
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    // Gerenciamento de estoque 
    public void adicionarEstoque(int quantidadeAdicionar) {
        if (quantidadeAdicionar > 0) {
            this.quantidade += quantidadeAdicionar;
        }
    }

    public boolean removerEstoque(int quantidadeRemover) {
        if (quantidadeRemover > 0 && quantidadeRemover <= this.quantidade) {
            this.quantidade -= quantidadeRemover;
            return true;
        }
        return false; // Retorna falso se não tiver estoque suficiente
    }

    // Cálculo do patrimônio em estoque
    public double calcularValorEmEstoque() {
        return this.preco * this.quantidade;
    }
}
