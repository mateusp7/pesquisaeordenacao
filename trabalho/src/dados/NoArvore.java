package dados;

import entities.Conta;

public class NoArvore {
        private Conta info;
        private NoArvore dir, esq;

        public NoArvore (Conta _info){
            this.info = _info;
        }
        public NoArvore getDir() {
            return dir;
        }
        public void setDir(NoArvore dir) {
            this.dir = dir;
        }
        public NoArvore getEsq() {
            return esq;
        }
        public void setEsq(NoArvore esq) {
            this.esq = esq;
        }
        public Conta getInfo() {
            return info;
        }
        public void setInfo(Conta novo) {
            this.info = novo;
        }

}
