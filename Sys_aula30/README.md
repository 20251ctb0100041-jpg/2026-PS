Gerenciador de Estoque - SysControl

Este projeto foi feito para praticar os pilares da Orientação a Objetos usando Java. Em vez de trabalhar com variáveis soltas, o sistema organiza tudo dentro de uma estrutura segura e inteligente, simulando o controle de estoque de uma loja real.

---

O que o projeto faz?

O sistema cria produtos, atualiza o estoque e protege os dados contra erros. Veja os principais pontos aplicados:

    Dados Protegidos (Encapsulamento): Ninguém consegue alterar o preço ou o nome de um produto diretamente. Tudo passa por uma camada de segurança.
    Validações Inteligentes: O sistema impede erros comuns, como cadastrar um produto com preço negativo ou nome em branco.
    Estoque Inteligente: Métodos prontos para adicionar e remover itens. Se você tentar vender mais do que tem no estoque, o sistema bloqueia a operação.
    Simulação Real: A classe `Main` cria 3 produtos diferentes e testa cenários do dia a dia, mostrando o que acontece quando uma alteração é válida ou inválida.

---

Estrutura dos Arquivos

O código está dividido de forma simples:
   `Produto.java`: Contém as regras de negócio, os dados do produto e a lógica de estoque.
   `Main.java`: É o arquivo que roda o sistema, cria os itens e exibe os resultados na tela.

---

Como testar no seu computador

Se quiser rodar o projeto direto pelo terminal, siga estes passos:

1. Salve os arquivos `Produto.java` e `Main.java` na mesma pasta.
2. Abra o terminal nessa pasta e compile o código:
   ```bash
   javac Main.java Produto.java
   ```
3. Agora é só rodar:
   ```bash
   java Main
   ```

---

O que você vai ver no painel?

Ao rodar o programa, o terminal mostra o fluxo das operações de forma limpa:

   O estado inicial do primeiro produto.
   O estoque sendo atualizado (entradas e saídas).
   O sistema bloqueando uma tentativa de colocar um preço negativo.
   O resumo completo e formatado de tudo o que está guardado no estoque.
