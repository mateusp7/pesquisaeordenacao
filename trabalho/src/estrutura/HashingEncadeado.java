package estrutura;

import entities.Conta;

import java.util.ArrayList;

public class HashingEncadeado {
    private ArrayList<Conta>[] tabelaHash;
    private int quantidade;

    public HashingEncadeado(int tamanho) {
        tabelaHash = new ArrayList[tamanho];
    }

    public int hash(int chave) {
        return chave % tabelaHash.length;
    }

    public int hashing (String chave){
        char carac;
        int i, soma=0;
        for (i=0; i < chave.length(); i++){
            carac = chave.charAt(i);
            soma += Character.getNumericValue(carac);
        }
        return soma;
    }

    public void inserirInHashing(Conta temp){
        int valorHashing = hash(hashing(temp.getNome()));
        if (tabelaHash[valorHashing] == null) {
            this.tabelaHash[valorHashing] = new ArrayList<>();
        }
        tabelaHash[valorHashing].add(temp);
        quantidade++;
    }

    public ArrayList<Conta> pesquisa (String nome){
        ArrayList<Conta> temp = new ArrayList<>();
        for (int i = 0; i < this.quantidade; i++) {
            if (this.tabelaHash[i] != null) {
                for (int j = 0; j < tabelaHash[i].size(); j++) {
                    if (this.tabelaHash[i].get(j).getNome().equalsIgnoreCase(nome)) {
                        temp.add(this.tabelaHash[i].get(j));
                    }
                }
            }
        }
        return temp;
    }
}
