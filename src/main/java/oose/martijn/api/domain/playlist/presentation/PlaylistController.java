package oose.martijn.api.domain.playlist.presentation;

import oose.martijn.api.exceptions.NullUserExeption;
import oose.martijn.api.exceptions.SpotitubeExeption;
import oose.martijn.api.domain.playlist.service.IPlaylistService;
import oose.martijn.api.domain.track.presentation.TrackRequest;
import oose.martijn.api.domain.track.presentation.TrackResponse;
import oose.martijn.api.domain.track.service.ITrackService;
import oose.martijn.api.domain.user.User;
import oose.martijn.api.domain.user.service.IUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    public IUserService userService;
    @Inject
    public IPlaylistService playlistService;
    @Inject
    public ITrackService trackService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String tokenId) {
        try {
            User user = validateUser(tokenId);
            PlaylistResponse playlists = playlistService.playlists(user.getUserId());
            return Response.status(Response.Status.OK).entity(playlists).build();
        } catch (NullUserExeption e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }catch (SpotitubeExeption e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPlaylist(@QueryParam("token") String tokenId, PlaylistRequest params) {
        try {
            User user = validateUser(tokenId);
            PlaylistResponse playlistResponse = playlistService.insertPlaylist(params.getName(), user.getUserId());
            return Response.status(Response.Status.OK).entity(playlistResponse).build();
        } catch (NullUserExeption e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }catch (SpotitubeExeption e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes
    @Produces
    public Response updatePlaylist(@QueryParam("token") String tokenId, PlaylistRequest params) {
        try {
            User user = validateUser(tokenId);
            PlaylistResponse playlistResponse = playlistService.updatePlaylist(params.getId(), params.getName(), user.getUserId());
            return Response.status(Response.Status.OK).entity(playlistResponse).build();
        } catch (NullUserExeption e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (SpotitubeExeption e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksFromPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String tokenId) {
        try {
            validateUser(tokenId);
            TrackResponse trackResponse = trackService.getTracksOnPlaylist(playlistId);
            return Response.status(Response.Status.OK).entity(trackResponse).build();
        } catch (NullUserExeption e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (SpotitubeExeption e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String tokenId, TrackRequest params) {
        try {
            validateUser(tokenId);
            TrackResponse trackResponse = trackService.addTrackToPlaylist(playlistId, params.getId(), params.isOfflineAvailable());
            return Response.status(Response.Status.OK).entity(trackResponse).build();
        } catch (NullUserExeption e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (SpotitubeExeption e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int playlistId, @QueryParam("token") String tokenId) {
        try {
            User user = validateUser(tokenId);
            PlaylistResponse playlistResponse = playlistService.deletePlaylist(playlistId, user.getUserId());
            return Response.status(Response.Status.OK).entity(playlistResponse).build();
        } catch (NullUserExeption e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (SpotitubeExeption e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String tokenId) {
        try {
            validateUser(tokenId);
            TrackResponse trackResponse = trackService.deleteTrackFromPlaylist(playlistId, trackId);
            return Response.status(Response.Status.OK).entity(trackResponse).build();
        } catch (NullUserExeption e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (SpotitubeExeption e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    public User validateUser(String tokenId) {
        return userService.getUserOnToken(tokenId);
    }
}
