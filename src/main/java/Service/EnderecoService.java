package Service;

import Entidade.Endereco;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EnderecoService {
    private List<Endereco> enderecos = new ArrayList<>();
    private int idSequence = 1;

    public Endereco salvar(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo.");
        }

        endereco.setId(idSequence++);
        enderecos.add(endereco);
        return endereco;
    }

    public List<Endereco> listarTodos() {
        return new ArrayList<>(enderecos);  // Retorna uma cópia da lista
    }

    public Endereco buscarPorId(int id) {
        return enderecos.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public Endereco atualizar(int id, Endereco dados) {
        if (dados == null) {
            throw new IllegalArgumentException("Os dados do endereço não podem ser nulos.");
        }

        Endereco existente = buscarPorId(id);
        if (existente != null) {
            // Atualizar os campos
            existente.setRua(dados.getRua());
            existente.setBairro(dados.getBairro());
            existente.setCidade(dados.getCidade());
            existente.setEstado(dados.getEstado());
            existente.setCep(dados.getCep());
            existente.setUsuario(dados.getUsuario());
        }
        return existente;
    }


    public boolean deletar(int id) {
        return enderecos.removeIf(e -> e.getId() == id);
    }
}
