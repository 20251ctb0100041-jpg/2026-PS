/* 
* Discplina    : 2026-PS
* Estudante    : Otávio Augusto Machado Ott
* Data         : 2026.08.13
* Projeto      : aula32-projeto-secretaria
* Arquivo      : Aluno.java
*/

public class Aluno {
    private String nome;          // ATRIBUTO PRIVADO, guarda o nome do aluno
    private String matricula;     // guarrda a matricula do aluno
    private String curso;         // guarda o curso do aluno
    private String cidade;

    public Aluno(String nome, String matricula, String curso, String cidade) {
        this.nome = nome;                 // "this.nome" atributo da classe, recebe o valor do parâmetro "nome"
        this.matricula = matricula;       // Atributo "matricula" recebe o valor passado no parâmetro
        this.curso = curso;               // Atributo "curso" recebe o valor passado no parâmetro
        this.cidade = cidade; 
    }

    // método GETTERS: servem para acessar os valores dos atributos fora da classe
    public String getNome() {
        return nome;       // retorna o valor do atributo "nome"
    }

    public String getMatricula() {
        return matricula;  // retorna o valor do atributo "matricula"
    }
    public String getCurso() {
        return curso;      // retorna o valor dop atributo "curso"
    }

    public String getCidade() {
        return cidade;
    }

    // método SETTERS: servem para alterar od valores dos atributos dps que o objeto ja foi criado
    public void setNome(String nome) {
        this.nome = nome;       // atualiza oa tributo "nome" com o novo valor recebido
    }

    public void setCurso(String curso) {
        this.curso = curso;    // atualiza o atributo "curso" com o novo valor recebido
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;    // atualiza o atributo "cidade" com o novo valor recebido
    }

    // toString: como a ficha se apresenta quando alguem manda imprimi-la.
    // Sem ele, System.out.println(aluno) mostra Aluno@7ad041f3.
    // O @Override avisa o compilador: estou trocando um metodo que toda
    // classe ja tem por uma versao minha.
    @Override
    public String toString() {
        return matricula + " | " + nome + " | " + curso + " | " + cidade;
    }
}
