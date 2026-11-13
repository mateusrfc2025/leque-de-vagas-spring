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

    protected Vaga() { }

    public Vaga(String titulo, String descricao, String area, String senioridade,
                String local, boolean aceitaIniciante, String empresaSlug) {
        trocarDados(titulo, descricao, area, senioridade, local, aceitaIniciante, empresaSlug);
    }

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

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getArea() { return area; }
    public String getSenioridade() { return senioridade; }
    public String getLocal() { return local; }
    public boolean isAceitaIniciante() { return aceitaIniciante; }
    public String getEmpresaSlug() { return empresaSlug; }
}