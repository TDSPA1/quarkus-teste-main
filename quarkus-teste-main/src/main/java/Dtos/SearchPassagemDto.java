package Dtos;

import Entidades.Passagem;

import java.util.List;

public record SearchPassagemDto(int page, int pageSize, int totalItems, List<Passagem> passagens, String direction){


}
