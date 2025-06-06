package Repository;

import Entidade.Sensor;
import Infrastructure.DataBaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SensorRepositorio {
    public List<Sensor> findAll() throws SQLException {
        List<Sensor> resultList = new ArrayList<>();
        String sql = "SELECT id, tipo, localizacao, ativo FROM sensores";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                resultList.add(new Sensor(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("localizacao"),
                        rs.getBoolean("ativo")
                ));
            }
        }
        return resultList;
    }

    public Optional<Sensor> findById(int id) throws SQLException {
        String sql = "SELECT id, tipo, localizacao, ativo FROM sensores WHERE id = ?";
        Sensor sensor = null;

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sensor = new Sensor(
                            rs.getInt("id"),
                            rs.getString("tipo"),
                            rs.getString("localizacao"),
                            rs.getBoolean("ativo")
                    );
                }
            }
        }
        return Optional.ofNullable(sensor);
    }

    public Sensor add(Sensor sensor) throws SQLException {

        String sql = "INSERT INTO sensores (tipo, localizacao, ativo) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID"})) {
            stmt.setString(1, sensor.getTipo());
            stmt.setString(2, sensor.getLocalizacao());
            stmt.setBoolean(3, sensor.isAtivo());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    sensor.setId(generatedKeys.getInt(1));
                }
            }
        }
        return sensor;
    }

    public void update(Sensor sensor) throws SQLException {
        String sql = "UPDATE sensores SET tipo = ?, localizacao = ?, ativo = ? WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sensor.getTipo());
            stmt.setString(2, sensor.getLocalizacao());
            stmt.setBoolean(3, sensor.isAtivo());
            stmt.setInt(4, sensor.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM sensores WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
