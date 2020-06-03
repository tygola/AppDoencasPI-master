package com.example.appdoencaspi;

public class Doenca {
    public Doenca(int id, String nome, String sintomas, String prevencao) {
        this.id = id;
        this.nome = nome;
        this.sintomas = sintomas;
        this.prevencao = prevencao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getPrevencao() {
        return prevencao;
    }

    public void setPrevencao(String prevencao) {
        this.prevencao = prevencao;
    }

    private int id;
    private String nome, sintomas, prevencao;

    public Doenca() {
    }
}
