package Repository;

import Entidade.Endereco;
import Infrastructure.DataBaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnderecoRepositorio {
    public List<Endereco> findAll() throws SQLException {
        List<Endereco> resultList = new ArrayList<>();
        String sql = "SELECT id, rua, bairro, cidade, estado, cep, usuarioId FROM enderecos";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idEndereco = rs.getInt("id");
                String rua = rs.getString("rua");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String cep = rs.getString("cep");
                int usuarioId = rs.getInt("usuarioId");

                Endereco endereco = new Endereco(idEndereco, rua, bairro, cidade, estado, cep, usuarioId);
                resultList.add(endereco);
            }
        }
        return resultList;
    }

    public Optional<Endereco> findById(int id) throws SQLException {
        String sql = "SELECT id, rua, bairro, cidade, estado, cep, usuarioId FROM enderecos WHERE id = ?";
        Endereco endereco = null;

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idEndereco = rs.getInt("id");
                    String rua = rs.getString("rua");
                    String bairro = rs.getString("bairro");
                    String cidade = rs.getString("cidade");
                    String estado = rs.getString("estado");
                    String cep = rs.getString("cep");
                    int usuarioId = rs.getInt("usuarioId");

                    endereco = new Endereco(idEndereco, rua, bairro, cidade, estado, cep, usuarioId);
                }
            }
        }
        return Optional.ofNullable(endereco);
    }

    public Endereco add(Endereco endereco) throws SQLException {

        String sql = "INSERT INTO enderecos (rua, bairro, cidade, estado, cep, usuarioId) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID"})) {
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getEstado());
            stmt.setString(5, endereco.getCep());
            stmt.setInt(6, endereco.getUsuario().getId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    endereco.setId(generatedKeys.getInt(1));
                }
            }
        }
        return endereco;
    }

    public void update(Endereco endereco) throws SQLException {
        String sql = "UPDATE enderecos SET rua = ?, bairro = ?, cidade = ?, estado = ?, cep = ?, usuarioId = ? WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getEstado());
            stmt.setString(5, endereco.getCep());
            stmt.setInt(6, endereco.getUsuario().getId());
            stmt.setInt(7, endereco.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM enderecos WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
