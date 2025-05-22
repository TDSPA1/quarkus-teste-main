package Services;

import Dtos.SearchLinhaDto;
import Entidades.CorLinha;
import Entidades.Linha;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import oracle.jdbc.proxy.annotation.Post;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Path("/linhas") //caminho para http
public class LinhaResource {


        public static final int PAGE_SIZE = 5; //ira aparecer 5 linhas numa mesma pagina
        private final LinhaService linhaService = new LinhaService();

        static List<Linha> linhas = new ArrayList<>(List.of(
                new Linha(1,"Lilas",CorLinha.LILAS),
                new Linha(2,"Verde",CorLinha.VERDE),
                new Linha(3,"Amarela",CorLinha.AMARELA)
        ));

        @GET
        @Path("/search")
        @Produces(MediaType.APPLICATION_JSON)
        public SearchLinhaDto searchLinhas(
                @QueryParam("page") int page,
                @QueryParam("nome")Optional<String>nome,
                @QueryParam("texto")Optional<String>texto,
                @QueryParam("direction")Optional<String>direction) {

                var filteredLinhas = linhas.stream()
                        .filter(e -> e.getNome().contains(nome.orElse("")))  // Filtra pelo nome
                        .filter(e -> e.getCor().name().contains(texto.orElse("")))  // Filtra pela cor
                        .sorted((l1, l2) -> {
                                if (direction.orElse("asc").equals("desc")) {
                                        return l2.getNome().compareToIgnoreCase(l1.getNome());  // Ordenação decrescente
                                } else {
                                        return l1.getNome().compareToIgnoreCase(l2.getNome());  // Ordenação crescente
                                }
                        })
                        .toList();
                if (filteredLinhas.isEmpty()) {
                        return new SearchLinhaDto(page, PAGE_SIZE, 0, List.of(), direction.orElse("asc"));
                }

                var start = page > 1 ? (page - 1) * PAGE_SIZE : 0;
                var end = Math.min(start + PAGE_SIZE, filteredLinhas.size());

                if (start >= filteredLinhas.size()){
                        return new SearchLinhaDto(page, PAGE_SIZE, filteredLinhas.size(), List.of(), direction.orElse("asc"));
                }
                return new SearchLinhaDto(page, PAGE_SIZE, filteredLinhas.size(),
                        filteredLinhas.subList(start, end), direction.orElse("asc"));

        }
        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)

        public Response getLinha(@PathParam("id")int id){
                var linha = linhas.stream().filter(l -> l.getId()==id)
                        .findFirst().orElse(null);

                return linha == null ? Response.status(Response.Status.NOT_FOUND)
                        .build() : Response.ok(linha).build();
        }

        @Post
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addLinha(Linha linha){
                if (!linhaService.validateLinha(linha))
                        return Response.status(Response.Status.BAD_REQUEST).build();

                linhas.add(linha);
                return Response.status(Response.Status.CREATED)
                        .entity(linha)
                        .build();
        }

        @PUT//atualizar
        @Path("{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateLinha(@PathParam("id")int id, Linha linha){
                var linhaToUpdate = linhas.stream().filter(l ->l.getId() == id)
                        .findFirst().orElse(null);

                if(linhaToUpdate == null)
                        return Response.status(RestResponse.Status.NOT_FOUND).build();

                linhas.stream().filter(l -> l.getId()== id)
                        .forEach(l -> {
                                l.setNome(linha.getNome());
                                l.setCor(linha.getCor());
                        });
                return Response.noContent().build();

        }
        @DELETE //deletar
        @Path("/{id}")
        public Response deleteLinha(@PathParam("id") int id){
                var linha = linhas.stream().filter(l -> l.getId()==id)
                        .findFirst().orElse(null);
                if(linha == null)
                        return Response.status(RestResponse.Status.NOT_FOUND).build();
                linhas.removeIf(l -> l.getId()==id);
                return Response.noContent().build();
        }
}

