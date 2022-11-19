package estrutura;

import dados.NoArvore;
import entities.Conta;

public class Arvore {
    private NoArvore raiz;

    public Arvore(){
        this.raiz = null;
    }

    public boolean pesquisa (String nome){
        NoArvore temp;

        temp = this.pesquisa (nome, this.raiz);
        if (temp != null)
            return true;
        else
            return false;
    }

    private NoArvore pesquisa (String nome, NoArvore no){
        NoArvore temp;
        temp = no;

        if (temp != null){
            if (nome.compareTo(temp.getInfo().getNome()) < 0)
                temp = this.pesquisa (nome, temp.getEsq());
            else{
                if (nome.compareTo(temp.getInfo().getNome()) > 0)
                    temp = this.pesquisa (nome, temp.getDir());
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
            if (elem.getNome().compareTo(no.getInfo().getNome()) < 0){
                no.setEsq(this.insere (elem, no.getEsq()));
                return no;
            }
            else{
                no.setDir(this.insere (elem, no.getDir()));
                return no;
            }
        }
    }

    private NoArvore remove (String nome, NoArvore arv){
        if (arv == null)
            return arv;
        else{
            if (nome.compareTo(arv.getInfo().getNome()) < 0)
                arv.setEsq(this.remove (nome, arv.getEsq()));
            else
            if (nome.compareTo(arv.getInfo().getNome()) > 0)
                arv.setDir(this.remove (nome, arv.getDir()));
            else
            if (arv.getDir() == null)
                return arv.getEsq();
            else
            if (arv.getEsq() == null)
                return arv.getDir();
            else
                arv.setEsq(this.arruma (arv, arv.getEsq()));
        }
        return arv;
    }
    private NoArvore arruma (NoArvore Q, NoArvore R){
        if (R.getDir() != null)
            R.setDir(this.arruma (Q, R.getDir()));
        else{
            Q.setInfo(R.getInfo());
            R = R.getEsq();
        }
        return R;
    }

    public CadConta CamCentral (CadConta vetOrdenado){
        return (this.FazCamCentral (this.raiz, vetOrdenado));
    }
    private CadConta FazCamCentral (NoArvore arv, CadConta vetOrdenado){
        if (arv != null) {
            vetOrdenado = this.FazCamCentral (arv.getEsq(), vetOrdenado);
            vetOrdenado.inserirVetor (arv.getInfo());
            vetOrdenado = this.FazCamCentral (arv.getDir(), vetOrdenado);
        }
        return vetOrdenado;
    }

    public Arvore ArvoreBalanceada (CadConta vetOrdenado){
        Arvore temp = new Arvore();
        this.Balancear (vetOrdenado, temp, 0, vetOrdenado.getTamanhoVetor()-1);
        return temp;
    }

    private void Balancear (CadConta vet, Arvore temp, int inic, int fim) {
        int meio;
        if (fim >= inic) {
            meio = (inic + fim) / 2;
            temp.insere(vet.getConta(meio));
            this.Balancear(vet, temp, inic, meio - 1);
            this.Balancear(vet, temp, meio + 1, fim);
        }
    }
}
