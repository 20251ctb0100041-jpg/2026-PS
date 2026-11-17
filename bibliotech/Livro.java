public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponivel;
    private String capa;

    public Livro(String titulo, String autor, int ano) {
        this(0, titulo, autor, ano, true, null);
    }

    public Livro(int id, String titulo, String autor, int ano) {
        this(id, titulo, autor, ano, true, null);
    }

    public Livro(int id, String titulo, String autor, int ano, String capa) {
        this(id, titulo, autor, ano, true, capa);
    }

    public Livro(int id, String titulo, String autor, int ano, boolean disponivel, String capa) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = disponivel;
        this.capa = capa;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAno() { return ano; }
    public boolean estaDisponivel() { return disponivel; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public String getCapa() { return capa; }
    public void setCapa(String capa) { this.capa = capa; }

    public void emprestar() {
        this.disponivel = false;
    }

    public void devolver() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " (" + autor + ", " + ano + ")";
    }
}
