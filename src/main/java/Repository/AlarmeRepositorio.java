package Repository;

import Entidade.Alarme;
import Infrastructure.DataBaseConfig;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlarmeRepositorio {
    public List<Alarme> findAll() throws SQLException {
        List<Alarme> resultList = new ArrayList<>();
        String sql = "SELECT id, tipo, localizacao, ativo, data, sensorId FROM alarmes";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idAlarme = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String localizacao = rs.getString("localizacao");
                boolean ativo = rs.getBoolean("ativo");
                LocalDateTime data = rs.getTimestamp("data").toLocalDateTime();
                int sensorId = rs.getInt("sensorId"); // ID do sensor

                Alarme alarme = new Alarme(idAlarme, tipo, localizacao, ativo, data, sensorId);
                resultList.add(alarme);
            }
        }
        return resultList;
    }

    public Optional<Alarme> findById(int id) throws SQLException {
        String sql = "SELECT id, tipo, localizacao, ativo, data, sensorId FROM alarmes WHERE id = ?";
        Alarme alarme = null;

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idAlarme = rs.getInt("id");
                    String tipo = rs.getString("tipo");
                    String localizacao = rs.getString("localizacao");
                    boolean ativo = rs.getBoolean("ativo");
                    LocalDateTime data = rs.getTimestamp("data").toLocalDateTime();
                    int sensorId = rs.getInt("sensorId");

                    alarme = new Alarme(idAlarme, tipo, localizacao, ativo, data, sensorId);
                }
            }
        }
        return Optional.ofNullable(alarme);
    }

    public Alarme add(Alarme alarme) throws SQLException {

        String sql = "INSERT INTO alarmes (tipo, localizacao, ativo, data, sensorId) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID"})) {
            stmt.setString(1, alarme.getTipo());
            stmt.setString(2, alarme.getLocalizacao());
            stmt.setBoolean(3, alarme.isAtivo());
            stmt.setTimestamp(4, Timestamp.valueOf(alarme.getData()));
            stmt.setInt(5, alarme.getSensor().getId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    alarme.setId(generatedKeys.getInt(1));
                }
            }
        }
        return alarme;
    }

    public void update(Alarme alarme) throws SQLException {
        String sql = "UPDATE alarmes SET tipo = ?, localizacao = ?, ativo = ?, data = ?, sensorId = ? WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, alarme.getTipo());
            stmt.setString(2, alarme.getLocalizacao());
            stmt.setBoolean(3, alarme.isAtivo());
            stmt.setTimestamp(4, Timestamp.valueOf(alarme.getData()));
            stmt.setInt(5, alarme.getSensor().getId());
            stmt.setInt(6, alarme.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM alarmes WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
