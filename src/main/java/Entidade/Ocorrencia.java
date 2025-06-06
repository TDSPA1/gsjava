package Entidade;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ocorrencia {
    private int id;
    private String descricao;
    private LocalDateTime data;
    private Alarme alarme;
    private int alarmeId;


    public Ocorrencia() {
    }

    public Ocorrencia(int id, String descricao, LocalDateTime data, int alarmeId) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.alarmeId = alarmeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Alarme getAlarme() {
        return alarme;
    }

    public void setAlarme(Alarme alarme) {
        this.alarme = alarme;
    }

    public int getAlarmeId() {
        return alarmeId;
    }

    public void setAlarmeId(int alarmeId) {
        this.alarmeId = alarmeId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Ocorrencia that = (Ocorrencia) object;
        return getId() == that.getId() && getAlarmeId() == that.getAlarmeId() && Objects.equals(getDescricao(), that.getDescricao()) && Objects.equals(getData(), that.getData()) && Objects.equals(getAlarme(), that.getAlarme());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getData(), getAlarme(), getAlarmeId());
    }

    @Override
    public String toString() {
        return "Ocorrencia{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", alarme=" + alarme +
                ", alarmeId=" + alarmeId +
                '}';
    }
}
