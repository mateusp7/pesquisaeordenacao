package estrutura;

import dados.NoArvore;

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

}
