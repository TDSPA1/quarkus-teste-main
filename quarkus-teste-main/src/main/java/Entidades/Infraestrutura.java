package Entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Infraestrutura {
    private int id;
    private String localizacao;
    private String estadoDeConservacao;
    private LocalDateTime dataDeManutencao;
    private String linha;

    public Infraestrutura() {
    }

    public Infraestrutura(int id, String localizacao, String estadoDeConservacao, LocalDateTime dataDeManutencao, String linha) {
        this.id = id;
        this.localizacao = localizacao;
        this.estadoDeConservacao = estadoDeConservacao;
        this.dataDeManutencao = dataDeManutencao;
        this.linha = linha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getEstadoDeConservacao() {
        return estadoDeConservacao;
    }

    public void setEstadoDeConservacao(String estadoDeConservacao) {
        this.estadoDeConservacao = estadoDeConservacao;
    }

    public LocalDateTime getDataDeManutencao() {
        return dataDeManutencao;
    }

    public void setDataDeManutencao(LocalDateTime dataDeManutencao) {
        this.dataDeManutencao = dataDeManutencao;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Infraestrutura that)) return false;
        return getId() == that.getId() && Objects.equals(getLocalizacao(), that.getLocalizacao()) && Objects.equals(getEstadoDeConservacao(), that.getEstadoDeConservacao()) && Objects.equals(getDataDeManutencao(), that.getDataDeManutencao()) && Objects.equals(getLinha(), that.getLinha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLocalizacao(), getEstadoDeConservacao(), getDataDeManutencao(), getLinha());
    }

    @Override
    public String toString() {
        return "Infraestrutura{" +
                "id=" + id +
                ", localizacao='" + localizacao + '\'' +
                ", estadoDeConservacao='" + estadoDeConservacao + '\'' +
                ", dataDeManutencao=" + dataDeManutencao +
                ", linha='" + linha + '\'' +
                '}';
    }
}
