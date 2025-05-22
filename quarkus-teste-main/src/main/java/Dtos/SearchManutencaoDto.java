package Dtos;

import Entidades.Estacao;
import Entidades.Manutencao;

import java.util.List;

public record SearchManutencaoDto(int page, int pageSize, int totalItems, List<Manutencao> manutencoes, String direction) {
}
