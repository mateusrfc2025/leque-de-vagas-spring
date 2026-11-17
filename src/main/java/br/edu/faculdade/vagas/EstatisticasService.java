package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstatisticasService {

    private final VagaRepository vagas;

    public EstatisticasService(VagaRepository vagas) {
        this.vagas = vagas;
    }

    // aula 05: as contagens ainda são feitas em Java. A contagem no banco vem na aula 08.
    public Map<String, Object> resumo() {
        List<Vaga> todas = vagas.findAll();
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalDeVagas", todas.size());
        resumo.put("aceitamIniciante", todas.stream().filter(Vaga::aceitaIniciante).count());
        resumo.put("porArea", todas.stream()
                .collect(Collectors.groupingBy(Vaga::area, Collectors.counting())));
        resumo.put("porSenioridade", todas.stream()
                .collect(Collectors.groupingBy(Vaga::senioridade, Collectors.counting())));
        return resumo;
    }
}