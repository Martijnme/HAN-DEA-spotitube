package oose.martijn.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class main {

    @GET
    public Response serverStart(){
        return Response.ok().entity("Spotitube API is running").build();
    }
}
