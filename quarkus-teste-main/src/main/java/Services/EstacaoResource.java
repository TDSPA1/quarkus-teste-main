package Services;


import Dtos.SearchEstacaoDto;
import Dtos.SearchLinhaDto;
import Entidades.CorLinha;
import Entidades.Estacao;
import Entidades.Linha;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import oracle.jdbc.proxy.annotation.Post;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/estacoes") //caminho para http
public class EstacaoResource {
    static  Linha lilas = new Linha(1,"Lilas",CorLinha.LILAS);
    public static final int PAGE_SIZE = 5;
    private final EstacaoService estacaoService = new EstacaoService();

    static List<Estacao> estacoes = new ArrayList<>(List.of(
            new Estacao(1,"Capao Redondo",lilas),
            new Estacao(2,"Campo limpo",lilas)
    ));

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchEstacaoDto searchEstacao(
            @QueryParam("page")int page,
            @QueryParam("nome")Optional<String> nome,
            @QueryParam("texto")Optional<String> texto,
            @QueryParam("direction")Optional<String> direction) {
        var filteredEstacao = estacoes.stream()
                .filter(e -> e.getNome().contains(nome.orElse("")))
                .sorted(direction.orElse("asc").equals("desc") ?
                        (c1, c2) -> c2.getNome().compareToIgnoreCase(c1.getNome()) :
                        (c1, c2) -> c1.getNome().compareToIgnoreCase(c2.getNome())
                )
                .toList();

        if (filteredEstacao.isEmpty())
            return null;

        var start = page > 1 ? (page - 1) * PAGE_SIZE : 0; // aqui eu calculo o inicio da pagina
        var end = Math.min(start + PAGE_SIZE, filteredEstacao.size());

        if (start >= filteredEstacao.size()){
            return new SearchEstacaoDto(page, PAGE_SIZE, filteredEstacao.size(), List.of(), direction.orElse("asc"));
        }
        return new SearchEstacaoDto(page, PAGE_SIZE, filteredEstacao.size(),
                filteredEstacao.subList(start, end), direction.orElse("asc"));
    }

        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getEstacao ( @PathParam("id") int id){
            var estacao = estacoes.stream().filter(e -> e.getId() == id)
                    .findFirst().orElse(null);
            return estacao == null ? Response.status(Response.Status.NOT_FOUND)
                    .build() : Response.ok(estacao).build();

        }
        @Post
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addEstacao(Estacao estacao){
            if (!estacaoService.validateEstacao(estacao))
                return Response.status(Response.Status.BAD_REQUEST).build();

            estacoes.add(estacao);
            return Response.status(Response.Status.CREATED)
                    .entity(estacao)
                    .build();
        }
    @PUT//atualizar
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEstacao(@PathParam("id")int id, Estacao estacao){
        var estacaoToUpdate = estacoes.stream().filter(e ->e.getId() == id)
                .findFirst().orElse(null);

        if(estacaoToUpdate == null)
            return Response.status(RestResponse.Status.NOT_FOUND).build();

        estacoes.stream().filter(e -> e.getId()== id)
                .forEach(e -> {
                    e.setNome(estacao.getNome());
                    e.setLinha(estacao.getLinha());
                });
        return Response.noContent().build();
    }
    @DELETE //deletar
    @Path("/{id}")
    public Response deleteEstacao(@PathParam("id") int id){
        var estacao = estacoes.stream().filter(e -> e.getId()==id)
                .findFirst().orElse(null);
        if(estacao == null)
            return Response.status(RestResponse.Status.NOT_FOUND).build();
        estacoes.removeIf(e -> e.getId()==id);
        return Response.noContent().build();
    }

}





