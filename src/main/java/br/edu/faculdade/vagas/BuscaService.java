package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaService {

    private final VagaRepository repositorio;

    public BuscaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    // aula 05: ainda traz todas do banco e filtra em Java. A correção vem na aula 08.
    public List<VagaResposta> buscar(String area, String senioridade, Boolean aceitaIniciante) {
        return repositorio.findAll().stream()
                .filter(v -> area == null || v.area().equalsIgnoreCase(area))
                .filter(v -> senioridade == null || v.senioridade().equalsIgnoreCase(senioridade))
                .filter(v -> aceitaIniciante == null || v.aceitaIniciante() == aceitaIniciante)
                .map(VagaResposta::de)
                .toList();
    }
}