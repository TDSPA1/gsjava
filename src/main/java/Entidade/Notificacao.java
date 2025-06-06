package Entidade;

import java.time.LocalDateTime;
import java.util.Objects;

public class Notificacao {
    private int id;
    private String mensagem;
    private boolean enviada;
    private LocalDateTime data;
    private Usuario usuario;
    private Alarme alarme;
    private int usuarioId;
    private int alarmeId;

    public Notificacao() {
    }

    public Notificacao(int id, String mensagem, boolean enviada, LocalDateTime data, int usuarioId, int alarmeId) {
        this.id = id;
        this.mensagem = mensagem;
        this.enviada = enviada;
        this.data = data;
        this.usuarioId = usuarioId;
        this.alarmeId = alarmeId;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Alarme getAlarme() {
        return alarme;
    }

    public void setAlarme(Alarme alarme) {
        this.alarme = alarme;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Notificacao that = (Notificacao) object;
        return getId() == that.getId() && isEnviada() == that.isEnviada() && getUsuarioId() == that.getUsuarioId() && getAlarmeId() == that.getAlarmeId() && Objects.equals(getMensagem(), that.getMensagem()) && Objects.equals(getData(), that.getData()) && Objects.equals(getUsuario(), that.getUsuario()) && Objects.equals(getAlarme(), that.getAlarme());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMensagem(), isEnviada(), getData(), getUsuario(), getAlarme(), getUsuarioId(), getAlarmeId());
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "id=" + id +
                ", mensagem='" + mensagem + '\'' +
                ", enviada=" + enviada +
                ", data=" + data +
                ", usuario=" + usuario +
                ", alarme=" + alarme +
                ", usuarioId=" + usuarioId +
                ", alarmeId=" + alarmeId +
                '}';
    }
}
