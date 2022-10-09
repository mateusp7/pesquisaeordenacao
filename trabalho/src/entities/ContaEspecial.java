package entities;

public class ContaEspecial extends Conta{
    private double limite;

    public ContaEspecial(String nome, String cpf, int numeroDaConta, double limite) {
        super(nome, cpf, numeroDaConta);
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
        return "ContaEspecial{" +
                "limite=" + limite +
                "} " + super.toString();
    }
}
