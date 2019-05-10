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

            String sqlC = "INSERT INTO jogo(nome_time_a, nome_time_b, gols_time_a, gols_time_b) VALUES(?,?,?,?)";
            String sqlR = "SELECT * FROM jogo";
            String sqlU = "UPDATE jogo SET nome_time_a=?, nome_time_b=?, gols_time_a=?, gols_time_b=? WHERE id_jogo=?";
            String sqlD = "DELETE FROM jogo WHERE id_jogo=?";
            
            this.stmC = conn.prepareStatement(sqlC,Statement.RETURN_GENERATED_KEYS);
            this.stmR = conn.prepareStatement(sqlR);
            this.stmU = conn.prepareStatement(sqlU);
            this.stmD = conn.prepareStatement(sqlD);
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
                j.setIdJogo(rs.getLong("id_jogo"));
                j.setNomeTimeA(rs.getString("nome_time_a"));
                j.setNomeTimeB(rs.getString("nome_time_b"));
                j.setGolsTimeA(rs.getInt("gols_time_a"));
                j.setGolsTimeB(rs.getInt("gols_time_b"));
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

    public boolean update(Jogo j){
        try{
            this.stmU.setString(1, j.getNomeTimeA());
            this.stmU.setString(2, j.getNomeTimeB());
            this.stmU.setInt(3, j.getGolsTimeA());
            this.stmU.setInt(4, j.getGolsTimeB());
            this.stmU.setLong(5, j.getIdJogo());
            if (this.stmU.executeUpdate() > 0){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(Jogo j){
        try{
            this.stmD.setLong(1, j.getIdJogo());
            if (this.stmD.executeUpdate() > 0){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
