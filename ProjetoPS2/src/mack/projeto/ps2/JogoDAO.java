package mack.projeto.ps2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JogoDAO {

    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;

    private Connection conn;

    public JogoDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/projeto";
            String usuario = "projeto", senha = "projeto";
            Connection conn = DriverManager.getConnection(url, usuario, senha);

            this.stmC = this.conn.prepareStatement("INSERT INTO jogo(nome_time_a, nome_time_b, gols_time_a, gols_time_b) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM jogo");
            this.stmU = this.conn.prepareStatement("UPDATE jogo SET nome_time_a=?, nome_time_b=?, gols_time_a=?, gols_time_b=? WHERE id_jogo=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM jogo WHERE id_jogo=?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Jogo> read() {
        try {
            ResultSet rs = this.stmR.executeQuery();

            List<Jogo> jogos = new ArrayList<>();

            while (rs.next()) {
                Jogo j = new Jogo();
                j.setIdJogo(rs.getLong("id"));
                j.setNomeTimeA(rs.getString("time1"));
                j.setNomeTimeB(rs.getString("time2"));
                j.setGolsTimeA(rs.getInt("golsTime1"));
                j.setGolsTimeB(rs.getInt("golsTime2"));
                jogos.add(j);
            }
            return jogos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Jogo create(Jogo novoJogo) {
        try {
            this.stmC.setString(1, novoJogo.getNomeTimeA());
            this.stmC.setString(2, novoJogo.getNomeTimeB());
            this.stmC.setInt(3, novoJogo.getGolsTimeA());
            this.stmC.setInt(4, novoJogo.getGolsTimeB());
            this.stmC.executeUpdate();
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novoJogo.setIdJogo(id);
            return novoJogo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Jogo update

    {

    }
    public Jogo delete

    {

    }
    //Fazer Update e Delete
}
