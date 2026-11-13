package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    private final VagaRepository repositorio;

    public VagaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<VagaResposta> listar() {
        return repositorio.findAll().stream().map(this::paraResposta).toList();
    }

    public Optional<VagaResposta> buscarPorId(Long id) {
        return repositorio.findById(id).map(this::paraResposta);
    }

    public VagaResposta criar(VagaEntrada e) {
        Vaga nova = new Vaga(e.titulo(), e.descricao(), e.area(), e.senioridade(),
                e.local(), e.aceitaIniciante(), e.empresaSlug());
        return paraResposta(repositorio.save(nova));
    }

    public Optional<VagaResposta> trocar(Long id, VagaEntrada e) {
        return repositorio.findById(id).map(vaga -> {
            vaga.trocarDados(e.titulo(), e.descricao(), e.area(), e.senioridade(),
                    e.local(), e.aceitaIniciante(), e.empresaSlug());
            return paraResposta(repositorio.save(vaga));
        });
    }

    public boolean apagar(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }

    private VagaResposta paraResposta(Vaga v) {
        return new VagaResposta(v.getId(), v.getTitulo(), v.getDescricao(), v.getArea(),
                v.getSenioridade(), v.getLocal(), v.isAceitaIniciante(), v.getEmpresaSlug());
    }
}