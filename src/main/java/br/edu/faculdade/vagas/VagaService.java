package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class VagaService {

    private final VagaRepository repositorio;

    public VagaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Vaga> listar() {
        return repositorio.todas();
    }

    public Optional<Vaga> buscarPorId(String id) {
        return repositorio.porId(id);
    }

    public Vaga criar(Vaga nova) {
        Vaga comId = new Vaga(
                UUID.randomUUID().toString(),
                nova.titulo(), nova.area(),
                nova.senioridade(), nova.local(),
                nova.aceitaIniciante(), nova.empresaSlug());

        repositorio.salvar(comId);

        return new Vaga(comId.id(), comId.titulo(), comId.area(), comId.senioridade(), comId.local(), comId.aceitaIniciante(), comId.empresaSlug());
    }

    public Optional<Vaga> trocar(String id, Vaga nova) {
        Vaga comId = new Vaga(
                id,
                nova.titulo(), nova.area(),
                nova.senioridade(), nova.local(),
                nova.aceitaIniciante(), nova.empresaSlug());
        return repositorio.trocar(id, comId);
    }

    public boolean apagar(String id) {
        return repositorio.remover(id);
    }
}