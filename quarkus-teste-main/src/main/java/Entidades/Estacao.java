package Entidades;

import java.util.ArrayList;
import java.util.Objects;

public class Estacao {
    private int id;
    private String nome;
    private Linha linha;

    public Estacao() {
    }

    public Estacao(int id, String nome, Linha linha) {
        this.id = id;
        this.nome = nome;
        this.linha = linha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Estacao estacao)) return false;
        return getId() == estacao.getId() && Objects.equals(getNome(), estacao.getNome()) && Objects.equals(getLinha(), estacao.getLinha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getLinha());
    }

    @Override
    public String toString() {
        return "Estacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", linha=" + linha +
                '}';
    }
}