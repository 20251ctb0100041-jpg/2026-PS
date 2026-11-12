/*
- Disciplina: 2026-PS
- Projeto : bibliotech
- Arquivo : Livro.java
- Autor : Otávio Augusto
- Descricao : a caixa "Livro" do diagrama de classes, em Java (Aula 45 - Suporte a Capas).
*/
public class Livro {
    // ATRIBUTOS
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponivel;
    private String capa; // Novo atributo da Aula 45

    // CONSTRUTORES
    public Livro() {
        this.disponivel = true;
    }

    public Livro(int id, String titulo, String autor, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
    }

    public Livro(int id, String titulo, String autor, int ano, String capa) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.capa = capa;
        this.disponivel = true;
    }

    public Livro(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
    }

    // GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    // OPERAÇÕES
    public boolean estaDisponivel() {
        return disponivel;
    }

    public void emprestar() {
        this.disponivel = false;
    }

    public void devolver() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        String situacao = disponivel ? "disponivel" : "emprestado";
        return id + " - " + titulo + " (" + autor + ", " + ano + ") - " + situacao;
    }
}