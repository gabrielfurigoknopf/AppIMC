/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.ccc.appimc.model;

import java.io.Serializable;

/**
 * Classes
 * @author gabriel
 */
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cpf;
    private String nome;
    private double peso;
    private double altura;

    public Pessoa(String cpf, String nome, double peso, double altura) {
        this.cpf = cpf;
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }
}