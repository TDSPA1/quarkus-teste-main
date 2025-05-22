package Entidades;

import java.time.LocalDateTime;
import java.util.Objects;

public class Incidente {
    private int id;
    private LocalDateTime dataIncidente;
    private Status status;
    private String transporte;

    public Incidente() {
    }

    public Incidente(int id, LocalDateTime dataIncidente, Status status, String transporte) {
        this.id = id;
        this.dataIncidente = dataIncidente;
        this.status = status;
        this.transporte = transporte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataIncidente() {
        return dataIncidente;
    }

    public void setDataIncidente(LocalDateTime dataIncidente) {
        this.dataIncidente = dataIncidente;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Incidente incidente)) return false;
        return getId() == incidente.getId() && Objects.equals(getDataIncidente(), incidente.getDataIncidente()) && getStatus() == incidente.getStatus() && Objects.equals(getTransporte(), incidente.getTransporte());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataIncidente(), getStatus(), getTransporte());
    }

    @Override
    public String toString() {
        return "Incidente{" +
                "id=" + id +
                ", dataIncidente=" + dataIncidente +
                ", status=" + status +
                ", transporte='" + transporte + '\'' +
                '}';
    }
}
