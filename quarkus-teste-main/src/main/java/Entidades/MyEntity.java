package Entidades;

import java.util.Objects;

public class MyEntity {
    private int id;
    private String nome;

    public MyEntity() {
    }

    public MyEntity(int id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof MyEntity myEntity)) return false;
        return getId() == myEntity.getId() && Objects.equals(getNome(), myEntity.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome());
    }

    @Override
    public String toString() {
        return "MyEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}

