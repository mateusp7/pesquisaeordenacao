package interfaces;

import entities.Conta;

import java.util.ArrayList;

public interface Vetor {
    int getTamanhoVetor();
    Conta getConta(int posicao);
    void inserirVetor(Conta conta);
    void quicksort(ArrayList<Conta> arrayContas);
    void shellsort (ArrayList<Conta> arrayContas);
    int pesqBinaria (int numeroDaConta);
}
