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

            this.stmC = this.conn.prepareStatement("INSERT INTO professores(nome, matricula) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM professores");
            this.stmU = this.conn.prepareStatement("UPDATE professores SET nome=?, matricula=? WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM professores WHERE id=?");
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
                Jogo p = new Jogo();
                p.setIdJogo(rs.getLong("id"));
                p.setNome(rs.getString("nome"));

                jogos.add(p);
            }

            return jogos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Time create(Jogo novoJogo) {
        try {
            this.stmC.setString(1, novoJogo.getNomeTimeA());

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
}
