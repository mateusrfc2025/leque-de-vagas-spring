package br.edu.faculdade.vagas;

import jakarta.persistence.*;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String area;
    private String senioridade;

    protected Pessoa() { }

    public Pessoa(String nome, String email, String area, String senioridade) {
        this.nome = nome;
        this.email = email;
        this.area = area;
        this.senioridade = senioridade;
    }

    public void trocarDados(String nome, String email, String area, String senioridade) {
        this.nome = nome;
        this.email = email;
        this.area = area;
        this.senioridade = senioridade;
    }

    public Long id() { return id; }
    public String nome() { return nome; }
    public String email() { return email; }
    public String area() { return area; }
    public String senioridade() { return senioridade; }
}