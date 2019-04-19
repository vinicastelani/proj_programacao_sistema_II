package mack.projeto.ps2;

public class ContaBancaria {
    private long idContaBancaria;
    private String nomeCliente;
    private String saldo;
    private int numeroAgencia;
    
    public ContaBancaria(){ }
    
    public ContaBancaria(long idTime,String nome,String saldo, int numeroAgencia){
        this.idContaBancaria = idContaBancaria;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
        this.numeroAgencia = numeroAgencia;
    }

    public long getIdContaBancaria() {
        return idContaBancaria;
    }

    public void setIdContaBancaria(long idContaBancaria) {
        this.idContaBancaria = idContaBancaria;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }
    
}
