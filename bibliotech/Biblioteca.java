import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros;
    private ArrayList<Leitor> leitores;
    private ArrayList<Emprestimo> emprestimos;

    // Declarar nao cria: as tres listas nascem aqui, no new.
    public Biblioteca() {
        this.livros = new ArrayList<Livro>();
        this.leitores = new ArrayList<Leitor>();
        this.emprestimos = new ArrayList<Emprestimo>();
    }

    public void cadastrarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void cadastrarLeitor(Leitor leitor) {
        this.leitores.add(leitor);
    }

    public void listarAcervo() {
        for (Livro l : this.livros) {
            System.out.println(l);
        }
    }

    // Devolve o livro achado, ou null se o titulo nao esta no acervo.
    public Livro buscarLivro(String titulo) {
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.getTitulo().equals(titulo)) {
                return l;
            }
        }
        return null;
    }

    // Devolve o leitor achado pela matricula, ou null se nao encontrar.
    public Leitor buscarLeitor(String matricula) {
        for (int i = 0; i < leitores.size(); i++) {
            Leitor l = leitores.get(i);
            if (l.getMatricula().equals(matricula)) {
                return l;
            }
        }
        return null;
    }

    // Acha os dois pelo texto, cria o registro e so arquiva se deu certo.
    public boolean emprestar(String titulo, String matricula) {
        Livro livro = buscarLivro(titulo);
        Leitor leitor = buscarLeitor(matricula);

        if (livro == null || leitor == null) {
            return false;
        }

        Emprestimo novo = new Emprestimo(livro, leitor);
        if (!novo.realizarEmprestimo()) {
            return false;
        }

        emprestimos.add(novo);
        return true;
    }

    // Procura o emprestimo ATIVO daquele titulo. O registro continua na lista
    public boolean devolver(String titulo) {
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo e = emprestimos.get(i);
            if (e.estaAtivo() && e.getLivro().getTitulo().equals(titulo)) {
                return e.registrarDevolucao();
            }
        }
        return false;
    }

    public void listarEmprestimos() {
        for (int i = 0; i < emprestimos.size(); i++) {
            System.out.println(emprestimos.get(i));
        }
    }
}