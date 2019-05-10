package mack.projeto.ps2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TimeDAO {

    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;

    private Connection conn;

    public TimeDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/projeto";
            String usuario = "projeto", senha = "projeto";
            Connection conn = DriverManager.getConnection(url, usuario, senha);

            String sqlC = "INSERT INTO time(nome, ano_fundacao, cidade, estado) VALUES(?,?,?,?)";
            String sqlR = "SELECT * FROM time";
            String sqlU = "UPDATE time SET nome=?, ano_fundacao=?, cidade=?, estado=? WHERE id=?";
            String sqlD = "DELETE FROM time WHERE id=?";
            
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

    public List<Time> read() {
        try {
            ResultSet rs = this.stmR.executeQuery();
            List<Time> times = new ArrayList<>();

            while (rs.next()) {
                Time p = new Time();
                p.setIdTime(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setAnoFundacao(rs.getString("ano_fundacao"));
                p.setCidade(rs.getString("cidade"));
                p.setEstado(rs.getString("estado"));
                times.add(p);
            }
            return times;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Time create(Time novoTime) {
        try {
            this.stmC.setString(1, novoTime.getNome());
            this.stmC.setString(2, novoTime.getAnoFundacao());
            this.stmC.setString(3, novoTime.getCidade());
            this.stmC.setString(4, novoTime.getEstado());
            this.stmC.executeUpdate();
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novoTime.setIdTime(id);
            return novoTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Time t) {
        try {
            this.stmU.setString(1, t.getNome());
            this.stmU.setString(2, t.getAnoFundacao());
            this.stmU.setString(3, t.getCidade());
            this.stmU.setString(4, t.getEstado());
            this.stmU.setLong(5, t.getIdTime());
            if (this.stmU.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Time t) {
        try {
            this.stmD.setLong(1, t.getIdTime());
            if (this.stmD.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
