package mack.projeto.ps2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaBancariaDAO {
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    
    private Connection conn;
    
    public ContaBancariaDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/projeto";
            String usuario = "projeto", senha = "projeto";
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            
            this.stmC = this.conn.prepareStatement("INSERT INTO contabancaria(nome, agencia) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM contabancaria");
            this.stmU = this.conn.prepareStatement("UPDATE contabancaria SET nome=?, agencia=? WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM contabancaria WHERE id=?");
        }catch(Exception e) {
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
    
    public List<ContaBancaria> read() {
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<ContaBancaria> professores = new ArrayList<>();
            
            while(rs.next()) {
                ContaBancaria cb = new ContaBancaria();
                cb.setIdContaBancaria(rs.getLong("id"));
                cb.setNomeCliente(rs.getString("nome"));
                cb.setNumeroAgencia(rs.getInt("agencia"));
                
                professores.add(cb);
            }
            
            return professores;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ContaBancaria create(ContaBancaria novoProfessor) {
        try {
            this.stmC.setString(1, novoProfessor.getNomeCliente());
            this.stmC.setInt(2, novoProfessor.getNumeroAgencia());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novoProfessor.setIdContaBancaria(id);
            
            return novoProfessor;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
