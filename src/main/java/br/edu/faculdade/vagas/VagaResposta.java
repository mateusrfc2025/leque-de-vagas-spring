package br.edu.faculdade.vagas;

public record VagaResposta(
        Long id,
        String titulo,
        String area,
        String senioridade,
        String local,
        boolean aceitaIniciante,
        String empresaSlug
) {
    // a listagem não precisa do texto longo, por isso a descricao fica de fora
    public static VagaResposta de(Vaga v) {
        return new VagaResposta(v.id(), v.titulo(), v.area(), v.senioridade(),
                v.local(), v.aceitaIniciante(), v.empresaSlug());
    }
}