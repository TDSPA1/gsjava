package Service;


import Dto.AlarmeRequestDto;
import Dto.AlarmeResponseDto;
import Entidade.Alarme;
import Entidade.Sensor;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/alarmes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlarmeResource {

    @Inject
    AlarmeService alarmeService;

    @Inject
    SensorService sensorService;

    @POST
    public Response criar(AlarmeRequestDto dto) {
        Sensor sensor = sensorService.buscarPorId(dto.sensorId);
        if (sensor == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Sensor não encontrado").build();
        }

        Alarme alarme = new Alarme();
        alarme.setTipo(dto.tipo);
        alarme.setLocalizacao(dto.localizacao);
        alarme.setAtivo(dto.ativo);
        alarme.setData(dto.data);
        alarmeService.salvar(alarme);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<AlarmeResponseDto> listarTodos() {
        return alarmeService.listarTodos().stream()
                .map(a -> {
                    AlarmeResponseDto dto = new AlarmeResponseDto();
                    dto.id = a.getId();
                    dto.tipo = a.getTipo();
                    dto.localizacao = a.getLocalizacao();
                    dto.ativo = a.isAtivo();
                    dto.data = a.getData();
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        Alarme a = alarmeService.buscarPorId(id);
        if (a == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        AlarmeResponseDto dto = new AlarmeResponseDto();
        dto.id = a.getId();
        dto.tipo = a.getTipo();
        dto.localizacao = a.getLocalizacao();
        dto.ativo = a.isAtivo();
        dto.data = a.getData();
        return Response.ok(dto).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, AlarmeRequestDto dto) {
        Sensor sensor = sensorService.buscarPorId(dto.sensorId);
        if (sensor == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Sensor não encontrado").build();
        }

        Alarme dados = new Alarme();
        dados.setTipo(dto.tipo);
        dados.setLocalizacao(dto.localizacao);
        dados.setAtivo(dto.ativo);
        dados.setData(dto.data);
        Alarme atualizado = alarmeService.atualizar(id, dados);
        if (atualizado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        boolean removido = alarmeService.deletar(id);
        if (!removido) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
