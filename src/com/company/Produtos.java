package com.company;

public class Produtos {
    String id;
    Integer quantidade;
    String nome;
    Double precoIndividual;
    Double precoTotal;

    public void atualizarPrecoTotal(){
        this.precoTotal = this.precoIndividual * this.quantidade;
    }

}
