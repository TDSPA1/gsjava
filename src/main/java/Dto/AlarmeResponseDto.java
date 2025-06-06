package Dto;

import java.time.LocalDateTime;

public class AlarmeResponseDto {
    public int id;
    public String tipo;
    public String localizacao;
    public boolean ativo;
    public LocalDateTime data;
    public SensorRequestDto sensor;

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

    public SensorRequestDto getSensor() {
        return sensor;
    }

    public void setSensor(SensorRequestDto sensor) {
        this.sensor = sensor;
    }
}
