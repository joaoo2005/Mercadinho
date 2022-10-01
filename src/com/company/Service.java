package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    public static List<Clientes> clienteslist = new ArrayList<>();

    static void metodoPrincipal(){
        LoopUM:
        while(true){
            mensagemInicial();
            Scanner s = new Scanner(System.in);
            int opcaoEscolhida = s.nextInt();
            if(opcaoEscolhida == 1){
                criaCliente();

            }else if(opcaoEscolhida == 2){
                System.out.println("CARREGANDO...");
                Scanner paraRemoverCliente = new Scanner(System.in);
                System.out.println("Digigte o cpf que você deseja excluir");
                String cpf = paraRemoverCliente.nextLine();
                removeClienteByCpf(cpf);

            }else if(opcaoEscolhida == 3){
                System.out.println("CARREGANDO...");
                Scanner paraRemoverProdutoDoCliente = new Scanner(System.in);
                System.out.println("Digigte o cpf e o ID do produto que você deseja excluir");
                String cpf = paraRemoverProdutoDoCliente.nextLine();
                String id = paraRemoverProdutoDoCliente.nextLine();
                atualizar(cpf, id);

            }else if(opcaoEscolhida == 4){
                System.out.println("CARREGANDO...");
                Scanner imprimirCliente = new Scanner(System.in);
                System.out.println("Digigte o cpf que você deseja excluir");
                String cpf = imprimirCliente.nextLine();
                getClienteByCpf(cpf);

            }else if(opcaoEscolhida == 5){
                System.out.println("CARREGANDO...");
                getAll();

            }else if(opcaoEscolhida == 6){
                System.out.println("Desligando...");
                break LoopUM;
            }
        }
    }

    static void mensagemInicial(){
        System.out.println("Seja bem vindo!");
        System.out.println("(1) - Para criar um novo cliente e  adicionar no banco de dados");
        System.out.println("(2) - Para removar um Cliente pelo seu CPF");
        System.out.println("(3) - Para remover um produto de um cliente");
        System.out.println("(4) - Para buscar um CLiente especifico pelo seu CPF");
        System.out.println("(5) - Para imprimir todas as informações dos nosso clientes");
        System.out.println("(6) - Para sair do sistema");
    }

    static void post(Clientes clientes){
        clienteslist.add(clientes);

    }

    static void removeClienteByCpf(String cpf){
        for(int i = 0; i < clienteslist.size(); i++){
            if(clienteslist.get(i).cpf.equals(cpf)){
                clienteslist.remove(i);
                System.out.println("Cliente removido com êxito");
            }else{
                System.out.println("Cleinte inexistente");
            }
        }
    }
    static void atualizar(String cpf, String id){
        for(int i = 0; i < clienteslist.size(); i++){
            boolean boolCpf = clienteslist.get(i).cpf.equals(cpf);
            if(boolCpf){
                for(int j = 0; j < clienteslist.get(i).produtos.size(); j++){
                    boolean boolId = clienteslist.get(i).produtos.get(j).id.equals(id);
                    if(boolId){
                        clienteslist.get(i).produtos.remove(j);
                        System.out.println("Produto removido com sucesso!");
                    }else{
                        System.out.println("Produto inexistente na lista de produtos desse cliente");
                    }
                }
            }else{
                System.out.println("Cliente inexistente no sistema!");
            }
        }
    }

    static void getClienteByCpf(String cpf){
        for(int i = 0; i < clienteslist.size(); i++){
            if(clienteslist.get(i).cpf.equals(cpf)){
                System.out.println("CPF: " + clienteslist.get(i).cpf);
                System.out.println("Nome: " + clienteslist.get(i).nome);
                System.out.println("Total: " + clienteslist.get(i).totalCompra);
                for(Produtos p : clienteslist.get(i).produtos){
                    System.out.println("ID: " + p.id);
                    System.out.println("Quantidade: " + p.quantidade);
                    System.out.println("Preço individual: " + p.precoIndividual);
                    System.out.println("Total de compras: " + p.precoTotal);
                    System.out.println("------------------------------");
                }
            }else{
                System.out.println("Cliente inexistente!");
            }
        }
    }

    static void getAll(){
        if(clienteslist.size() != 0 && !clienteslist.isEmpty()){
            for(int i = 0; i < clienteslist.size(); i++){
                System.out.println("CPF: " + clienteslist.get(i).cpf);
                System.out.println("Nome: " + clienteslist.get(i).nome);
                System.out.println("Total: " + clienteslist.get(i).totalCompra);
                System.out.println("------------------------------");
            }
        }else{
            System.out.println("A lista esta vazia!");
        }
    }

    static void criaCliente(){
        System.out.println("CARREGANDO...");
        Scanner paraCriarNovoCliente = new Scanner(System.in);
        System.out.println("Digite as seguintes informaçoes do cliente: CPF, Nome e Produtos desejados");
        String cpf = paraCriarNovoCliente.nextLine();
        String nome = paraCriarNovoCliente.nextLine();
        Produtos produtoDesejado = new Produtos();
        System.out.println("Qual o nome do produto?");
        Scanner paraCriarNovoProduto = new Scanner(System.in);
        String nomeProduto = paraCriarNovoProduto.nextLine();
        System.out.println("Quantos desse produto você deseja? ");
        Integer quantidade = Integer.valueOf(paraCriarNovoProduto.nextLine());
        produtoDesejado.id = String.valueOf(produtoDesejado.hashCode());
        produtoDesejado.nome = nomeProduto;
        produtoDesejado.quantidade = quantidade;
        System.out.println("Preço do produto");
        produtoDesejado.precoIndividual = paraCriarNovoProduto.nextDouble();
        produtoDesejado.atualizarPrecoTotal();
        Clientes cliente = new Clientes();
        cliente.produtos = new ArrayList<>();
        cliente.produtos.add(produtoDesejado);
        cliente.cpf = cpf;
        cliente.nome = nome;
        cliente.totalCompra = cliente.retornaTotalCompra();
        post(cliente);
    }
}
