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
            
            this.stmC = this.conn.prepareStatement("INSERT INTO conta_bancaria(nome_titular, saldo, numero_agencia) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM conta_bancaria");
            this.stmU = this.conn.prepareStatement("UPDATE conta_bancaria SET nome_titular=?, saldo=?, numero_agencia=? WHERE id_conta=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM conta_bancaria WHERE id_conta=?");
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
                cb.setSaldo(rs.getString("saldo"));
                cb.setNumeroAgencia(rs.getInt("agencia"));
                
                professores.add(cb);
            }
            
            return professores;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ContaBancaria create(ContaBancaria novaConta) {
        try {
            this.stmC.setString(1, novaConta.getNomeCliente());
            this.stmC.setString(2, novaConta.getSaldo());
            this.stmC.setInt(3, novaConta.getNumeroAgencia());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novaConta.setIdContaBancaria(id);
            
            return novaConta;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
