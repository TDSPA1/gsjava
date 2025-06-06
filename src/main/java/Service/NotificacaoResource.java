package Service;

import Dto.NotificacaoRequestDto;
import Dto.NotificacaoResponseDto;
import Entidade.Notificacao;
import Entidade.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/notificacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificacaoResource {

    @Inject
    NotificacaoService notificacaoService;
    @Inject
    UsuarioService usuarioService;

    @POST
    public Response criar(NotificacaoRequestDto dto) {
        Usuario usuario = usuarioService.buscarPorId(dto.usuarioId);
        if (usuario == null) return Response.status(400).entity("Usuário não encontrado").build();

        Notificacao n = new Notificacao();
        n.setMensagem(dto.mensagem);
        n.setData(dto.data);
        n.setUsuario(usuario);

        notificacaoService.salvar(n);
        return Response.status(201).build();
    }

    @GET
    public List<NotificacaoResponseDto> listarTodos() {
        return notificacaoService.listarTodos().stream().map(n -> {
            NotificacaoResponseDto dto = new NotificacaoResponseDto();
            dto.id = n.getId();
            dto.mensagem = n.getMensagem();
            dto.data = n.getData();
            dto.usuarioId = n.getUsuario().getId();
            return dto;
        }).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        if (notificacaoService.deletar(id)) return Response.noContent().build();
        return Response.status(404).build();
    }
}