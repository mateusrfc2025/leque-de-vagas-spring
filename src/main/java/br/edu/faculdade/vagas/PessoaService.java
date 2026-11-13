package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository repositorio;

    public PessoaService(PessoaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<PessoaResposta> listar() {
        return repositorio.findAll().stream().map(this::paraResposta).toList();
    }

    public Optional<PessoaResposta> buscarPorId(Long id) {
        return repositorio.findById(id).map(this::paraResposta);
    }

    public PessoaResposta criar(PessoaEntrada entrada) {
        Pessoa nova = new Pessoa(
                entrada.nome(), entrada.email(), entrada.area(), entrada.senioridade());
        return paraResposta(repositorio.save(nova));
    }

    public Optional<PessoaResposta> trocar(Long id, PessoaEntrada nova) {
        return repositorio.findById(id).map(p -> {
            p.trocarDados(nova.nome(), nova.email(), nova.area(), nova.senioridade());
            return paraResposta(repositorio.save(p));
        });
    }

    public boolean apagar(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }

    private PessoaResposta paraResposta(Pessoa p) {
        return new PessoaResposta(p.id(), p.nome(), p.email(), p.area(), p.senioridade());
    }
}