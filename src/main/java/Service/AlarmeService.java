package Service;

import Entidade.Alarme;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AlarmeService {

    private List<Alarme> alarmes = new ArrayList<>();
    private int idSequence = 1;


    public Alarme salvar(Alarme alarme) {
        if (alarme == null) {
            throw new IllegalArgumentException("Alarme não pode ser nulo.");
        }

        alarme.setId(idSequence++);
        alarmes.add(alarme);
        return alarme;
    }


    public List<Alarme> listarTodos() {
        return new ArrayList<>(alarmes);
    }


    public Alarme buscarPorId(int id) {
        return alarmes.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public Alarme atualizar(int id, Alarme dados) {
        if (dados == null) {
            throw new IllegalArgumentException("Os dados do alarme não podem ser nulos.");
        }

        Alarme existente = buscarPorId(id);
        if (existente != null) {

            existente.setTipo(dados.getTipo());
            existente.setLocalizacao(dados.getLocalizacao());
            existente.setAtivo(dados.isAtivo());
            existente.setData(dados.getData());
            existente.setSensor(dados.getSensor());
        }
        return existente;
    }


    public boolean deletar(int id) {
        return alarmes.removeIf(a -> a.getId()== id);
    }
}
