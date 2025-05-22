package Entidades;

import java.util.Objects;

public class Notificacao {
    private int id;
    private Usuario usuario;
    private String mensagem;
    private String dataEnvio;

    public Notificacao() {
    }

    public Notificacao(int id, Usuario usuario, String mensagem, String dataEnvio) {
        this.id = id;
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Notificacao that)) return false;
        return getId() == that.getId() && Objects.equals(getUsuario(), that.getUsuario()) && Objects.equals(getMensagem(), that.getMensagem()) && Objects.equals(getDataEnvio(), that.getDataEnvio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario(), getMensagem(), getDataEnvio());
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", mensagem='" + mensagem + '\'' +
                ", dataEnvio='" + dataEnvio + '\'' +
                '}';
    }
}
