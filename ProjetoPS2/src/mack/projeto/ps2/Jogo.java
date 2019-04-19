package mack.projeto.ps2;

public class Jogo {
    private long idJogo;
    private String nomeTimeA;
    private String nomeTimeB;
    private int golsTimeA;
    private int golsTimeB;
    
    public Jogo(){ }
    

    public Jogo(long idJogo,String nomeTimeA,String nomeTimeB, int golsTimeA,  int golsTimeB){
        this.idJogo = idJogo;
        this.nomeTimeA = nomeTimeA;
        this.nomeTimeB = nomeTimeB;
        this.golsTimeA = golsTimeA;
        this.golsTimeB = golsTimeB;
    }

    public long getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(long idJogo) {
        this.idJogo = idJogo;
    }

    public String getNomeTimeA() {
        return nomeTimeA;
    }

    public void setNomeTimeA(String nomeTimeA) {
        this.nomeTimeA = nomeTimeA;
    }

    public String getNomeTimeB() {
        return nomeTimeB;
    }

    public void setNomeTimeB(String nomeTimeB) {
        this.nomeTimeB = nomeTimeB;
    }

    public int getGolsTimeA() {
        return golsTimeA;
    }

    public void setGolsTimeA(int golsTimeA) {
        this.golsTimeA = golsTimeA;
    }

    public int getGolsTimeB() {
        return golsTimeB;
    }

    public void setGolsTimeB(int golsTimeB) {
        this.golsTimeB = golsTimeB;
    }
}
