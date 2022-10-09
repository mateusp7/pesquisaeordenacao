package application;


import entities.Conta;
import estrutura.CadConta;

import java.util.ArrayList;

public class Program2 {
    public static void main(String[] args) {
        ArrayList<Conta> contas = new ArrayList<>();
        CadConta cadConta = new CadConta(contas);

        Conta conta1 = new Conta("Mateus", "18727714756", 2);
        Conta conta2 = new Conta("Julia", "123456789", 10);
        Conta conta3 = new Conta("Brayan", "157789", 3);
        Conta conta4 = new Conta("Franz", "15632457", 6);
        Conta conta5 = new Conta("Brayan", "8964552", 5);
        Conta conta6 = new Conta("Brayan", "123659497", 1);
        cadConta.inserirVetor(conta1);
        cadConta.inserirVetor(conta2);
        cadConta.inserirVetor(conta3);
        cadConta.inserirVetor(conta4);
        cadConta.inserirVetor(conta5);
        cadConta.inserirVetor(conta6);


        System.out.println(cadConta);

        System.out.println("Resultado pesquisa binária");

        cadConta.quicksort(contas);
        System.out.println(cadConta.pesqBinaria(1));


    }
}
