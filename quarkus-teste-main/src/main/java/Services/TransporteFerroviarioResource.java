package Services;

import Dtos.SearchLinhaDto;
import Dtos.SearchTransporteFerroviarioDto;
import Entidades.Linha;
import Entidades.TransporteFerroviario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import oracle.jdbc.proxy.annotation.Post;
import org.jboss.resteasy.reactive.RestResponse;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Path("/transportes")
public class TransporteFerroviarioResource {
    public static final int PAGE_SIZE = 5;
    private final TransporteFerroviarioService transporteFerroviarioService = new TransporteFerroviarioService();
    static List<TransporteFerroviario> transportes = new ArrayList<>(List.of(
            new TransporteFerroviario(1,"metro",500,1,"lilas",LocalTime.now(),LocalTime.now()),
            new TransporteFerroviario(2,"trem",300,1,"verde", LocalTime.now(),LocalTime.now()),
            new TransporteFerroviario(3,"metro",500,1,"amarela",LocalTime.now(), LocalTime.now())
    ));

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchTransporteFerroviarioDto searchTransportes(
            @QueryParam("page") int page,
            @QueryParam("nome") Optional<String> nome,
            @QueryParam("texto")Optional<String>texto,
            @QueryParam("direction")Optional<String>direction) {

        var filteredTransportes = transportes.stream()
                .filter(t -> t.getNome().contains(nome.orElse("")))  // Filtra pelo nome
                .filter(t -> t.getLinhaDaRota().contains(texto.orElse("")))  // Filtra pela cor
                .sorted((t1, t2) -> {
                    if (direction.orElse("asc").equals("desc")) {
                        return t2.getNome().compareToIgnoreCase(t1.getNome());  // Ordenação decrescente
                    } else {
                        return t1.getNome().compareToIgnoreCase(t2.getNome());  // Ordenação crescente
                    }
                })
                .toList();
        if (filteredTransportes.isEmpty()) {
            return new SearchTransporteFerroviarioDto(page, PAGE_SIZE, 0, List.of(), direction.orElse("asc"));
        }

        var start = page > 1 ? (page - 1) * PAGE_SIZE : 0;
        var end = Math.min(start + PAGE_SIZE, filteredTransportes.size());

        if (start >= filteredTransportes.size()){
            return new SearchTransporteFerroviarioDto(page, PAGE_SIZE, filteredTransportes.size(), List.of(), direction.orElse("asc"));
        }
        return new SearchTransporteFerroviarioDto(page, PAGE_SIZE, filteredTransportes.size(),
                filteredTransportes.subList(start, end), direction.orElse("asc"));
        }
        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)

        public Response getTransporte(@PathParam("id")int id){
            var transporte = transportes.stream().filter(t -> t.getId()==id)
                    .findFirst().orElse(null);

            return transporte == null ? Response.status(Response.Status.NOT_FOUND)
                    .build() : Response.ok(transporte).build();
        }

        @Post
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addTransporte(TransporteFerroviario transporte){
            if (!transporteFerroviarioService.validateTransporteFerroviario(transporte))
                return Response.status(Response.Status.BAD_REQUEST).build();

            transportes.add(transporte);
            return Response.status(Response.Status.CREATED)
                    .entity(transporte)
                    .build();
        }
    @PUT//atualizar
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTransporte(@PathParam("id")int id, TransporteFerroviario transporte){
        var transporteToUpdate = transportes.stream().filter(t ->t.getId() == id)
                .findFirst().orElse(null);

        if(transporteToUpdate == null)
            return Response.status(RestResponse.Status.NOT_FOUND).build();

        transportes.stream().filter(t -> t.getId()== id)
                .forEach(t -> {
                    t.setNome(transporte.getNome());
                    t.setCapacidadeMax(transporte.getCapacidadeMax());
                    t.setCapacidadeMin(transporte.getCapacidadeMin());
                    t.setLinhaDaRota(transporte.getLinhaDaRota());
                    t.setHorarioChegada(transporte.getHorarioChegada());
                    t.setHorarioSaida(transporte.getHorarioSaida());
                });
        return Response.noContent().build();

    }
    @DELETE //deletar
    @Path("/{id}")
    public Response deleteTransporte(@PathParam("id") int id){
        var transporte = transportes.stream().filter(t -> t.getId()==id)
                .findFirst().orElse(null);
        if(transporte == null)
            return Response.status(RestResponse.Status.NOT_FOUND).build();
        transportes.removeIf(t -> t.getId()==id);
        return Response.noContent().build();
    }
}

