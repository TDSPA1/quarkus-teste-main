package Services;


import Dtos.SearchIncidenteDto;
import Dtos.SearchPassagemDto;
import Entidades.Incidente;
import Entidades.Linha;
import Entidades.Status;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import oracle.jdbc.proxy.annotation.Post;
import org.jboss.resteasy.reactive.RestResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("incidente")
public class IncidenteResource {
    public static final int PAGE_SIZE = 5;
    private final IncidenteService incidenteService = new IncidenteService();
    static List<Incidente> incidentes = new ArrayList<>(List.of(
            new Incidente(1, LocalDateTime.now(), Status.RESOLVIDO, "metro"),
            new Incidente(2, LocalDateTime.now(), Status.EM_ANDAMENTO, "metro"),
            new Incidente(3, LocalDateTime.now(), Status.ABERTO, "metro")
    ));
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchIncidenteDto searchIncidentes(
            @QueryParam("page") int page,
            @QueryParam("nome") Optional<String> nome,
            @QueryParam("texto")Optional<String>texto,
            @QueryParam("direction")Optional<String>direction) {

        var filteredIncidentes = incidentes.stream()
                .filter(i -> i.getTransporte().contains(nome.orElse("")))  // Filtra pelo nome
                .filter(i -> i.getDataIncidente().toString().contains(texto.orElse("")))
                .sorted((i1, i2) -> {
                    if (direction.orElse("asc").equals("desc")) {
                        return i2.getTransporte().compareToIgnoreCase(i1.getTransporte());  // Ordenação decrescente
                    } else {
                        return i1.getTransporte().compareToIgnoreCase(i2.getTransporte());  // Ordenação crescente
                    }
                })
                .toList();
        if (filteredIncidentes.isEmpty()) {
            return new SearchIncidenteDto(page, PAGE_SIZE, 0, List.of(), direction.orElse("asc"));
        }

        var start = page > 1 ? (page - 1) * PAGE_SIZE : 0;
        var end = Math.min(start + PAGE_SIZE, filteredIncidentes.size());

        if (start >= filteredIncidentes.size()) {
            return new SearchIncidenteDto(page, PAGE_SIZE, filteredIncidentes.size(), List.of(), direction.orElse("asc"));
        }
        return new SearchIncidenteDto(page, PAGE_SIZE, filteredIncidentes.size(),
                filteredIncidentes.subList(start, end), direction.orElse("asc"));
        }
        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getIncidente(@PathParam("id")int id){
            var incidente = incidentes.stream().filter(i -> i.getId()==id)
                    .findFirst().orElse(null);

            return incidente == null ? Response.status(Response.Status.NOT_FOUND)
                    .build() : Response.ok(incidente).build();
        }
        @Post
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addIncidente(Incidente incidente){
            if (!incidenteService.validateIncidente(incidente))
                return Response.status(Response.Status.BAD_REQUEST).build();

            incidentes.add(incidente);
            return Response.status(Response.Status.CREATED)
                    .entity(incidente)
                    .build();
        }
        @PUT//atualizar
        @Path("{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateIncidente(@PathParam("id")int id, Incidente incidente){
            var incidenteToUpdate = incidentes.stream().filter(i ->i.getId() == id)
                    .findFirst().orElse(null);

            if(incidenteToUpdate == null)
                return Response.status(RestResponse.Status.NOT_FOUND).build();

            incidentes.stream().filter(i -> i.getId()== id)
                    .forEach(i -> {
                        i.setDataIncidente(incidente.getDataIncidente());
                        i.setStatus(incidente.getStatus());
                        i.setTransporte(incidente.getTransporte());
                    });
            return Response.noContent().build();

        }
        @DELETE //deletar
        @Path("/{id}")
        public Response deleteIncidente(@PathParam("id") int id){
            var incidente = incidentes.stream().filter(l -> l.getId()==id)
                    .findFirst().orElse(null);
            if(incidente == null)
                return Response.status(RestResponse.Status.NOT_FOUND).build();
            incidentes.removeIf(l -> l.getId()==id);
            return Response.noContent().build();
        }


}
