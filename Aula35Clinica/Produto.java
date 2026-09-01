public class Produto {

    // Tarefa 1: Atributos privados (Encapsulamento)
    private int codigo;
    private String nome;
    private double preco;

    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        }
    }

    // Tarefa 5: Sobrecarga de Métodos
    // Versão 1: Altera o preço diretamente
    public void alterarPreco(double preco) { 
        setPreco(preco);
    }

    // Versão 2: Altera o preço aplicando porcentagem de desconto
    public void alterarPreco(double preco, double desconto) {
        double precoComDesconto = preco - (preco * (desconto / 100));
        setPreco(precoComDesconto);
    }

    // Tarefa 4: Método toString()
    @Override
    public String toString() {
        return codigo + " - " + nome + " - R$ " + String.format("%.2f", preco);
    }
}