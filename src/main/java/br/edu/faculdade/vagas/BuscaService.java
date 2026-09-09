package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaService {

    private final VagaRepository repositorio;

    public BuscaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Vaga> buscar(String area, String senioridade,
                             Boolean aceitaIniciante) {

        return repositorio.todas().stream()
                .filter(v -> area == null
                        || v.area().equalsIgnoreCase(area))
                .filter(v -> senioridade == null
                        || v.senioridade().equalsIgnoreCase(senioridade))
                .filter(v -> aceitaIniciante == null
                        || v.aceitaIniciante() == aceitaIniciante)
                .toList();
    }
}