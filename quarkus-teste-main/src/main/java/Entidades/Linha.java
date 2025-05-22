package Entidades;

import java.util.Objects;

public class Linha {
    private int id;
    private String nome;
    private CorLinha cor;

    public Linha() {
    }

    public Linha(int id, String nome, CorLinha cor) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
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

    public CorLinha getCor() {
        return cor;
    }

    public void setCor(CorLinha cor) {
        this.cor = cor;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Linha linha)) return false;
        return getId() == linha.getId() && Objects.equals(getNome(), linha.getNome()) && getCor() == linha.getCor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCor());
    }

    @Override
    public String toString() {
        return "Linha{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cor=" + cor +
                '}';
    }
}
