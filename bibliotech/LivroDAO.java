import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public boolean existeLivro(String titulo, String autor) {
        String sql = "SELECT COUNT(*) FROM livros WHERE LOWER(TRIM(titulo)) = LOWER(TRIM(?)) AND LOWER(TRIM(autor)) = LOWER(TRIM(?))";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível verificar duplicidade no acervo.", e);
        }
    }

    public boolean adicionarLivro(Livro livro) throws LivroInvalidoException {
        ValidadorLivro.validar(livro.getTitulo(), livro.getAutor(), livro.getAno());

        if (existeLivro(livro.getTitulo(), livro.getAutor())) {
            throw new LivroInvalidoException("Já existe um livro cadastrado com este título e autor.");
        }

        String sql = "INSERT INTO livros (titulo, autor, ano, capa) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getCapa());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível salvar o livro no MariaDB.", e);
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, ano, capa FROM livros ORDER BY id DESC";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getInt("ano"),
                    rs.getString("capa")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível consultar os livros no MariaDB.", e);
        }
    }

    public Livro buscarLivroPorId(int id) {
        String sql = "SELECT id, titulo, autor, ano, capa FROM livros WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        rs.getString("capa")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível buscar o livro pelo ID " + id + ".", e);
        }
    }

    public Livro buscarPorId(int id) {
        return buscarLivroPorId(id);
    }

    public List<Livro> buscarLivrosPorTitulo(String termo) {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, ano, capa FROM livros WHERE titulo LIKE ? ORDER BY id DESC";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + termo + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        rs.getString("capa")
                    ));
                }
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível pesquisar livros por título.", e);
        }
    }

    public List<Livro> buscarPorTitulo(String termo) {
        return buscarLivrosPorTitulo(termo);
    }

    public boolean atualizarCapa(int idLivro, String caminhoCapa) {
        String sql = "UPDATE livros SET capa = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caminhoCapa);
            stmt.setInt(2, idLivro);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível atualizar o caminho da capa no banco.", e);
        }
    }

    public boolean excluirLivro(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Não foi possível excluir o livro.", e);
        }
    }
}
