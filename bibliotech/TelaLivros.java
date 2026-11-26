import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;

public class TelaLivros extends JFrame {
    private final JTextField campoId = new JTextField(4);
    private final JTextField campoTitulo = new JTextField(16);
    private final DefaultTableModel modelo;
    private final JTable tabela;
    private final JLabel status = new JLabel("Livros encontrados: 0");
    private final LivroDAO dao = new LivroDAO();

    // Paleta limpa baseada apenas no #0D214F
    private final Color azulMarinho = Color.decode("#0D214F");
    private final Color azulSelecao = new Color(235, 240, 248);
    private final Color fundoPagina = new Color(245, 246, 248);
    private final Color bordaSuave = new Color(218, 222, 228);
    private final Color textoEscuro = new Color(30, 35, 42);
    private final Color textoCinza = new Color(110, 115, 125);

    public TelaLivros() {
        setTitle("BiblioTech — Catálogo de Livros");
        setSize(940, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(fundoPagina);

        // --- CABEÇALHO ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(azulMarinho);
        header.setBorder(new EmptyBorder(16, 24, 16, 24));

        JPanel titulos = new JPanel();
        titulos.setLayout(new BoxLayout(titulos, BoxLayout.Y_AXIS));
        titulos.setOpaque(false);

        JLabel tituloPagina = new JLabel("Catálogo BiblioTech");
        tituloPagina.setFont(new Font("SansSerif", Font.BOLD, 20));
        tituloPagina.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Explore os livros disponíveis na biblioteca");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitulo.setForeground(new Color(200, 210, 225));
        subtitulo.setBorder(new EmptyBorder(2, 0, 0, 0));

        titulos.add(tituloPagina);
        titulos.add(subtitulo);
        header.add(titulos, BorderLayout.WEST);

        // --- BARRA DE PESQUISA ---
        JPanel painelControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 10));
        painelControles.setBackground(Color.WHITE);
        painelControles.setBorder(new CompoundBorder(
            new LineBorder(bordaSuave, 1),
            new EmptyBorder(4, 10, 4, 10)
        ));

        estilizarCampo(campoId);
        estilizarCampo(campoTitulo);

        JButton botaoBuscarId = criarBotao("Buscar ID", azulMarinho, Color.WHITE);
        JButton botaoBuscarTitulo = criarBotao("Pesquisar", azulMarinho, Color.WHITE);
        JButton botaoCapa = criarBotao("Selecionar capa", Color.WHITE, azulMarinho);
        JButton botaoNovo = criarBotao("Novo livro", azulMarinho, Color.WHITE);
        JButton botaoMostrarTodos = criarBotao("Todos", Color.WHITE, textoEscuro);
        JButton botaoLimpar = criarBotao("Limpar", Color.WHITE, textoCinza);

        JLabel lblId = new JLabel("ID:");
        lblId.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblId.setForeground(textoEscuro);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTitulo.setForeground(textoEscuro);

        painelControles.add(lblId);
        painelControles.add(campoId);
        painelControles.add(botaoBuscarId);
        painelControles.add(Box.createHorizontalStrut(6));
        painelControles.add(lblTitulo);
        painelControles.add(campoTitulo);
        painelControles.add(botaoBuscarTitulo);
        painelControles.add(Box.createHorizontalStrut(10));
        painelControles.add(botaoCapa);
        painelControles.add(botaoNovo);
        painelControles.add(botaoMostrarTodos);
        painelControles.add(botaoLimpar);

        JPanel corpo = new JPanel(new BorderLayout(0, 12));
        corpo.setBackground(fundoPagina);
        corpo.setBorder(new EmptyBorder(16, 24, 12, 24));
        corpo.add(painelControles, BorderLayout.NORTH);

        // --- LISTA DE LIVROS (CATÁLOGO) ---
        modelo = new DefaultTableModel(new Object[]{"Capa", "Detalhes"}, 0) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        tabela = new JTable(modelo);
        tabela.setTableHeader(null);
        tabela.setRowHeight(142);
        tabela.setShowVerticalLines(false);
        tabela.setShowHorizontalLines(true);
        tabela.setGridColor(bordaSuave);
        tabela.setBackground(Color.WHITE);
        tabela.setSelectionBackground(azulSelecao);
        tabela.setSelectionForeground(textoEscuro);
        tabela.setIntercellSpacing(new Dimension(0, 1));

        // Coluna 0: Capa
        tabela.getColumnModel().getColumn(0).setMaxWidth(130);
        tabela.getColumnModel().getColumn(0).setMinWidth(130);
        tabela.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable tabela, Object valor, boolean selecionada, boolean foco, int linha, int coluna) {

                JPanel wrapper = new JPanel(new GridBagLayout());
                wrapper.setBackground(selecionada ? azulSelecao : Color.WHITE);

                JLabel capaLabel = new JLabel();
                capaLabel.setHorizontalAlignment(JLabel.CENTER);
                capaLabel.setPreferredSize(new Dimension(80, 110));
                capaLabel.setOpaque(true);

                if (valor instanceof ImageIcon) {
                    capaLabel.setIcon((ImageIcon) valor);
                    capaLabel.setBorder(new LineBorder(bordaSuave, 1));
                } else {
                    capaLabel.setText("Sem capa");
                    capaLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
                    capaLabel.setForeground(textoCinza);
                    capaLabel.setBackground(fundoPagina);
                    capaLabel.setBorder(new LineBorder(bordaSuave, 1));
                }

                wrapper.add(capaLabel);
                return wrapper;
            }
        });

        // Coluna 1: Informações
        tabela.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable tabela, Object valor, boolean selecionada, boolean foco, int linha, int coluna) {

                Livro l = (Livro) valor;
                Color bg = selecionada ? azulSelecao : Color.WHITE;

                JPanel card = new JPanel(new BorderLayout());
                card.setBackground(bg);
                card.setBorder(new EmptyBorder(16, 10, 16, 20));

                JPanel infoTopo = new JPanel();
                infoTopo.setLayout(new BoxLayout(infoTopo, BoxLayout.Y_AXIS));
                infoTopo.setOpaque(false);

                JLabel lblTitulo = new JLabel(l.getTitulo());
                lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));
                lblTitulo.setForeground(azulMarinho);

                JLabel lblAutor = new JLabel(l.getAutor());
                lblAutor.setFont(new Font("SansSerif", Font.PLAIN, 13));
                lblAutor.setForeground(textoCinza);
                lblAutor.setBorder(new EmptyBorder(3, 0, 0, 0));

                infoTopo.add(lblTitulo);
                infoTopo.add(lblAutor);

                JPanel infoBase = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                infoBase.setOpaque(false);

                JLabel tagAno = new JLabel(l.getAno() + "  •  ID #" + l.getId());
                tagAno.setFont(new Font("SansSerif", Font.PLAIN, 11));
                tagAno.setForeground(textoEscuro);
                tagAno.setOpaque(true);
                tagAno.setBackground(fundoPagina);
                tagAno.setBorder(new EmptyBorder(4, 10, 4, 10));

                JLabel btnVisual = new JLabel("Ver detalhes");
                btnVisual.setFont(new Font("SansSerif", Font.BOLD, 11));
                btnVisual.setForeground(Color.WHITE);
                btnVisual.setOpaque(true);
                btnVisual.setBackground(azulMarinho);
                btnVisual.setBorder(new EmptyBorder(5, 12, 5, 12));

                infoBase.add(tagAno);
                infoBase.add(Box.createHorizontalStrut(10));
                infoBase.add(btnVisual);

                card.add(infoTopo, BorderLayout.NORTH);
                card.add(infoBase, BorderLayout.SOUTH);

                return card;
            }
        });

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(new LineBorder(bordaSuave, 1));
        scroll.getViewport().setBackground(Color.WHITE);
        corpo.add(scroll, BorderLayout.CENTER);

        // --- RODAPÉ ---
        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setBackground(fundoPagina);
        rodape.setBorder(new EmptyBorder(0, 24, 12, 24));
        status.setFont(new Font("SansSerif", Font.BOLD, 12));
        status.setForeground(textoCinza);
        rodape.add(status, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);
        add(corpo, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        // Eventos
        botaoBuscarId.addActionListener(e -> buscarPorId());
        botaoBuscarTitulo.addActionListener(e -> buscarPorTitulo());
        campoTitulo.addActionListener(e -> buscarPorTitulo());
        campoId.addActionListener(e -> buscarPorId());
        botaoCapa.addActionListener(e -> selecionarCapa());
        botaoNovo.addActionListener(e -> novoLivro());
        botaoMostrarTodos.addActionListener(e -> carregarLivros());
        botaoLimpar.addActionListener(e -> limparCampos());

        carregarLivros();
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campo.setBorder(new CompoundBorder(
            new LineBorder(bordaSuave, 1),
            new EmptyBorder(5, 8, 5, 8)
        ));
    }

    private JButton criarBotao(String texto, Color fundo, Color textoCor) {
        JButton botao = new JButton(texto);
        botao.setBackground(fundo);
        botao.setForeground(textoCor);
        botao.setFont(new Font("SansSerif", Font.BOLD, 12));
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Color borda = fundo.equals(Color.WHITE) ? bordaSuave : azulMarinho;
        botao.setBorder(new CompoundBorder(
            new LineBorder(borda, 1),
            new EmptyBorder(6, 12, 6, 12)
        ));
        return botao;
    }

    private ImageIcon carregarMiniatura(String caminho) {
        if (caminho == null || caminho.isBlank()) return null;
        File arquivo = new File(caminho);
        if (!arquivo.isFile()) return null;

        try {
            BufferedImage imagem = ImageIO.read(arquivo);
            if (imagem == null) return null;
            Image reduzida = imagem.getScaledInstance(80, 110, Image.SCALE_SMOOTH);
            return new ImageIcon(reduzida);
        } catch (IOException e) {
            return null;
        }
    }

    private void adicionarLinha(Livro livro) {
        modelo.addRow(new Object[]{
            carregarMiniatura(livro.getCapa()),
            livro
        });
    }

    private void selecionarCapa() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um livro na lista primeiro.");
            return;
        }

        int linhaModelo = tabela.convertRowIndexToModel(linha);
        Livro livroSelecionado = (Livro) modelo.getValueAt(linhaModelo, 1);
        int id = livroSelecionado.getId();

        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Selecione a capa do livro");
        seletor.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens JPG e PNG", "jpg", "jpeg", "png"));

        if (seletor.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

        java.nio.file.Path destino = null;
        try {
            destino = java.nio.file.Path.of(ImagemLivro.salvar(seletor.getSelectedFile().toPath(), id));
            boolean atualizado = dao.atualizarCapa(id, destino.toString().replace('\\', '/'));

            if (!atualizado) {
                java.nio.file.Files.deleteIfExists(destino);
                JOptionPane.showMessageDialog(this, "Não foi possível atualizar o livro.");
                return;
            }

            carregarLivros();
            JOptionPane.showMessageDialog(this, "Capa salva com sucesso!");

        } catch (Exception ex) {
            if (destino != null) {
                try { java.nio.file.Files.deleteIfExists(destino); } catch (IOException ignorado) {}
            }
            JOptionPane.showMessageDialog(this, "Erro ao salvar capa: " + ex.getMessage());
        }
    }

    private void buscarPorTitulo() {
        String titulo = campoTitulo.getText().trim();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o título do livro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Livro> livros;
        try {
            livros = dao.buscarLivrosPorTitulo(titulo);
        } catch (PersistenciaException e) {
            status.setText("Erro ao consultar o banco.");
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível buscar os livros. Verifique a conexão com o MariaDB.",
                "Erro de conexão",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        modelo.setRowCount(0);
        for (Livro l : livros) adicionarLinha(l);
        status.setText("Livros encontrados: " + livros.size());
    }

    private void buscarPorId() {
        String idTexto = campoId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o ID do livro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(idTexto);
            Livro livro;
            try {
                livro = dao.buscarLivroPorId(id);
            } catch (PersistenciaException e) {
                status.setText("Erro ao consultar o banco.");
                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível buscar o livro. Verifique a conexão com o MariaDB.",
                    "Erro de conexão",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            modelo.setRowCount(0);
            if (livro != null) {
                adicionarLinha(livro);
                status.setText("Livros encontrados: 1");
            } else {
                status.setText("Livros encontrados: 0");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID numérico inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarLivros() {
        try {
            List<Livro> livros = dao.listarLivros();
            modelo.setRowCount(0);
            for (Livro l : livros) adicionarLinha(l);
            status.setText("Livros encontrados: " + livros.size());
        } catch (PersistenciaException e) {
            status.setText("Erro ao consultar o banco.");
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoTitulo.setText("");
        carregarLivros();
    }

    private void novoLivro() {
        new TelaCadastroLivro(this).setVisible(true);
        carregarLivros();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLivros().setVisible(true));
    }
}
