package Dtos;

import Entidades.Status;

import java.time.LocalDateTime;

public record Incidente( int id,LocalDateTime dataIncidente,Status status,String transporte) {
}
