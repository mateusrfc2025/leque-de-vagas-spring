package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository repositorio;

    public EmpresaService(EmpresaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Empresa> listar() {
        return repositorio.findAll();
    }

    public Optional<Empresa> buscarPorSlug(String slug) {
        return repositorio.findBySlug(slug);
    }

    public EmpresaResposta criar(EmpresaEntrada nova) {
        if (repositorio.findBySlug(nova.slug()).isPresent()) {
            throw new SlugJaExisteException(nova.slug());
        }

        Empresa salva = repositorio.save(
                new Empresa(nova.nome(), nova.slug(), nova.site(), nova.descricao()));

        return new EmpresaResposta(salva.getId(), salva.getNome(), salva.getSlug(),
                salva.getSite(), salva.getDescricao());
    }

    public boolean apagar(String slug) {
        return repositorio.findBySlug(slug)
                .map(empresa -> {
                    repositorio.delete(empresa);
                    return true;
                })
                .orElse(false);
    }
}