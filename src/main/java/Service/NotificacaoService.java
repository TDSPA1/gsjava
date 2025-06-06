package Service;

import Entidade.Notificacao;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NotificacaoService {

    private List<Notificacao> notificacoes = new ArrayList<>();
    private int idSequence = 1;


    public Notificacao salvar(Notificacao notificacao) {
        if (notificacao == null) {
            throw new IllegalArgumentException("Notificação não pode ser nula.");
        }

        notificacao.setId(idSequence++);
        notificacoes.add(notificacao);
        return notificacao;
    }

    public List<Notificacao> listarTodos() {
        return new ArrayList<>(notificacoes);
    }

    public Notificacao buscarPorId(int id) {
        return notificacoes.stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public Notificacao atualizar(int id, Notificacao dados) {
        if (dados == null) {
            throw new IllegalArgumentException("Os dados da notificação não podem ser nulos.");
        }

        Notificacao existente = buscarPorId(id);
        if (existente != null) {
            existente.setMensagem(dados.getMensagem());
            existente.setEnviada(dados.isEnviada());
            existente.setData(dados.getData());
            existente.setUsuario(dados.getUsuario());
            existente.setAlarme(dados.getAlarme());
        }
        return existente;
    }


    public boolean deletar(int id) {
        return notificacoes.removeIf(n -> n.getId() == id);
    }
}