package Entidade;

import java.util.Objects;

public class Endereco {
    private int id;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private Usuario usuario;
    private int usuarioID;

    public Endereco() {
    }

    public Endereco(int id, String rua, String bairro, String cidade, String estado, String cep, int usuarioID) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.usuarioID = usuarioID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Endereco endereco = (Endereco) object;
        return getId() == endereco.getId() && getUsuarioID() == endereco.getUsuarioID() && Objects.equals(getRua(), endereco.getRua()) && Objects.equals(getBairro(), endereco.getBairro()) && Objects.equals(getCidade(), endereco.getCidade()) && Objects.equals(getEstado(), endereco.getEstado()) && Objects.equals(getCep(), endereco.getCep()) && Objects.equals(getUsuario(), endereco.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRua(), getBairro(), getCidade(), getEstado(), getCep(), getUsuario(), getUsuarioID());
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", usuario=" + usuario +
                ", usuarioID=" + usuarioID +
                '}';
    }
}
