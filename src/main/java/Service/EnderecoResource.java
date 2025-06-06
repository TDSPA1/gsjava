package Service;

import Dto.EnderecoRequestDto;
import Dto.EnderecoResponseDto;
import Entidade.Endereco;
import Entidade.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject EnderecoService enderecoService;
    @Inject
    UsuarioService usuarioService;

    @POST
    public Response criar(EnderecoRequestDto dto) {
        Usuario usuario = usuarioService.buscarPorId(dto.usuarioId);
        if (usuario == null) return Response.status(400).entity("Usuário não encontrado").build();

        Endereco endereco = new Endereco();
        endereco.setRua(dto.rua);
        endereco.setBairro(dto.bairro);
        endereco.setCidade(dto.cidade);
        endereco.setEstado(dto.estado);
        endereco.setCep(dto.cep);
        endereco.setUsuario(usuario);

        enderecoService.salvar(endereco);
        return Response.status(201).build();
    }

    @GET
    public List<EnderecoResponseDto> listarTodos() {
        return enderecoService.listarTodos().stream().map(e -> {
            EnderecoResponseDto dto = new EnderecoResponseDto();
            dto.id = e.getId();
            dto.rua = e.getRua();
            dto.bairro = e.getBairro();
            dto.cidade = e.getCidade();
            dto.estado = e.getEstado();
            dto.cep = e.getCep();
            dto.usuarioID = e.getUsuario().getId();
            return dto;
        }).collect(Collectors.toList());
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, EnderecoRequestDto dto) {
        Usuario usuario = usuarioService.buscarPorId(dto.usuarioId);
        if (usuario == null) return Response.status(400).entity("Usuário não encontrado").build();

        Endereco dados = new Endereco();
        dados.setRua(dto.rua);
        dados.setBairro(dto.bairro);
        dados.setCidade(dto.cidade);
        dados.setEstado(dto.estado);
        dados.setCep(dto.cep);
        dados.setUsuario(usuario);

        Endereco atualizado = enderecoService.atualizar(id, dados);
        if (atualizado == null) return Response.status(404).build();
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        if (enderecoService.deletar(id)) return Response.noContent().build();
        return Response.status(404).build();
    }
}
