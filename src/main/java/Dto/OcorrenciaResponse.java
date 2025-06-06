package Dto;

import Dto.AlarmeResponseDto;
import Entidade.Alarme;

import java.time.LocalDateTime;

public class OcorrenciaResponse {
    private int id;
    private String descricao;
    private LocalDateTime data;
    private AlarmeResponseDto alarme;

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

    public AlarmeResponseDto getAlarme() {
        return alarme;
    }

    public void setAlarme(AlarmeResponseDto alarme) {
        this.alarme = alarme;
    }
}
