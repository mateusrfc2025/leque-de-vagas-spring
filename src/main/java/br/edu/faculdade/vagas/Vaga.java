package br.edu.faculdade.vagas;

//import org.springframework.stereotype.Repository;

public record Vaga(
    String id,
    String titulo,
    //String descricao,
    String area,
    String senioridade,
    String local,
    boolean aceitaIniciante,
    String empresaSlug
) { }
