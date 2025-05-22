package Dtos;

import Entidades.Incidente;

import java.util.List;

public record SearchIncidenteDto(int page, int pageSize, int totalItems, List<Incidente>incidentes, String direction) {
}
