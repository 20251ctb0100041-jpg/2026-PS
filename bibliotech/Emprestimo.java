import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Leitor leitor;
    private boolean ativo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;

    public Emprestimo(Livro livro, Leitor leitor) {
        this.livro = livro;
        this.leitor = leitor;
        this.ativo = false;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = this.dataEmprestimo.plusDays(7);
    }

    public boolean realizarEmprestimo() {
        if (livro != null && leitor != null && livro.estaDisponivel() && leitor.podePegarEmprestado()) {
            livro.emprestar();
            leitor.pegouLivro();
            this.ativo = true;
            return true;
        }
        return false;
    }

    public boolean registrarDevolucao() {
        if (ativo) {
            livro.devolver();
            leitor.devolveuLivro();
            this.ativo = false;
            return true;
        }
        return false;
    }

    public boolean estaAtivo() {
        return ativo;
    }

    public Livro getLivro() {
        return livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    @Override
    public String toString() {
        return "Emprestimo de '" + (livro != null ? livro.getTitulo() : "Desconhecido") + 
               "' para " + (leitor != null ? leitor.getNome() : "Desconhecido") + 
               " | Devolucao prevista: " + dataDevolucaoPrevista;
    }
}