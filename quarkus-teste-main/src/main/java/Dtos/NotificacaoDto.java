package Dtos;

public record NotificacaoDto(int id, UsuarioDto usuario, String mensagem, String dataEnvio) {
}
