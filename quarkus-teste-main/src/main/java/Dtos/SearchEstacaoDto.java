package Dtos;

import Entidades.Estacao;

import java.util.List;

public record SearchEstacaoDto(int page, int pageSize, int totalItems, List<Estacao> estacoes,String direction){

}

