package oose.martijn.api.domain.user.presentation;

import oose.martijn.api.exceptions.InvalidLoginUser;
import oose.martijn.api.exceptions.NullUserExeption;
import oose.martijn.api.exceptions.SpotitubeExeption;
import oose.martijn.api.domain.user.service.IUserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class UserController {

    @Inject
    public IUserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userLogin(UserRequest params) {
        try {
            UserResponse userResponse = userService.loginUser(params.getUser(), params.getPassword());
            return Response.status(Response.Status.OK).entity(userResponse).build();
        } catch (InvalidLoginUser | NullUserExeption e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (SpotitubeExeption e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
