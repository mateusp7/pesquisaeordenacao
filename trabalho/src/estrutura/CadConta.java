package estrutura;

import entities.Conta;
import interfaces.Vetor;

import java.util.ArrayList;

public class CadConta implements Vetor {
    private ArrayList<Conta> arrayContas;

    public CadConta(int tamanho) {
        this.arrayContas = new ArrayList<>(tamanho);
    }

    public void inserirVetor(Conta conta) {
        this.arrayContas.add(conta);
    }

    public int getTamanhoVetor() {
        return this.arrayContas.size();
    }

    public Conta getConta(int posicao) {
        return arrayContas.get(posicao);
    }

    public void limpar() {
        this.arrayContas.clear();
    }

    public ArrayList<Conta> getArrayContas() {
        return this.arrayContas;
    }

    public int pesqBinaria (int numeroDaConta) {
        int meio, esq, dir;
        esq = 0;
        dir = this.arrayContas.size() - 1;
        while (esq <= dir){
            meio = (esq + dir) / 2;
            /*String numeroContaString = Integer.toString(this.arrayContas.get(meio).getNumeroDaConta());
            int numeroDaContaInt = Integer.parseInt(numeroContaString.substring(1));*/
            if (numeroDaConta == this.arrayContas.get(meio).getNumeroDaConta())
                return meio;
            else{
                if (numeroDaConta < this.arrayContas.get(meio).getNumeroDaConta())
                    dir = meio - 1;
                else
                    esq = meio + 1;
            }
        }
        return -1;
    }

    public void shellsort (ArrayList<Conta> arrayContas){
        int i, j, h;
        Conta temp;
        h = 1;
        do{
            h = 3*h+1;
        }while (h < arrayContas.size());
        do{
            h = h/3;
            for (i=h; i < arrayContas.size(); i++){
                temp = arrayContas.get(i);
                j = i;
                while (arrayContas.get(j-h).getNumeroDaConta() > temp.getNumeroDaConta()){
                    arrayContas.set(j, arrayContas.get(j-h));
                    j -= h;
                    if (j < h)
                        break;
                }
                arrayContas.set(j, temp);
            }
        }while (h != 1);
    }

    public void quicksort(ArrayList<Conta> arrayContas) {
        ordena (arrayContas, 0, arrayContas.size() - 1);
    }

    private void ordena (ArrayList<Conta> arrayContas,int esq, int dir) {
        int pivo, i = esq, j = dir;
        Conta temp;
        pivo = arrayContas.get((i + j) / 2).getNumeroDaConta();
        do {
            while (arrayContas.get(i).getNumeroDaConta() < pivo) i++;
            while (arrayContas.get(j).getNumeroDaConta() > pivo) j--;
            if (i <= j) {
                temp = arrayContas.get(i);
                arrayContas.set(i, arrayContas.get(j));
                arrayContas.set(j, temp);
                i++;
                j--;
            }
        } while (i <= j);
        if (esq < j ) ordena(arrayContas, esq, j);
        if (dir > i ) ordena(arrayContas, i, dir);
    }


    public String toString() {
        String resultado = "";
        for (int i = 0; i < this.getTamanhoVetor(); i++) {
            resultado += this.getConta(i) + "\n";
        }
        return resultado;
    }
}
