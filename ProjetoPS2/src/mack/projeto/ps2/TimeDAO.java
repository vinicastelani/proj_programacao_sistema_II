package mack.projeto.ps2;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.*;

@Path("/time")
@Produces(MediaType.APPLICATION_JSON)
public class TimeDAO {

    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/projeto";
            String usuario = "projeto", senha = "projeto";
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            String sqlInsert = "INSERT INTO time (nome, ano_fundacao, cidade, estado)";
            sqlInsert += " VALUES (?,?,?,?)";
            String sqlSelect = "SELECT * FROM time";
            String sqlUpdate = "UPDATE time SET nome=?, ano_fundacao=?, cidade=?, estado=?";
            sqlUpdate += " WHERE id_time=?";
            String sqlDelete = " DELETE FROM time WHERE id_time=?";

            PreparedStatement stmInsert = conexao.prepareStatement(sqlInsert);
            PreparedStatement stmSelect = conexao.prepareStatement(sqlSelect);
            PreparedStatement stmUpdate = conexao.prepareStatement(sqlUpdate);
            PreparedStatement stmDelete = conexao.prepareStatement(sqlDelete);

        } catch (ClassNotFoundException ex) {
            System.out.println("Falha na carga do Driver JDBC!");
        } catch (SQLException ex) {
            System.out.println("Falha ao acessar a base de dados: " + ex.getMessage());
        }
    }

    private List<Time> times;
    private long proximoId;

    public TimeDAO() {
        proximoId = 1;
        times = new ArrayList<>();
        times.add(new Time(proximoId++, "São Paulo", 1930, "São Paulo", "SP"));
        times.add(new Time(proximoId++, "Palmeiras", 1914, "São Paulo", "SP"));
    }

    @GET
    public List<Time> read() {
        return times;
    }

    @POST
    public Time create(Time a) {
        a.setIdTime(proximoId++);
        times.add(a);
        return a;
    }

    @PUT
    @Path("{id}")
    public Time update(@PathParam("id") LongParam id, Time a) {
        for (Time time : times) {
            if (time.getIdTime() == id.get()) {
                time.setNome(a.getNome());
                time.setAnoFundacao(a.getAnoFundacao());
                time.setCidade(a.getCidade());
                time.setEstado(a.getEstado());
                return time;
            }
        }
        return null;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id) {
        Time a = null;
        for (Time time : times) {
            if (time.getIdTime() == id.get()) {
                a = time;
                break;
            }
        }
        if (a != null) {
            times.remove(a);
        } else {
            throw new WebApplicationException("Professor com id =" + id.get()
                    + "não encontrado!", 404);
        }
        return Response.ok().build();
    }

}
