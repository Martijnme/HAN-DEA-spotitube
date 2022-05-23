package oose.martijn.api.domain.track.presentation;

import oose.martijn.api.domain.track.service.ITrackService;
import oose.martijn.api.domain.user.service.IUserService;
import oose.martijn.api.exceptions.NullUserExeption;
import oose.martijn.api.exceptions.SpotitubeExeption;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    @Inject
    public ITrackService trackService;
    @Inject
    public IUserService userService;

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("token") String tokenId, @QueryParam("forPlaylist") int playlistId) {
        try {
            userService.getUserOnToken(tokenId);
            TrackResponse trackResponse = trackService.getTracksNotInPlaylist(playlistId);
            return Response.status(Response.Status.OK).entity(trackResponse).build();
        } catch (NullUserExeption e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }catch (SpotitubeExeption e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
