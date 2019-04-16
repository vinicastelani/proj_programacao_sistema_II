package mack.projeto.ps2;

public class Time {
    private long idTime;
    private String nome;
    private float anoFundacao;
    private String cidade;
    private String estado;
    
    public Time(){ }
    
    /**
     *
     * @param idTime
     * @param nome
     * @param anoFundacao
     * @param cidade
     * @param estado
     */
    public Time(long idTime,String nome,float anoFundacao, String cidade,  String estado){
        this.idTime = idTime;
        this.nome = nome;
        this.anoFundacao = anoFundacao;
        this.cidade = cidade;
        this.estado = estado;
    }

    public long getIdTime() {
        return idTime;
    }

    public void setIdTime(long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(float anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
