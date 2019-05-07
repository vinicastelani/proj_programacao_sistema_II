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

   // @PUT
  //  @Path("{id}")
   // public Response update(@PathParam("id") LongParam id, Time time) {
   //     if (dao.update(id.get(), time)
  //          return Response.ok().build()
    //    );
   //     throw new WebApplicationException("Erro" + Response.Status.NOT_FOUND);
  //  }

   // @DELETE
 //   @Path("{id}")
  //  public Time deleteTime

  //  {

   // }
}
