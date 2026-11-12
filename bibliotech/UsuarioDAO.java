import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public Usuario buscarPorId(int id) {
        String sql = "SELECT id, nome, email, foto FROM usuarios WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    return new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("email"),
                        resultado.getString("foto")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarFotoUsuario(int id, String caminho) {
        String sql = "UPDATE usuarios SET foto = ? WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, caminho);
            comando.setInt(2, id);
            return comando.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar foto: " + e.getMessage());
            return false;
        }
    }
}