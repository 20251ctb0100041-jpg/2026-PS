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

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;                 // "this.nome" atributo da classe, recebe o valor do parâmetro "nome"
        this.matricula = matricula;       // Atributo "matricula" recebe o valor passado no parâmetro
        this.curso = curso;               // Atributo "curso" recebe o valor passado no parâmetro
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

    // método SETTERS: servem para alterar od valores dos atributos dps que o objeto ja foi criado
    public void setNome(String nome) {
        this.nome = nome;       // atualiza oa tributo "nome" com o novo valor recebido
    }

    public void setCurso(String curso) {
        this.curso = curso;    // atualiza o atributo "curso" com o novo valor recebido
    }
}
