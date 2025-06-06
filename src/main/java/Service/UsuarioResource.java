package Service;


import Dto.UsuarioRequestDto;
import Dto.UsuarioResponseDto;
import Entidade.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    @Inject
    UsuarioService usuarioService;

    @POST
    public Response criar(UsuarioRequestDto dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome);
        usuario.setEmail(dto.email);
        usuario.setSenha(dto.senha);
        usuario.setTelefone(dto.telefone);
        usuarioService.salvar(usuario);
        return Response.status(Response.Status.CREATED).build();

    }
    @GET
    public List<UsuarioResponseDto> listarTodos(){
        return usuarioService.listarTodos().stream()
                .map(u->{
                    UsuarioResponseDto dto = new UsuarioResponseDto();
                    dto.id = u.getId();
                    dto.nome = u.getNome();
                    dto.email = u.getEmail();
                    dto.telefone = u.getTelefone();
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id")int id, UsuarioRequestDto dto){
        Usuario dados = new Usuario();
        dados.setNome(dto.nome);
        dados.setEmail(dto.email);
        dados.setSenha(dto.senha);
        dados.setTelefone(dto.telefone);
        Usuario atualizado = usuarioService.atualizar(id,dados);
        if(atualizado == null){
            return Response.status(Response.Status.NOT_FOUND).build();

        }
        return Response.ok().build();
    }
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id")int id){
        boolean removido = usuarioService.deletar(id);
        if(!removido){
            return Response.status(Response.Status.NOT_FOUND).build();

        }
        return Response.noContent().build();
    }
}
