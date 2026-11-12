import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaLivros extends JFrame {

    private final LivroDAO dao = new LivroDAO();

    private final DefaultTableModel modelo;
    private final JTable tabela;

    private final JTextField campoId = new JTextField(8);
    private final JLabel status = new JLabel("Pronto."); // Desafio 1: Exibe contagem e mensagens

    public TelaLivros() {

        setTitle("BiblioTech - Consulta de Livros");
        setSize(850, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        modelo = new DefaultTableModel(
            new Object[]{"ID", "Título", "Autor", "Ano"}, 0
        ) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        tabela = new JTable(modelo);

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel controles = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton atualizar = new JButton("Atualizar listagem");
        JButton buscar = new JButton("Buscar por ID");
        JButton mostrarTodos = new JButton("Mostrar todos");
        JButton limpar = new JButton("Limpar"); // Desafio 3: Botão Limpar

        controles.add(new JLabel("ID do livro:"));
        controles.add(campoId);
        controles.add(buscar);
        controles.add(limpar); // Adicionado à barra de controles
        controles.add(atualizar);
        controles.add(mostrarTodos);

        add(controles, BorderLayout.NORTH);
        add(status, BorderLayout.SOUTH);

        // --- EVENTOS ---

        atualizar.addActionListener(e -> carregarLivros());
        mostrarTodos.addActionListener(e -> carregarLivros());
        buscar.addActionListener(e -> buscarLivro());

        // Desafio 2: Executar busca ao pressionar Enter no campo de ID
        campoId.addActionListener(e -> buscarLivro());

        // Desafio 3: Limpar o campo de ID
        limpar.addActionListener(e -> campoId.setText(""));

        carregarLivros();
    }

    private void carregarLivros() {
        modelo.setRowCount(0);

        List<Livro> livros = dao.listarLivros();

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        // Desafio 1: Exibe a quantidade total de livros carregados
        status.setText("Livros apresentados: " + livros.size());
    }

    private void adicionarLinha(Livro livro) {
        modelo.addRow(new Object[]{
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getAno()
        });
    }

    private void buscarLivro() {
        String texto = campoId.getText().trim();
        int id;

        try {
            id = Integer.parseInt(texto);

            if (id <= 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Informe um ID inteiro positivo.",
                "Entrada inválida",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Livro livro = dao.buscarLivroPorId(id);

        modelo.setRowCount(0);

        if (livro == null) {
            status.setText("Nenhum livro encontrado para o ID " + id);
            JOptionPane.showMessageDialog(
                this,
                "Livro não encontrado."
            );
        } else {
            adicionarLinha(livro);
            status.setText("Livro encontrado: " + livro.getTitulo());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaLivros().setVisible(true);
        });
    }
}