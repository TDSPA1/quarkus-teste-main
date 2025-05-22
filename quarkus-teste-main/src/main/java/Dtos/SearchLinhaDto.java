package Dtos;

import Entidades.Linha;

import java.util.List;

public record SearchLinhaDto(int page, int pageSize, int totalItems, List<Linha> linhas,String direction) {// direction ->asc ou desc
}
