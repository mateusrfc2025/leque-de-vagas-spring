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

    public List<EmpresaResposta> listar() {
        return repositorio.findAll().stream().map(this::paraResposta).toList();
    }

    public Optional<EmpresaResposta> buscarPorSlug(String slug) {
        return repositorio.findBySlug(slug).map(this::paraResposta);
    }

    public EmpresaResposta criar(EmpresaEntrada nova) {
        if (repositorio.findBySlug(nova.slug()).isPresent()) {
            throw new RecursoJaExiste("já existe uma empresa com o slug: " + nova.slug());
        }
        Empresa salva = repositorio.save(
                new Empresa(nova.nome(), nova.slug(), nova.site(), nova.descricao()));
        return paraResposta(salva);
    }

    public boolean apagar(String slug) {
        return repositorio.findBySlug(slug)
                .map(empresa -> {
                    repositorio.delete(empresa);
                    return true;
                })
                .orElse(false);
    }

    private EmpresaResposta paraResposta(Empresa e) {
        return new EmpresaResposta(e.getId(), e.getNome(), e.getSlug(), e.getSite(), e.getDescricao());
    }
}