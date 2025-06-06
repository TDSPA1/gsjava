package Service;

import Entidade.Sensor;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SensorService {
    private List<Sensor> sensores = new ArrayList<>();
    private int idSequence = 1;


    public Sensor salvar(Sensor sensor) {
        if (sensor == null) {
            throw new IllegalArgumentException("Sensor não pode ser nulo.");
        }


        sensor.setId(idSequence++);
        sensores.add(sensor);
        return sensor;
    }

    public List<Sensor> listarTodos() {
        return new ArrayList<>(sensores);
    }


    public Sensor buscarPorId(int id) {
        return sensores.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public Sensor atualizar(int id, Sensor dados) {
        if (dados == null) {
            throw new IllegalArgumentException("Os dados do sensor não podem ser nulos.");
        }

        Sensor existente = buscarPorId(id);
        if (existente != null) {

            existente.setId(dados.getId());
            existente.setLocalizacao(dados.getLocalizacao());
            existente.setTipo(dados.getTipo());
            existente.setAtivo(dados.isAtivo());
        }
        return existente;
    }


    public boolean deletar(int id) {
        return sensores.removeIf(s -> s.getId() == id);
    }
}

