package Dtos;

import Entidades.Estacao;
import Entidades.TransporteFerroviario;

import java.util.List;

public record SearchTransporteFerroviarioDto(int page, int pageSize, int totalItems, List<TransporteFerroviario> transportesFerroviarios, String direction) {
}
