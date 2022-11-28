package estrutura;

import dados.Nodo;
import entities.Conta;

import java.util.ArrayList;

public class Avl {
    private Nodo raiz;
    private boolean h;

    public Avl(){
        this.raiz = null;
        this.h = true;
    }

    public void insereRaiz (Conta elem){
        this.raiz = this.insere (elem, this.raiz);
    }

    private Nodo insere (Conta elem, Nodo no) {
        Nodo novo;

        if (no == null) {
            novo = new Nodo(elem);
            this.h = true;
            return novo;
        }
        else{
            if (elem.getNome().compareTo(no.getInfo().get(0).getNome()) < 0){
                no.setEsq(this.insere (elem, no.getEsq()));
                no = this.balancearDir (no);
                return no;
            }
            else if (elem.getNome().compareTo(no.getInfo().get(0).getNome()) > 0){
                no.setDir(this.insere (elem, no.getDir()));
                no = this.balancearEsq (no);
                return no;
            } else if (elem.getNome().compareTo(no.getInfo().get(0).getNome()) == 0) {
                no.insereIgual(elem);
                return no;
            }
            return null;
        }
    }

    private Nodo balancearDir (Nodo no){
        if (this.h)
            switch (no.getFatorBalanceamento()){
                case 1 : no.setFatorBalanceamento((byte)0);
                    this.h = false;
                    break;
                case 0 : no.setFatorBalanceamento((byte)-1);
                    break;
                case -1: no = this.rotacaoDireita (no);
            }
        return no;
    }

    private Nodo balancearEsq (Nodo no){
        if (this.h)
            switch (no.getFatorBalanceamento()){
                case -1: no.setFatorBalanceamento((byte)0);
                    this.h = false;
                    break;
                case 0 : no.setFatorBalanceamento((byte)1);
                    break;
                case 1 : no = this.rotacaoEsquerda (no); }
        return no;
    }

    private Nodo rotacaoDireita (Nodo no){
        Nodo temp1, temp2;
        temp1 = no.getEsq();
        if (temp1.getFatorBalanceamento() == -1){
            no.setEsq(temp1.getDir());
            temp1.setDir(no);
            no.setFatorBalanceamento((byte)0);
            no = temp1;
        }
        else {
            try {
                temp2 = temp1.getDir();
                temp1.setDir(temp2.getEsq());
                temp2.setEsq(temp1);
                no.setEsq(temp2.getDir());
                temp2.setDir(no);
                if (temp2.getFatorBalanceamento() == -1)
                    no.setFatorBalanceamento((byte)1);
                else
                    no.setFatorBalanceamento((byte)0);
                if (temp2.getFatorBalanceamento() == 1)
                    temp1.setFatorBalanceamento((byte)-1);
                else
                    temp1.setFatorBalanceamento((byte)0);
                no = temp2;
            } catch (NullPointerException e) {

            }
        }
        no.setFatorBalanceamento((byte)0);
        this.h = false;
        return no;
    }

    private Nodo rotacaoEsquerda (Nodo no) {
        Nodo temp1, temp2;
        temp1 = no.getDir();
        if (temp1.getFatorBalanceamento() == 1) {
            no.setDir(temp1.getEsq());
            temp1.setEsq(no);
            no.setFatorBalanceamento((byte) 0);
            no = temp1;
        } else {
            try {
                temp2 = temp1.getEsq();
                temp1.setEsq(temp2.getDir());
                temp2.setDir(temp1);
                no.setDir(temp2.getEsq());
                temp2.setEsq(no);
                if (temp2.getFatorBalanceamento() == 1)
                    no.setFatorBalanceamento((byte) -1);
                else
                    no.setFatorBalanceamento((byte) 0);
                if (temp2.getFatorBalanceamento() == -1)
                    temp1.setFatorBalanceamento((byte) 1);
                else
                    temp1.setFatorBalanceamento((byte) 0);
                no = temp2;
            } catch (NullPointerException e) {

            }
        }
        no.setFatorBalanceamento((byte) 0);
        this.h = false;
        return no;
    }

    public ArrayList<Conta> pesquisa (String nome){
        ArrayList<Conta> temp;

        temp = this.pesquisa (nome, this.raiz);
        return temp;
    }

    private ArrayList<Conta> pesquisa (String nome, Nodo no){
        ArrayList<Conta> temp;
        temp = null;
        if (no!=null){
            if (nome.compareTo(no.getInfo().get(0).getNome()) < 0)
                temp = this.pesquisa (nome, no.getEsq());
            else if (nome.compareTo(no.getInfo().get(0).getNome()) > 0) {
                temp = this.pesquisa (nome, no.getDir());
            } else if (nome.compareTo(no.getInfo().get(0).getNome()) == 0) {
                return no.getInfo();
            }
        }
        return temp;
    }
}
