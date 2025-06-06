package Dto;

import java.time.LocalDateTime;

public class NotificacaoResponseDto {
    public int id;
    public String mensagem;
    public boolean enviada;
    public LocalDateTime data;
    public int usuarioId;
    public int alarmeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isEnviada() {
        return enviada;
    }

    public void setEnviada(boolean enviada) {
        this.enviada = enviada;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getAlarmeId() {
        return alarmeId;
    }

    public void setAlarmeId(int alarmeId) {
        this.alarmeId = alarmeId;
    }
}
