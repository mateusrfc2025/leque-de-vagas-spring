package br.edu.faculdade.vagas;

public class SlugJaExisteException extends RuntimeException {
    public SlugJaExisteException(String slug) {
        super("já existe uma empresa com o slug: " + slug);
    }
}