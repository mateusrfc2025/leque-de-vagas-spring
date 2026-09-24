package br.edu.faculdade.vagas;

public record VagaResposta(
        String id,
        String titulo,
        String area,
        String senioridade,
        String local,
        boolean aceitaIniciante,
        String empresaSlug
) {

}