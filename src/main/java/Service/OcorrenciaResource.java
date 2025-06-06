package Service;

import Dto.OcorrenciaRequestDto;
import Dto.OcorrenciaResponseDto;
import Entidade.Alarme;
import Entidade.Ocorrencia;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/ocorrencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciaResource {

    @Inject
    OcorrenciaService ocorrenciaService;
    @Inject
    AlarmeService alarmeService;

    @POST
    public Response criar(OcorrenciaRequestDto dto) {
        Alarme alarme = alarmeService.buscarPorId(dto.alarmeId);
        if (alarme == null) return Response.status(400).entity("Alarme não encontrado").build();

        Ocorrencia o = new Ocorrencia();
        o.setDescricao(dto.descricao);
        o.setData(dto.data);
        o.setAlarme(alarme);

        ocorrenciaService.salvar(o);
        return Response.status(201).build();
    }

    @GET
    public List<OcorrenciaResponseDto> listarTodos() {
        return ocorrenciaService.listarTodos().stream().map(o -> {
            OcorrenciaResponseDto dto = new OcorrenciaResponseDto();
            dto.id = o.getId();
            dto.descricao = o.getDescricao();
            dto.data = o.getData();
            dto.alarmeId = o.getAlarme().getId();
            return dto;
        }).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        if (ocorrenciaService.deletar(id)) return Response.noContent().build();
        return Response.status(404).build();
    }
}