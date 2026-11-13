package br.edu.faculdade.vagas;

public record PessoaResposta(
        Long id,
        String nome,
        String email,
        String area,
        String senioridade
) { }