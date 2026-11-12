/*
 * Disciplina: 2026-PS
 * Projeto    : bibliotech
 * Arquivo    : Usuario.java
 * Autor      : Otávio Augusto
 * Descricao  : A classe geral do diagrama com suporte a id e foto de perfil (Aula 45).
 */
public class Usuario {

    private int id;
    private String nome;
    private String matricula;
    private String email;
    private String foto; // Novo atributo para o caminho da imagem de perfil

    // Construtores
    public Usuario() {}

    public Usuario(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public Usuario(int id, String nome, String email, String foto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.foto = foto;
    }

    public Usuario(int id, String nome, String matricula, String email, String foto) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.foto = foto;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    // Método de negócio original
    public boolean entrar() {
        return matricula != null && !matricula.isEmpty();
    }

    @Override
    public String toString() {
        return nome + " (" + (matricula != null ? matricula : id) + ")";
    }
}