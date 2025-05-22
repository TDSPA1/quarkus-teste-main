package Entidades;

import java.util.Objects;

public class Manutencao {
    private int id;
    private TipoManutencao tipo; //tipo de manutencao (Preventiva,emergencia,Corretiva,Evolutiva)
    private String tecnicoResponsavel;
    private String descricao;
    private String localizacao;

    public Manutencao() {
    }

    public Manutencao(int id, TipoManutencao tipo, String tecnicoResponsavel, String descricao, String localizacao) {
        this.id = id;
        this.tipo = tipo;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.descricao = descricao;
        this.localizacao = localizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoManutencao getTipo() {
        return tipo;
    }

    public void setTipo(TipoManutencao tipo) {
        this.tipo = tipo;
    }

    public String getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(String tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Manutencao that)) return false;
        return getId() == that.getId() && getTipo() == that.getTipo() && Objects.equals(getTecnicoResponsavel(), that.getTecnicoResponsavel()) && Objects.equals(getDescricao(), that.getDescricao()) && Objects.equals(getLocalizacao(), that.getLocalizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTipo(), getTecnicoResponsavel(), getDescricao(), getLocalizacao());
    }

    @Override
    public String toString() {
        return "Manutencao{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", tecnicoResponsavel='" + tecnicoResponsavel + '\'' +
                ", descricao='" + descricao + '\'' +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}
