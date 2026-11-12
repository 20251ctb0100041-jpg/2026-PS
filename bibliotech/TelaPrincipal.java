import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private static final Color AZUL_MARINHO = new Color(13, 33, 79);
    private static final Color FUNDO_GERAL  = new Color(245, 247, 250);
    private static final Color TEXTO_SUAVE  = new Color(100, 110, 125);

    public TelaPrincipal() {
        setTitle("BiblioTech - Sistema de Gestão de Biblioteca");
        setSize(520, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(FUNDO_GERAL);
        setLayout(new BorderLayout());

        // Cabeçalho institucional
        JPanel header = new JPanel(new GridLayout(2, 1, 0, 4));
        header.setBackground(AZUL_MARINHO);
        header.setBorder(new EmptyBorder(20, 26, 20, 26));

        JLabel titulo = new JLabel("BiblioTech");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel subtitulo = new JLabel("Portal Acadêmico • Acervo e Perfil do Leitor");
        subtitulo.setForeground(new Color(200, 215, 240));
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));

        header.add(titulo);
        header.add(subtitulo);
        add(header, BorderLayout.NORTH);

        // Área central de navegação
        JPanel centro = new JPanel(new GridLayout(1, 2, 16, 0));
        centro.setBackground(FUNDO_GERAL);
        centro.setBorder(new EmptyBorder(26, 26, 26, 26));

        JButton btnLivros = criarCardBotao("Acervo de Livros", "Consultar títulos e gerenciar capas");
        JButton btnPerfil = criarCardBotao("Meu Perfil", "Visualizar dados e alterar fotografia");

        btnLivros.addActionListener(e -> {
            TelaLivros tela = new TelaLivros();
            tela.setLocationRelativeTo(this);
            tela.setVisible(true);
        });

        btnPerfil.addActionListener(e -> {
            TelaPerfilUsuario perfil = new TelaPerfilUsuario(1);
            perfil.setLocationRelativeTo(this);
            perfil.setVisible(true);
        });

        centro.add(btnLivros);
        centro.add(btnPerfil);
        add(centro, BorderLayout.CENTER);

        // Rodapé discreto
        JLabel rodape = new JLabel("Conectado ao banco MariaDB • Identidade Visual Azul-Marinho", SwingConstants.CENTER);
        rodape.setFont(new Font("SansSerif", Font.PLAIN, 11));
        rodape.setForeground(TEXTO_SUAVE);
        rodape.setBorder(new EmptyBorder(0, 0, 12, 0));
        add(rodape, BorderLayout.SOUTH);
    }

    private JButton criarCardBotao(String titulo, String descricao) {
        JButton btn = new JButton("<html><center><b style='font-size:13px; color:#0D214F;'>" + titulo +
                "</b><br><br><span style='font-size:10px; color:#5A6577;'>" + descricao + "</span></center></html>");
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(210, 218, 232), 1, true),
                new EmptyBorder(16, 12, 16, 12)
        ));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
