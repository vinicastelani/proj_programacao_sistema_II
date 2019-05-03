package mack.projeto.ps2;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/contas-bancarias")
@Produces(MediaType.APPLICATION_JSON)
public class ContaBancariaResource {
    private ContaBancariaDAO dao;
    
    public ContaBancariaResource(ContaBancariaDAO dao) {
        this.dao = dao;
    }
    
    @GET
    public List<ContaBancaria> read() {
        return dao.read();
    }
    
    @POST
    public ContaBancaria create(ContaBancaria p) {
        return this.dao.create(p);
    }
    
    @GET
    @Path("{id}")
    public ContaBancaria readOne(@PathParam("id") LongParam id) {
        long idContaBancaria = id.get();
        // Precisa implementar no DAO
        return null;
    }
    
//
//    @PUT
//    @Path("{id}")
//    public ContaBancaria update(@PathParam("id") LongParam id, ContaBancaria p) {
//        for (ContaBancaria professor: professores) {
//            if (professor.getId() == id.get()) {
//                professor.setNome(p.getNome());
//                professor.setMatricula(p.getMatricula());
//                return professor;
//            }
//        }
//        return null;
//    }
//    
//    @DELETE
//    @Path("{id}")
//    public Response delete(@PathParam("id") LongParam id) {
//        ContaBancaria p = null;
//        for (ContaBancaria professor: professores) {
//            if (professor.getId() == id.get()) {
//                p = professor;
//                break;
//            }
//        }
//        if (p != null) { 
//            professores.remove(p); 
//        }
//        else {
//            throw new WebApplicationException("ContaBancaria com id=" + id.get() 
//                                              + " não encontrado!", 404);
//        }
//        return Response.ok().build();
//    }
}
