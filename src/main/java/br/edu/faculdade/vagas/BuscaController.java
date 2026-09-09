package br.edu.faculdade.vagas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class BuscaController {

    private final BuscaService servico;

    public BuscaController(BuscaService servico) {
        this.servico = servico;
    }

    @GetMapping("/busca")
    public List<Vaga> buscar(
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String senioridade,
            @RequestParam(required = false) Boolean aceitaIniciante) {

        return servico.buscar(area, senioridade, aceitaIniciante);
    }
}