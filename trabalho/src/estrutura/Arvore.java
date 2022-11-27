package estrutura;

import dados.NoArvore;
import entities.Conta;

import java.util.ArrayList;
import java.util.LinkedList;

public class Arvore {
    private NoArvore raiz;

    public Arvore(){
        this.raiz = null;
    }

    public NoArvore getNoArvore() {
        return this.raiz;
    }

    public ArrayList<Conta> pesquisa (String nome){
        ArrayList<Conta> temp;

        temp = this.pesquisa (nome, this.raiz);
        return temp;
    }

    private ArrayList<Conta> pesquisa (String nome, NoArvore no){
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

    public boolean insere (Conta elem){
        this.raiz = this.insere (elem, this.raiz);
        return true;
    }
    private NoArvore insere (Conta elem, NoArvore no){
        NoArvore novo;

        if (no == null){
            novo = new NoArvore(elem);
            return novo;
        }
        else {
            if (elem.getNome().compareTo(no.getInfo().get(0).getNome()) < 0){
                no.setEsq(this.insere (elem, no.getEsq()));
                return no;
            }
            else if (elem.getNome().compareTo(no.getInfo().get(0).getNome()) > 0){
                no.setDir(this.insere (elem, no.getDir()));
                return no;
            }
            else if(elem.getNome().compareTo(no.getInfo().get(0).getNome()) == 0) {
                no.insereIgual(elem);
                return no;
            }
            return null;
        }
    }

    public boolean insereCamCentral (ArrayList<Conta> elem){
        this.raiz = this.insereCamCentral (elem, this.raiz);
        return true;
    }
    private NoArvore insereCamCentral (ArrayList<Conta> elem, NoArvore no){
        NoArvore novo;

        if (no == null){
            novo = new NoArvore(elem);
            return novo;
        }
        else {
            if (elem.get(0).getNome().compareTo(no.getInfo().get(0).getNome()) < 0){
                no.setEsq(this.insereCamCentral (elem, no.getEsq()));
                return no;
            }
            else if (elem.get(0).getNome().compareTo(no.getInfo().get(0).getNome()) > 0){
                no.setDir(this.insereCamCentral (elem, no.getDir()));
                return no;
            }
            else if(elem.get(0).getNome().compareTo(no.getInfo().get(0).getNome()) == 0) {
                no.insereIgualCamCentral(elem);
                return no;
            }
            return null;
        }
    }


    public ArrayList<ArrayList<Conta>> CamCentral (ArrayList<ArrayList<Conta>> vetOrdenado){
        return (this.FazCamCentral (this.raiz, vetOrdenado));
    }
    private ArrayList<ArrayList<Conta>> FazCamCentral (NoArvore arv, ArrayList<ArrayList<Conta>> vetOrdenado){
        if (arv != null) {
            vetOrdenado = this.FazCamCentral (arv.getEsq(), vetOrdenado);
            vetOrdenado.add (arv.getInfo());
            vetOrdenado = this.FazCamCentral (arv.getDir(), vetOrdenado);
        }
        return vetOrdenado;
    }

    public Arvore ArvoreBalanceada (ArrayList<ArrayList<Conta>> vetOrdenado){
        Arvore temp = new Arvore();
        this.Balancear (vetOrdenado, temp, 0, vetOrdenado.size() -1);
        return temp;
    }

    private void Balancear (ArrayList<ArrayList<Conta>> vet, Arvore temp, int inic, int fim) {
        int meio;
        if (fim >= inic) {
            meio = (inic + fim) / 2;
            temp.insereCamCentral(vet.get(meio));
            this.Balancear(vet, temp, inic, meio - 1);
            this.Balancear(vet, temp, meio + 1, fim);
        }
    }
}
