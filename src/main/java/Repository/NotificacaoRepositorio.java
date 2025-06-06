package Repository;

import Entidade.Notificacao;
import Infrastructure.DataBaseConfig;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotificacaoRepositorio {
    public List<Notificacao> findAll() throws SQLException {
        List<Notificacao> resultList = new ArrayList<>();
        String sql = "SELECT id, mensagem, enviada, data, usuarioId, alarmeId FROM notificacoes";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idNotificacao = rs.getInt("id");
                String mensagem = rs.getString("mensagem");
                boolean enviada = rs.getBoolean("enviada");
                LocalDateTime data = rs.getTimestamp("data").toLocalDateTime();
                int usuarioId = rs.getInt("usuarioId");
                int alarmeId = rs.getInt("alarmeId");

                Notificacao notificacao = new Notificacao(idNotificacao, mensagem, enviada, data, usuarioId, alarmeId);
                resultList.add(notificacao);
            }
        }
        return resultList;
    }

    public Optional<Notificacao> findById(int id) throws SQLException {
        String sql = "SELECT id, mensagem, enviada, data, usuarioId, alarmeId FROM notificacoes WHERE id = ?";
        Notificacao notificacao = null;

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idNotificacao = rs.getInt("id");
                    String mensagem = rs.getString("mensagem");
                    boolean enviada = rs.getBoolean("enviada");
                    LocalDateTime data = rs.getTimestamp("data").toLocalDateTime();
                    int usuarioId = rs.getInt("usuarioId");
                    int alarmeId = rs.getInt("alarmeId");

                    notificacao = new Notificacao(idNotificacao, mensagem, enviada, data, usuarioId, alarmeId);
                }
            }
        }
        return Optional.ofNullable(notificacao);
    }

    public Notificacao add(Notificacao notificacao) throws SQLException {

        String sql = "INSERT INTO notificacoes (mensagem, enviada, data, usuarioId, alarmeId) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID"})) {
            stmt.setString(1, notificacao.getMensagem());
            stmt.setBoolean(2, notificacao.isEnviada());
            stmt.setTimestamp(3, Timestamp.valueOf(notificacao.getData()));
            stmt.setInt(4, notificacao.getUsuario().getId());
            stmt.setInt(5, notificacao.getAlarme().getId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    notificacao.setId(generatedKeys.getInt(1));
                }
            }
        }
        return notificacao;
    }


    public void update(Notificacao notificacao) throws SQLException {
        String sql = "UPDATE notificacoes SET mensagem = ?, enviada = ?, data = ?, usuarioId = ?, alarmeId = ? WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, notificacao.getMensagem());
            stmt.setBoolean(2, notificacao.isEnviada());
            stmt.setTimestamp(3, Timestamp.valueOf(notificacao.getData()));
            stmt.setInt(4, notificacao.getUsuario().getId());
            stmt.setInt(5, notificacao.getAlarme().getId());
            stmt.setInt(6, notificacao.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM notificacoes WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
