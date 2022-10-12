package entities;

public class ContaEspecial extends Conta{
    private double limite;

    public ContaEspecial(int numeroDaConta, String cpf, String nome, double limite) {
        super(numeroDaConta, cpf, nome);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double saldo() {
        return super.getValorNaConta() + this.limite;
    }

    @Override
    public String toString() {
        return super.getNumeroDaConta() + " - " + super.getCpf() + " - " + super.getNome() + " - " +
                super.getValorNaConta() + " - " +  this.saldo();
    }
}
