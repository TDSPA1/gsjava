package Service;

import Dto.SensorRequestDto;
import Dto.SensorResponseDto;
import Entidade.Sensor;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/sensores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    @Inject
    SensorService sensorService;

    @POST
    public Response criar(SensorRequestDto dto) {
        if (dto.tipo == null || dto.tipo.isEmpty()) {
            return Response.status(400).entity("Tipo de sensor é obrigatório").build();
        }
        if (dto.localizacao == null || dto.localizacao.isEmpty()) {
            return Response.status(400).entity("Localização de sensor é obrigatória").build();
        }
        Sensor sensor = new Sensor();
        sensor.setTipo(dto.tipo);
        sensor.setLocalizacao(dto.localizacao);
        sensor.setAtivo(dto.ativo);
        sensorService.salvar(sensor);
        return Response.status(201).build();
    }

    @GET
    public List<SensorResponseDto> listarTodos() {
        return sensorService.listarTodos().stream().map(s -> {
            SensorResponseDto dto = new SensorResponseDto();
            dto.id = s.getId();
            dto.tipo = s.getTipo();
            dto.localizacao = s.getLocalizacao();
            dto.ativo = s.isAtivo();
            return dto;
        }).collect(Collectors.toList());
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, SensorRequestDto dto) {
        Sensor dados = new Sensor();
        dados.setTipo(dto.tipo);
        dados.setLocalizacao(dto.localizacao);
        dados.setAtivo(dto.ativo);
        Sensor atualizado = sensorService.atualizar(id, dados);
        if (atualizado == null) return Response.status(404).build();
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        if (sensorService.deletar(id)) return Response.noContent().build();
        return Response.status(404).build();
    }
}