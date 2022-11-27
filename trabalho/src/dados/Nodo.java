package dados;

import entities.Conta;

import java.util.ArrayList;

public class Nodo {
    private ArrayList<Conta> info;
    private Nodo esq, dir;
    private byte fatorBalanceamento;

    public Nodo (Conta i){
        info = new ArrayList<>();
        this.info.add(i);
        this.fatorBalanceamento = 0;
    }
    public Nodo getDir() {
        return this.dir;
    }
    public void setDir(Nodo dir) {
        this.dir = dir;
    }
    public Nodo getEsq() {
        return this.esq;
    }
    public void setEsq(Nodo esq) {
        this.esq = esq;
    }

    public byte getFatorBalanceamento () {
        return this.fatorBalanceamento;
    }
    public void setFatorBalanceamento (byte fatorBalanceamento) {
        this.fatorBalanceamento = fatorBalanceamento;
    }
    public ArrayList<Conta> getInfo() {
        return this.info;
    }

    public void insereIgual(Conta conta) {
        this.getInfo().add(conta);
    }
}
