package Service;

import Entidade.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();
    private int idSequence = 1;

    public List<Usuario> listarTodos() {
        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);


    }

    public Usuario salvar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }

        usuario.setId(idSequence++);
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario atualizar(int id, Usuario novosDados) {
        if (novosDados == null) {
            throw new IllegalArgumentException("Os dados do usuário não podem ser nulos.");
        }

        Usuario existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(novosDados.getNome());
            existente.setEmail(novosDados.getEmail());
            existente.setTelefone(novosDados.getTelefone());
            existente.setSenha(novosDados.getSenha());
        }
        return existente;
    }

    public boolean deletar(int id) {
        return usuarios.removeIf(u -> u.getId() == id);
    }

}
