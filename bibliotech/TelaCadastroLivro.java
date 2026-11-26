/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : TelaCadastroLivro.java
 * Descricao : janela modal de cadastro de livros com validacao.
 */
import javax.swing.*;
import java.awt.*;

public class TelaCadastroLivro extends JDialog {

    private final LivroDAO dao = new LivroDAO();

    private final JTextField campoTitulo = new JTextField(24);
    private final JTextField campoAutor = new JTextField(24);
    private final JTextField campoAno = new JTextField(6);

    public TelaCadastroLivro(Frame dona) {
        // true = janela modal: a tela de livros espera esta fechar.
        super(dona, "BiblioTech - Novo Livro", true);

        setLayout(new GridLayout(4, 2, 8, 8));

        add(new JLabel("Título:"));
        add(campoTitulo);
        add(new JLabel("Autor:"));
        add(campoAutor);
        add(new JLabel("Ano:"));
        add(campoAno);

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoCancelar = new JButton("Cancelar");

        add(botaoSalvar);
        add(botaoCancelar);

        botaoSalvar.addActionListener(e -> salvar());
        botaoCancelar.addActionListener(e -> dispose());

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(dona);
    }

    private void salvar() {
        try {
            String titulo = campoTitulo.getText().trim();
            String autor = campoAutor.getText().trim();

            int ano = Integer.parseInt(
                campoAno.getText().trim()
            );

            ValidadorLivro.validar(
                titulo, autor, ano
            );

            boolean salvou = dao.adicionarLivro(
                titulo, autor, ano
            );

            if (salvou) {
                JOptionPane.showMessageDialog(
                    this,
                    "Livro cadastrado."
                );
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível salvar o livro.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "O ano deve ser um número inteiro."
            );
        } catch (LivroInvalidoException e) {
            JOptionPane.showMessageDialog(
                this,
                e.getMessage()
            );
        }
    }
}
