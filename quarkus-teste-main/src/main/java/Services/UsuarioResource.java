package Services;

import Dtos.SearchLinhaDto;
import Dtos.SearchUsuarioDto;
import Entidades.Linha;
import Entidades.TransporteFerroviario;
import Entidades.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Path("/usuarios")
public class UsuarioResource {
    public static final int PAGE_SIZE = 5;
    private final UsuarioService usuarioService = new UsuarioService();
    static List<Usuario> usuarios = new ArrayList<>(List.of(
            new Usuario(1,"Guilherme","123.456.789-09","Masculino",79),
            new Usuario(2,"Marcos","987.654.321-00","Masculino",38),
            new Usuario(3,"Arthur","111.444.777-35","Masculino",10)
    ));

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchUsuarioDto searchUsuarios(
            @QueryParam("page") int page,
            @QueryParam("nome") Optional<String> nome,
            @QueryParam("texto")Optional<String>texto,
            @QueryParam("direction")Optional<String>direction) {

        var filteredUsuarios = usuarios.stream()
                .filter(e -> e.getNome().contains(nome.orElse("")))  // Filtra pelo nome
                .filter(e -> e.getCpf().contains(texto.orElse("")))  // Filtra pela cor
                .sorted((l1, l2) -> {
                    if (direction.orElse("asc").equals("desc")) {
                        return l2.getNome().compareToIgnoreCase(l1.getNome());  // Ordenação decrescente
                    } else {
                        return l1.getNome().compareToIgnoreCase(l2.getNome());  // Ordenação crescente
                    }
                })
                .toList();
        if (filteredUsuarios.isEmpty()) {
            return new SearchUsuarioDto(page, PAGE_SIZE, 0, List.of(), direction.orElse("asc"));
        }

        var start = page > 1 ? (page - 1) * PAGE_SIZE : 0;
        var end = Math.min(start + PAGE_SIZE, filteredUsuarios.size());

        if (start >= filteredUsuarios.size()) {
            return new SearchUsuarioDto(page, PAGE_SIZE, filteredUsuarios.size(), List.of(), direction.orElse("asc"));
        }
        return new SearchUsuarioDto(page, PAGE_SIZE, filteredUsuarios.size(),
                filteredUsuarios.subList(start, end), direction.orElse("asc"));
        }
        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getUsuario(@PathParam("id")int id){
            var usuario = usuarios.stream().filter(u -> u.getId()==id)
                    .findFirst().orElse(null);

            return usuario == null ? Response.status(Response.Status.NOT_FOUND)
                    .build() : Response.ok(usuario).build();
        }
    @PUT//atualizar
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id")int id, Usuario usuario) {
        var usuarioToUpdate = usuarios.stream().filter(u -> u.getId() == id)
                .findFirst().orElse(null);

        if (usuarioToUpdate == null)
            return Response.status(RestResponse.Status.NOT_FOUND).build();

        usuarios.stream().filter(u -> u.getId() == id)
                .forEach(u -> {
                    u.setNome(usuario.getNome());
                    u.setCpf(usuario.getCpf());
                });
        return Response.noContent().build();
    }
    @DELETE //deletar
    @Path("/{id}")
    public Response deleteLinha(@PathParam("id") int id){
        var usuario = usuarios.stream().filter(u -> u.getId()==id)
                .findFirst().orElse(null);
        if(usuario == null)
            return Response.status(RestResponse.Status.NOT_FOUND).build();
        usuarios.removeIf(u -> u.getId()==id);
        return Response.noContent().build();
    }

}

