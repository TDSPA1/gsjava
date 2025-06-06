package Repository;

import Entidade.Ocorrencia;
import Infrastructure.DataBaseConfig;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OcorrenciaRepositorio {
    public List<Ocorrencia> findAll() throws SQLException {
        List<Ocorrencia> resultList = new ArrayList<>();
        String sql = "SELECT id, descricao, data, alarmeId FROM ocorrencias";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idOcorrencia = rs.getInt("id");
                String descricao = rs.getString("descricao");
                LocalDateTime data = rs.getTimestamp("data").toLocalDateTime();
                int alarmeId = rs.getInt("alarmeId");

                Ocorrencia ocorrencia = new Ocorrencia(idOcorrencia, descricao, data, alarmeId);
                resultList.add(ocorrencia);
            }
        }
        return resultList;
    }

    public Optional<Ocorrencia> findById(int id) throws SQLException {
        String sql = "SELECT id, descricao, data, alarmeId FROM ocorrencias WHERE id = ?";
        Ocorrencia ocorrencia = null;

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idOcorrencia = rs.getInt("id");
                    String descricao = rs.getString("descricao");
                    LocalDateTime data = rs.getTimestamp("data").toLocalDateTime();
                    int alarmeId = rs.getInt("alarmeId");

                    ocorrencia = new Ocorrencia(idOcorrencia, descricao, data, alarmeId);
                }
            }
        }
        return Optional.ofNullable(ocorrencia);
    }

    public Ocorrencia add(Ocorrencia ocorrencia) throws SQLException {
        // Assume que a tabela 'ocorrencias' tem um ID auto-incrementável
        // e uma coluna 'alarmeId' para a chave estrangeira.
        String sql = "INSERT INTO ocorrencias (descricao, data, alarmeId) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID"})) {
            stmt.setString(1, ocorrencia.getDescricao());
            stmt.setTimestamp(2, Timestamp.valueOf(ocorrencia.getData()));
            stmt.setInt(3, ocorrencia.getAlarme().getId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ocorrencia.setId(generatedKeys.getInt(1));
                }
            }
        }
        return ocorrencia;
    }

    public void update(Ocorrencia ocorrencia) throws SQLException {
        String sql = "UPDATE ocorrencias SET descricao = ?, data = ?, alarmeId = ? WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ocorrencia.getDescricao());
            stmt.setTimestamp(2, Timestamp.valueOf(ocorrencia.getData()));
            stmt.setInt(3, ocorrencia.getAlarme().getId());
            stmt.setInt(4, ocorrencia.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM ocorrencias WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
