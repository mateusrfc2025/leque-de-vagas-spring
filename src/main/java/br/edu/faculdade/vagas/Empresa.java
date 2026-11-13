package br.edu.faculdade.vagas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 60)
    private String slug;

    private String site;

    @Column(length = 1000)
    private String descricao;

    protected Empresa() { }

    public Empresa(String nome, String slug, String site, String descricao) {
        this.nome = nome;
        this.slug = slug;
        this.site = site;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getSlug() { return slug; }
    public String getSite() { return site; }
    public String getDescricao() { return descricao; }
}