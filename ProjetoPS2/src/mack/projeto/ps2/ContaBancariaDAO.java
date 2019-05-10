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
            
            String sqlC = "INSERT INTO conta_bancaria(nome_titular, saldo, numero_agencia) VALUES(?,?,?)";
            String sqlR ="SELECT * FROM conta_bancaria";
            String sqlU = "UPDATE conta_bancaria SET nome_titular=?, saldo=?, numero_agencia=? WHERE id_conta=?";
            String sqlD ="DELETE FROM conta_bancaria WHERE id_conta=?";
            
            this.stmC = conn.prepareStatement(sqlC,Statement.RETURN_GENERATED_KEYS);
            this.stmR = conn.prepareStatement(sqlR);
            this.stmU = conn.prepareStatement(sqlU);
            this.stmD = conn.prepareStatement(sqlD);
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
    
    public boolean update (ContaBancaria cb) {
        try{
            this.stmU.setString(1, cb.getNomeCliente());
            this.stmU.setString(2, cb.getSaldo());
            this.stmU.setInt(3, cb.getNumeroAgencia());
            this.stmU.setLong(4, cb.getIdContaBancaria());
            if (this.stmU.executeUpdate() > 0){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(ContaBancaria cb){
        try {
            this.stmD.setLong(1, cb.getIdContaBancaria());
            if (this.stmD.executeUpdate() > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
