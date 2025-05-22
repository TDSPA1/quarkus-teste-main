package Dtos;

import Entidades.Usuario;

import java.util.List;

public record SearchUsuarioDto(int page, int pageSize, int totalItems, List<Usuario> usuarios, String direction) {
}
