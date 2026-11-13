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

    public Map<String, Object> resumo() {
        List<Vaga> todas = vagas.findAll();
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalDeVagas", todas.size());
        resumo.put("aceitamIniciante", todas.stream().filter(Vaga::isAceitaIniciante).count());
        resumo.put("porArea", todas.stream()
                .collect(Collectors.groupingBy(Vaga::getArea, Collectors.counting())));
        resumo.put("porSenioridade", todas.stream()
                .collect(Collectors.groupingBy(Vaga::getSenioridade, Collectors.counting())));
        return resumo;
    }
}