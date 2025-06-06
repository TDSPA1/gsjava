package Dto;

import java.time.LocalDateTime;

public class OcorrenciaRequestDto {
    public String descricao;
    public LocalDateTime data;
    public int alarmeId;

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

    public int getAlarmeId() {
        return alarmeId;
    }

    public void setAlarmeId(int alarmeId) {
        this.alarmeId = alarmeId;
    }
}
