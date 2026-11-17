package br.edu.faculdade.vagas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String senioridade;

    private String local;

    private boolean aceitaIniciante;

    @Column(nullable = false)
    private String empresaSlug;

    protected Vaga() { } // a JPA exige

    public Vaga(String titulo, String descricao, String area, String senioridade,
                String local, boolean aceitaIniciante, String empresaSlug) {
        trocarDados(titulo, descricao, area, senioridade, local, aceitaIniciante, empresaSlug);
    }

    // usado pelo PUT: troca tudo, menos o id
    public void trocarDados(String titulo, String descricao, String area, String senioridade,
                            String local, boolean aceitaIniciante, String empresaSlug) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.area = area;
        this.senioridade = senioridade;
        this.local = local;
        this.aceitaIniciante = aceitaIniciante;
        this.empresaSlug = empresaSlug;
    }

    public Long id() { return id; }
    public String titulo() { return titulo; }
    public String descricao() { return descricao; }
    public String area() { return area; }
    public String senioridade() { return senioridade; }
    public String local() { return local; }
    public boolean aceitaIniciante() { return aceitaIniciante; }
    public String empresaSlug() { return empresaSlug; }
}