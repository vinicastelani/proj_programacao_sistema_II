package mack.projeto.ps2;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/time")
@Produces(MediaType.APPLICATION_JSON)
public class TimeResource {    

    private TimeDAO dao;
    
    public TimeResource(TimeDAO dao) {
        this.dao = dao;
    }
    
    @GET
    public List<Time> read() {
        return dao.read();
    }
    
    @POST
    public Time create(Time a) {
        return this.dao.create(a);
    }
    
    @GET
    @Path("{id}")
    public Time readOne(@PathParam("id") LongParam id) {
        long idTime = id.get();
        // Precisa implementar no DAO
        return null;
    }
    
//
//    @PUT
//    @Path("{id}")
//    public Professor update(@PathParam("id") LongParam id, Professor p) {
//        for (Professor professor: professores) {
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
//        Professor p = null;
//        for (Professor professor: professores) {
//            if (professor.getId() == id.get()) {
//                p = professor;
//                break;
//            }
//        }
//        if (p != null) { 
//            professores.remove(p); 
//        }
//        else {
//            throw new WebApplicationException("Professor com id=" + id.get() 
//                                              + " não encontrado!", 404);
//        }
//        return Response.ok().build();
//    }
}

