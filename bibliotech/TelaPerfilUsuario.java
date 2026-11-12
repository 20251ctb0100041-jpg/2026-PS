import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;

public class TelaPerfilUsuario extends JFrame {

    private static final Color AZUL_MARINHO = new Color(13, 33, 79);
    private static final Color AZUL_HOVER   = new Color(25, 55, 120);
    private static final Color FUNDO_GERAL  = new Color(245, 247, 250);
    private static final Color TEXTO_ESCURO = new Color(30, 35, 45);
    private static final Color TEXTO_SUAVE  = new Color(100, 110, 125);

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuarioAtual;
    private Path arquivoSelecionado;

    private JLabel lblFotoAtual;
    private JLabel lblPrevia;
    private JLabel lblNome;
    private JLabel lblEmail;
    private JButton btnSalvarFoto;

    public TelaPerfilUsuario(int idUsuario) {
        setTitle("BiblioTech - Meu Perfil");
        setSize(560, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(FUNDO_GERAL);
        setLayout(new BorderLayout());

        // Cabeçalho institucional
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AZUL_MARINHO);
        header.setBorder(new EmptyBorder(16, 24, 16, 24));

        JLabel titulo = new JLabel("Meu Perfil");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));

        JLabel subtitulo = new JLabel("Identidade do leitor e fotografia de cadastro");
        subtitulo.setForeground(new Color(200, 215, 240));
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JPanel textosHeader = new JPanel(new GridLayout(2, 1, 0, 2));
        textosHeader.setOpaque(false);
        textosHeader.add(titulo);
        textosHeader.add(subtitulo);
        header.add(textosHeader, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // Card central
        JPanel card = new JPanel(new BorderLayout(20, 16));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(20, 24, 20, 24),
                BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(220, 226, 235), 1, true),
                        new EmptyBorder(20, 20, 20, 20)
                )
        ));

        // Esquerda: Foto atual + Dados do usuário
        JPanel esquerda = new JPanel(new BorderLayout(16, 0));
        esquerda.setOpaque(false);

        lblFotoAtual = new JLabel("Sem foto", SwingConstants.CENTER);
        lblFotoAtual.setPreferredSize(new Dimension(120, 150));
        lblFotoAtual.setOpaque(true);
        lblFotoAtual.setBackground(new Color(235, 240, 248));
        lblFotoAtual.setForeground(TEXTO_SUAVE);
        lblFotoAtual.setBorder(new LineBorder(new Color(205, 215, 230), 1));

        JPanel infoUsuario = new JPanel();
        infoUsuario.setOpaque(false);
        infoUsuario.setLayout(new BoxLayout(infoUsuario, BoxLayout.Y_AXIS));

        lblNome = new JLabel("Carregando...");
        lblNome.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblNome.setForeground(TEXTO_ESCURO);

        JLabel lblTipo = new JLabel("Leitora cadastrada • ID #" + idUsuario);
        lblTipo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblTipo.setForeground(TEXTO_SUAVE);

        lblEmail = new JLabel("");
        lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblEmail.setForeground(TEXTO_SUAVE);

        JLabel lblEmprestimos = new JLabel("Livros emprestados: 2");
        lblEmprestimos.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblEmprestimos.setForeground(AZUL_MARINHO);

        infoUsuario.add(lblNome);
        infoUsuario.add(Box.createVerticalStrut(4));
        infoUsuario.add(lblTipo);
        infoUsuario.add(Box.createVerticalStrut(4));
        infoUsuario.add(lblEmail);
        infoUsuario.add(Box.createVerticalStrut(12));
        infoUsuario.add(lblEmprestimos);

        esquerda.add(lblFotoAtual, BorderLayout.WEST);
        esquerda.add(infoUsuario, BorderLayout.CENTER);

        // Direita: Prévia antes de salvar (Desafio Adicional)
        JPanel direita = new JPanel(new BorderLayout(0, 6));
        direita.setOpaque(false);

        JLabel tituloPrevia = new JLabel("Prévia da nova foto", SwingConstants.CENTER);
        tituloPrevia.setFont(new Font("SansSerif", Font.BOLD, 12));
        tituloPrevia.setForeground(TEXTO_SUAVE);

        lblPrevia = new JLabel("Nenhuma selecionada", SwingConstants.CENTER);
        lblPrevia.setPreferredSize(new Dimension(120, 150));
        lblPrevia.setOpaque(true);
        lblPrevia.setBackground(FUNDO_GERAL);
        lblPrevia.setForeground(TEXTO_SUAVE);
        lblPrevia.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblPrevia.setBorder(BorderFactory.createDashedBorder(new Color(180, 190, 210)));

        direita.add(tituloPrevia, BorderLayout.NORTH);
        direita.add(lblPrevia, BorderLayout.CENTER);

        card.add(esquerda, BorderLayout.CENTER);
        card.add(direita, BorderLayout.EAST);

        // Rodapé do card com botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        botoes.setOpaque(false);

        JButton btnEscolher = criarBotao("Selecionar fotografia", new Color(230, 236, 245), AZUL_MARINHO);
        btnSalvarFoto = criarBotao("Salvar no perfil", AZUL_MARINHO, Color.WHITE);
        btnSalvarFoto.setEnabled(false);

        btnEscolher.addActionListener(e -> escolherFotografia());
        btnSalvarFoto.addActionListener(e -> salvarFotografia());

        botoes.add(btnEscolher);
        botoes.add(btnSalvarFoto);
        card.add(botoes, BorderLayout.SOUTH);

        add(card, BorderLayout.CENTER);

        carregarUsuario(idUsuario);
    }

    private JButton criarBotao(String texto, Color fundo, Color textoCor) {
        JButton btn = new JButton(texto);
        btn.setBackground(fundo);
        btn.setForeground(textoCor);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setBorder(new EmptyBorder(10, 16, 10, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void carregarUsuario(int idUsuario) {
        usuarioAtual = usuarioDAO.buscarPorId(idUsuario);
        if (usuarioAtual == null) {
            usuarioAtual = new Usuario(idUsuario, "Ana Silva", "ana.silva@bibliotech.edu.br", null);
        }
        lblNome.setText(usuarioAtual.getNome());
        lblEmail.setText(usuarioAtual.getEmail() != null ? usuarioAtual.getEmail() : "Sem e-mail cadastrado");
        exibirImagemNoLabel(usuarioAtual.getFoto(), lblFotoAtual, "Sem foto");
    }

    private void escolherFotografia() {
        JFileChooser seletor = new JFileChooser(new File("."));
        seletor.setDialogTitle("Escolher fotografia de perfil");
        seletor.setFileFilter(new FileNameExtensionFilter("Imagens (JPG, PNG)", "jpg", "jpeg", "png"));

        if (seletor.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            arquivoSelecionado = seletor.getSelectedFile().toPath();
            exibirImagemNoLabel(arquivoSelecionado.toString(), lblPrevia, "Inválida");
            btnSalvarFoto.setEnabled(true);
        }
    }

    private void salvarFotografia() {
        if (arquivoSelecionado == null || usuarioAtual == null) return;
        try {
            String caminhoSalvo = ImagemUsuario.salvar(arquivoSelecionado, usuarioAtual.getId());
            if (usuarioDAO.atualizarFotoUsuario(usuarioAtual.getId(), caminhoSalvo)) {
                usuarioAtual.setFoto(caminhoSalvo);
                exibirImagemNoLabel(caminhoSalvo, lblFotoAtual, "Sem foto");
                lblPrevia.setIcon(null);
                lblPrevia.setText("Salva!");
                btnSalvarFoto.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Fotografia atualizada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar caminho no banco.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao salvar imagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exibirImagemNoLabel(String caminho, JLabel destino, String fallback) {
        if (caminho != null && !caminho.isBlank() && new File(caminho).exists()) {
            ImageIcon icone = new ImageIcon(caminho);
            Image imagem = icone.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH);
            destino.setIcon(new ImageIcon(imagem));
            destino.setText("");
        } else {
            destino.setIcon(null);
            destino.setText(fallback);
        }
    }
}
