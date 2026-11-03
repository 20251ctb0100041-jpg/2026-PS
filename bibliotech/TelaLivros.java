import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaLivros extends JFrame {
    private final JTextField campoId = new JTextField(5);
    private final JTextField campoTitulo = new JTextField(15);
    private final DefaultTableModel modelo = new DefaultTableModel();
    private final JLabel status = new JLabel("Livros encontrados: 0");
    private final LivroDAO dao = new LivroDAO();

    public TelaLivros() {
        setTitle("BiblioTech - Gerenciamento de Livros");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo.addColumn("ID");
        modelo.addColumn("Título");
        modelo.addColumn("Autor");
        modelo.addColumn("Ano");
        JTable tabela = new JTable(modelo);

        JPanel controles = new JPanel(new FlowLayout());

        // Busca por ID
        controles.add(new JLabel("ID:"));
        controles.add(campoId);
        JButton botaoBuscarId = new JButton("Buscar por ID");
        controles.add(botaoBuscarId);

        // Busca por Título (Parte 18)
        controles.add(new JLabel("Título:"));
        controles.add(campoTitulo);
        JButton botaoBuscarTitulo = new JButton("Buscar por Título");
        controles.add(botaoBuscarTitulo);

        // Botões auxiliares
        JButton botaoMostrarTodos = new JButton("Mostrar Todos");
        JButton botaoLimpar = new JButton("Limpar");
        controles.add(botaoMostrarTodos);
        controles.add(botaoLimpar);

        add(controles, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        // Eventos
        botaoBuscarId.addActionListener(e -> buscarPorId());
        botaoBuscarTitulo.addActionListener(e -> buscarPorTitulo());
        campoTitulo.addActionListener(e -> buscarPorTitulo()); // Atalho ENTER
        botaoMostrarTodos.addActionListener(e -> carregarLivros());
        botaoLimpar.addActionListener(e -> limparCampos());

        carregarLivros();
    }

    private void buscarPorTitulo() {
        String titulo = campoTitulo.getText().trim();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o título do livro.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Livro> livros = dao.buscarLivrosPorTitulo(titulo);
        modelo.setRowCount(0);
        for (Livro livro : livros) {
            adicionarLinha(livro);
        }
        status.setText("Livros encontrados: " + livros.size());

        if (livros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum livro encontrado.");
        }
    }

    private void buscarPorId() {
        String idTexto = campoId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o ID do livro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Livro livro = dao.buscarLivroPorId(id);
            modelo.setRowCount(0);

            if (livro != null) {
                adicionarLinha(livro);
                status.setText("Livros encontrados: 1");
            } else {
                status.setText("Livros encontrados: 0");
                JOptionPane.showMessageDialog(this, "Nenhum livro encontrado com o ID informado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um ID numérico válido.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarLivros() {
        List<Livro> livros = dao.listarLivros();
        modelo.setRowCount(0);
        for (Livro livro : livros) {
            adicionarLinha(livro);
        }
        status.setText("Livros encontrados: " + livros.size());
    }

    private void adicionarLinha(Livro livro) {
        modelo.addRow(new Object[]{livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAno()});
    }

    private void limparCampos() {
        campoId.setText("");
        campoTitulo.setText("");
        carregarLivros();
    }
}