package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaService {

    private final VagaRepository repositorio;

    public BuscaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<VagaResposta> buscar(String area, String senioridade, Boolean aceitaIniciante) {
        return repositorio.findAll().stream()
                .filter(v -> area == null || v.getArea().equalsIgnoreCase(area))
                .filter(v -> senioridade == null || v.getSenioridade().equalsIgnoreCase(senioridade))
                .filter(v -> aceitaIniciante == null || v.isAceitaIniciante() == aceitaIniciante)
                .map(VagaResposta::de)
                .toList();
    }
}