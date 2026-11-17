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
        return repositorio.findAll().stream().map(VagaResposta::de).toList();
    }

    public Optional<VagaResposta> buscarPorId(Long id) {
        return repositorio.findById(id).map(VagaResposta::de);
    }

    public VagaResposta criar(VagaEntrada e) {
        Vaga nova = new Vaga(e.titulo(), e.descricao(), e.area(), e.senioridade(),
                e.local(), e.aceitaIniciante(), e.empresaSlug());
        return VagaResposta.de(repositorio.save(nova));
    }

    public Optional<VagaResposta> trocar(Long id, VagaEntrada e) {
        return repositorio.findById(id).map(vaga -> {
            vaga.trocarDados(e.titulo(), e.descricao(), e.area(), e.senioridade(),
                    e.local(), e.aceitaIniciante(), e.empresaSlug());
            return VagaResposta.de(repositorio.save(vaga));
        });
    }

    public boolean apagar(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }
}