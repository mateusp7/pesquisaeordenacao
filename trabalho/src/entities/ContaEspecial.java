package entities;

public class ContaEspecial extends Conta{
    private double limite;

    public ContaEspecial(int numeroDaConta, String nome, String cpf, double valorNaConta, double limite) {
        super(numeroDaConta, nome, cpf, valorNaConta);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double saldo() {
        return this.getValorNaConta() + this.limite;
    }

    @Override
    public String toString() {
        return super.getNumeroDaConta() + ";" + super.getNome() + ";" + super.getCpf() + ";" +
                super.saldo() + ";" +  this.getLimite();
    }
}
