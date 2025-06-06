package Entidade;

import java.util.Objects;

public class Sensor {
    private int id;
    private String tipo;
    private String localizacao;
    private boolean ativo;

    public Sensor() {
    }

    public Sensor(int id, String tipo, String localizacao, boolean ativo) {
        this.id = id;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.ativo = ativo;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Sensor sensor = (Sensor) object;
        return getId() == sensor.getId() && isAtivo() == sensor.isAtivo() && Objects.equals(getTipo(), sensor.getTipo()) && Objects.equals(getLocalizacao(), sensor.getLocalizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTipo(), getLocalizacao(), isAtivo());
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
