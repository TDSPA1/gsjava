package Service;

import Entidade.Ocorrencia;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class OcorrenciaService {


    private List<Ocorrencia> ocorrencias = new ArrayList<>();
    private int idSequence = 1;

    public Ocorrencia salvar(Ocorrencia ocorrencia) {
        if (ocorrencia == null) {
            throw new IllegalArgumentException("Ocorrência não pode ser nula.");
        }


        ocorrencia.setId(idSequence++);
        ocorrencias.add(ocorrencia);
        return ocorrencia;
    }


    public List<Ocorrencia> listarTodos() {
        return new ArrayList<>(ocorrencias);
    }


    public Ocorrencia buscarPorId(int id) {
        return ocorrencias.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public Ocorrencia atualizar(int id, Ocorrencia dados) {
        if (dados == null) {
            throw new IllegalArgumentException("Os dados da ocorrência não podem ser nulos.");
        }

        Ocorrencia existente = buscarPorId(id);
        if (existente != null) {

            existente.setDescricao(dados.getDescricao());
            existente.setData(dados.getData());
            existente.setAlarme(dados.getAlarme());
        }
        return existente;
    }


    public boolean deletar(int id) {
        return ocorrencias.removeIf(o -> o.getId() == id);
    }
}