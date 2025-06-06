package Entidade;

import java.time.LocalDateTime;
import java.util.Objects;

public class Alarme {
    private int id;
    private String tipo;
    private String localizacao;
    private boolean ativo;
    private LocalDateTime data;
    private Sensor sensor;
    private int sensorId;

    public Alarme() {
    }

    public Alarme(int id, String tipo, String localizacao, boolean ativo, LocalDateTime data, int sensorId) {
        this.id = id;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.ativo = ativo;
        this.data = data;
        this.sensorId = sensorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Alarme alarme = (Alarme) object;
        return getId() == alarme.getId() && isAtivo() == alarme.isAtivo() && getSensorId() == alarme.getSensorId() && Objects.equals(getTipo(), alarme.getTipo()) && Objects.equals(getLocalizacao(), alarme.getLocalizacao()) && Objects.equals(getData(), alarme.getData()) && Objects.equals(getSensor(), alarme.getSensor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTipo(), getLocalizacao(), isAtivo(), getData(), getSensor(), getSensorId());
    }

    @Override
    public String toString() {
        return "Alarme{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", ativo=" + ativo +
                ", data=" + data +
                ", sensor=" + sensor +
                ", sensorId=" + sensorId +
                '}';
    }
}
