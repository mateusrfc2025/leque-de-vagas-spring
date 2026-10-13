package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {

    private final PessoaRepository repositorio;

    public PessoaService(PessoaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<PessoaResposta> listar() {
        return repositorio.todas().stream()
                .map(this::paraResposta)
                .toList();
    }

    public Optional<PessoaResposta> buscarPorId(String id) {
        return repositorio.porId(id).map(this::paraResposta);
    }

    public PessoaResposta criar(PessoaEntrada nova) {
        Pessoa salva = repositorio.salvar(paraPessoa(UUID.randomUUID().toString(), nova));
        return paraResposta(salva);
    }

    public Optional<PessoaResposta> trocar(String id, PessoaEntrada nova) {
        return repositorio.trocar(id, paraPessoa(id, nova))
                .map(this::paraResposta);
    }

    public boolean apagar(String id) {
        return repositorio.remover(id);
    }

    // entrada → modelo (o id vem de fora: gerado no POST, da URL no PUT)
    private Pessoa paraPessoa(String id, PessoaEntrada e) {
        return new Pessoa(id, e.nome(), e.email(), e.area(), e.senioridade());
    }

    // modelo → resposta
    private PessoaResposta paraResposta(Pessoa p) {
        return new PessoaResposta(p.id(), p.nome(), p.email(), p.area(), p.senioridade());
    }
}