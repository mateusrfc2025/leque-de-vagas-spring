package br.edu.faculdade.vagas;

public record Empresa( //recurso publico para armazenar dados
    String id, //campos que represantam os dados da empresa em texto(spring
    String nome,
    String slug,
    String site,
    String descricao
) { }
