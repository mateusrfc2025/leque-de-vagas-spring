package br.edu.faculdade.vagas;

public record EmpresaResposta(
        Long id,
        String nome,
        String slug,
        String site,
        String descricao
) { }//guarda a empresa entrada com um id