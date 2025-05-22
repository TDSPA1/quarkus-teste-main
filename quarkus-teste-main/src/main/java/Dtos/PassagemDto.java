package Dtos;

import java.time.LocalTime;

public record PassagemDto(int id, String nome,float valor, String formaDePagamento,LocalTime horarioDePagamento) {
}
