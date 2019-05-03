package mack.projeto.ps2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            this.stmC = this.conn.prepareStatement("INSERT INTO time(nome, matricula) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM time");
            this.stmU = this.conn.prepareStatement("UPDATE time SET nome=?, matricula=? WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM time WHERE id=?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            this.conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Time> read() {
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<Time> times = new ArrayList<>();
            
            while(rs.next()) {
                Time p = new Time();
                p.setIdTime(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                
                times.add(p);
            }
            
            return times;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public Time create(Time novoTime) {
        try {
            this.stmC.setString(1, novoTime.getNome());
            
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novoTime.setIdTime(id);
            
            return novoTime;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
}
