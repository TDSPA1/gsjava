package Repository;

import Entidade.Usuario;
import Infrastructure.DataBaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositorio {
    public List<Usuario> findAll() throws Exception {
        List<Usuario> resultList = new ArrayList<>();
        // Adapte as colunas para sua tabela 'usuarios'
        String sql = "SELECT id, nome, email, telefone, senha FROM usuarios";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Adapte os getters/setters da classe Usuario
                resultList.add(new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"), rs.getString("senha")));
            }
        }

        return resultList;
    }

    public Optional<Usuario> findById(int id) throws Exception {
        // Adapte as colunas para sua tabela 'usuarios'
        String sql = "SELECT id, nome, email, telefone, senha FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"), rs.getString("senha"));
                }
            }
        }

        return Optional.ofNullable(usuario);
    }

    public Usuario add(Usuario usuario) throws Exception {

        String sql = "INSERT INTO usuarios (nome, email, telefone, senha) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID"})) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getSenha());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                }
            }
        }

        return usuario;
    }

    public void update(Usuario usuario) throws Exception {

        String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ?, senha = ? WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getSenha());
            stmt.setInt(5, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

