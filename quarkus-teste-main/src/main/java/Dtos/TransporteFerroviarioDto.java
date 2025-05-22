package Dtos;

import java.time.LocalTime;

public record TransporteFerroviarioDto(int id, String nome, int capacidadeMax, int capacidadeMin, String linhaDaRota,
                                       LocalTime horarioSaida,LocalTime horarioChegada) {
}
