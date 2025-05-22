package Entidades;

import java.time.LocalTime;
import java.util.Objects;

public class TransporteFerroviario {
    private int id;
    private String nome;
    private int capacidadeMax;
    private int capacidadeMin;
    private String linhaDaRota;
    private LocalTime horarioSaida;
    private LocalTime horarioChegada;

    public TransporteFerroviario() {
    }

    public TransporteFerroviario(int id, String nome, int capacidadeMax, int capacidadeMin, String linhaDaRota, LocalTime horarioSaida, LocalTime horarioChegada) {
        this.id = id;
        this.nome = nome;
        this.capacidadeMax = capacidadeMax;
        this.capacidadeMin = capacidadeMin;
        this.linhaDaRota = linhaDaRota;
        this.horarioSaida = horarioSaida;
        this.horarioChegada = horarioChegada;
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

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public void setCapacidadeMax(int capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }

    public int getCapacidadeMin() {
        return capacidadeMin;
    }

    public void setCapacidadeMin(int capacidadeMin) {
        this.capacidadeMin = capacidadeMin;
    }

    public String getLinhaDaRota() {
        return linhaDaRota;
    }

    public void setLinhaDaRota(String linhaDaRota) {
        this.linhaDaRota = linhaDaRota;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public LocalTime getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(LocalTime horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof TransporteFerroviario that)) return false;
        return getId() == that.getId() && getCapacidadeMax() == that.getCapacidadeMax() && getCapacidadeMin() == that.getCapacidadeMin() && Objects.equals(getNome(), that.getNome()) && Objects.equals(getLinhaDaRota(), that.getLinhaDaRota()) && Objects.equals(getHorarioSaida(), that.getHorarioSaida()) && Objects.equals(getHorarioChegada(), that.getHorarioChegada());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCapacidadeMax(), getCapacidadeMin(), getLinhaDaRota(), getHorarioSaida(), getHorarioChegada());
    }

    @Override
    public String toString() {
        return "TransporteFerroviario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capacidadeMax=" + capacidadeMax +
                ", capacidadeMin=" + capacidadeMin +
                ", linhaDaRota='" + linhaDaRota + '\'' +
                ", horarioSaida=" + horarioSaida +
                ", horarioChegada=" + horarioChegada +
                '}';
    }
}
