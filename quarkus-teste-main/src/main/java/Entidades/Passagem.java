package Entidades;

import java.time.LocalTime;
import java.util.Objects;

public class Passagem {
    private int id;
    private String nome;
    private float valor;
    private String formaDePagamento;
    private LocalTime horarioDePagamento;

    public Passagem() {
    }

    public Passagem(int id, String nome, float valor, String formaDePagamento, LocalTime horarioDePagamento) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.formaDePagamento = formaDePagamento;
        this.horarioDePagamento = horarioDePagamento;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public LocalTime getHorarioDePagamento() {
        return horarioDePagamento;
    }

    public void setHorarioDePagamento(LocalTime horarioDePagamento) {
        this.horarioDePagamento = horarioDePagamento;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Passagem passagem)) return false;
        return getId() == passagem.getId() && Float.compare(getValor(), passagem.getValor()) == 0 && Objects.equals(getNome(), passagem.getNome()) && Objects.equals(getFormaDePagamento(), passagem.getFormaDePagamento()) && Objects.equals(getHorarioDePagamento(), passagem.getHorarioDePagamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getValor(), getFormaDePagamento(), getHorarioDePagamento());
    }

    @Override
    public String toString() {
        return "Passagem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", formaDePagamento='" + formaDePagamento + '\'' +
                ", horarioDePagamento=" + horarioDePagamento +
                '}';
    }
}
