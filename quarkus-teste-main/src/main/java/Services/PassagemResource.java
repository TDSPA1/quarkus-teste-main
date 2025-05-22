package Services;


import Dtos.SearchPassagemDto;
import Dtos.SearchUsuarioDto;
import Entidades.Passagem;
import Entidades.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/passagem")
public class PassagemResource {
    public static final int PAGE_SIZE = 5;
    private final PassagemService passagemService = new PassagemService();
    static List<Passagem>passagens = new ArrayList<>(List.of(
            new Passagem(1,"Lucas",5.20f,"pix", LocalTime.now()),
            new Passagem(2,"Antonio",5.20f,"dinheiro", LocalTime.now()),
            new Passagem(3,"Maria",2.60f,"pix", LocalTime.now())
    ));

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchPassagemDto searchPassagens(
            @QueryParam("page") int page,
            @QueryParam("nome") Optional<String> nome,
            @QueryParam("texto")Optional<String>texto,
            @QueryParam("direction")Optional<String>direction) {

        var filteredPassagens = passagens.stream()
                .filter(e -> e.getNome().contains(nome.orElse("")))  // Filtra pelo nome
                .filter(e -> e.getFormaDePagamento().contains(texto.orElse("")))
                .sorted((p1, p2) -> {
                    if (direction.orElse("asc").equals("desc")) {
                        return p2.getNome().compareToIgnoreCase(p1.getNome());  // Ordenação decrescente
                    } else {
                        return p1.getNome().compareToIgnoreCase(p2.getNome());  // Ordenação crescente
                    }
                })
                .toList();
        if (filteredPassagens.isEmpty()) {
            return new SearchPassagemDto(page, PAGE_SIZE, 0, List.of(), direction.orElse("asc"));
        }

        var start = page > 1 ? (page - 1) * PAGE_SIZE : 0;
        var end = Math.min(start + PAGE_SIZE, filteredPassagens.size());

        if (start >= filteredPassagens.size()) {
            return new SearchPassagemDto(page, PAGE_SIZE, filteredPassagens.size(), List.of(), direction.orElse("asc"));
        }
        return new SearchPassagemDto(page, PAGE_SIZE, filteredPassagens.size(),
                filteredPassagens.subList(start, end), direction.orElse("asc"));
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPassagem(@PathParam("id")int id){
        var passagem = passagens.stream().filter(p -> p.getId()==id)
                .findFirst().orElse(null);

        return passagem == null ? Response.status(Response.Status.NOT_FOUND)
                .build() : Response.ok(passagem).build();
    }
    @PUT//atualizar
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassagem(@PathParam("id")int id, Passagem passagem) {
        var passagemToUpdate = passagens.stream().filter(u -> u.getId() == id)
                .findFirst().orElse(null);

        if (passagemToUpdate == null)
            return Response.status(RestResponse.Status.NOT_FOUND).build();

        passagens.stream().filter(p -> p.getId() == id)
                .forEach(u -> {
                    u.setNome(passagem.getNome());
                    u.setValor(passagem.getValor());
                    u.setFormaDePagamento(passagem.getFormaDePagamento());
                    u.setHorarioDePagamento(passagem.getHorarioDePagamento());
                });
        return Response.noContent().build();

    }
    @DELETE //deletar
    @Path("/{id}")
    public Response deletePassagem(@PathParam("id") int id){
        var passagem = passagens.stream().filter(p -> p.getId()==id)
                .findFirst().orElse(null);
        if(passagem == null)
            return Response.status(RestResponse.Status.NOT_FOUND).build();
        passagens.removeIf(u -> u.getId()==id);
        return Response.noContent().build();
    }
}