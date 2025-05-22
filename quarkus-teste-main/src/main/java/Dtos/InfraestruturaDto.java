package Dtos;

import java.time.LocalDateTime;

public record InfraestruturaDto( int id,String localizacao,String estadoDeConservacao,LocalDateTime dataDeManutencao,String linha) {
}
