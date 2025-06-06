package Dto;

import java.time.LocalDateTime;

public class OcorrenciaResponseDto {
    public int id;
    public String descricao;
    public LocalDateTime data;
    public int alarmeId;

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

    public int getAlarmeId() {
        return alarmeId;
    }

    public void setAlarmeId(int alarmeId) {
        this.alarmeId = alarmeId;
    }
}
