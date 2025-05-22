package Dtos;

import Entidades.TipoManutencao;

public record ManutencaoDto( int id,TipoManutencao tipo,String tecnicoResponsavel,String descricao,String localizacao) {
}
