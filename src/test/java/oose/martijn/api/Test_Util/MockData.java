package oose.martijn.api.Test_Util;

import oose.martijn.api.domain.playlist.Playlist;
import oose.martijn.api.domain.playlist.presentation.PlaylistRequest;
import oose.martijn.api.domain.playlist.presentation.PlaylistResponse;
import oose.martijn.api.domain.track.Track;
import oose.martijn.api.domain.track.presentation.TrackRequest;
import oose.martijn.api.domain.track.presentation.TrackResponse;
import oose.martijn.api.domain.user.User;
import oose.martijn.api.domain.user.presentation.UserRequest;
import oose.martijn.api.domain.user.presentation.UserResponse;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MockData {


    public PlaylistResponse playlistResponse() {
        return new PlaylistResponse(this.playlistList(), 10);
    }

    public TrackResponse trackResponse() {
        return new TrackResponse(this.trackList());
    }

    public UserResponse userResponse() {
        return new UserResponse("admin admin", "c519e527-634f-4838-a8ba-d0c70c76c7de");
    }

    public User user() {
        return new User(1, "bob", "2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b", "admin", "admin");
    }

    public PlaylistRequest playlistRequest() {
        PlaylistRequest playlistRequest = new PlaylistRequest();
        playlistRequest.setId(1);
        playlistRequest.setName("Pop");
        return playlistRequest;
    }

    public UserRequest userRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUser("admin");
        userRequest.setPassword("admin");
        return userRequest;
    }

    public TrackRequest trackRequest() {
        TrackRequest trackRequest = new TrackRequest();
        trackRequest.setId(1);
        trackRequest.setOfflineAvailable(false);
        return trackRequest;
    }

    public List<Playlist> playlistList() {
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist(1, "Pop", 1));
        playlists.add(new Playlist(2, "Rock", 1));
        playlists.add(new Playlist(3, "Rap", 1));
        playlists.add(new Playlist(4, "Pop", 1));
        playlists.add(new Playlist(5, "Rock", 1));
        playlists.add(new Playlist(6, "Rap", 1));

        return playlists;
    }

    public List<Track> trackList() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track(1, "John doe", "Taxes blues", 1, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false));
        tracks.add(new Track(2, "John doe", "Taxes blues", 2, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false));
        tracks.add(new Track(3, "John doe", "Taxes blues", 2, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false));
        tracks.add(new Track(4, "John doe", "Taxes blues", 4, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false));
        tracks.add(new Track(5, "John doe", "Taxes blues", 0, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false));
        tracks.add(new Track(6, "John doe", "Taxes blues", 1, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false));
        return tracks;
    }

    public List<Track> tracksOfPlaylist() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track(1, "John doe", "Taxes blues", 345, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false));
        tracks.add(new Track(2, "John doe", "Taxes blues", 345, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false));
        tracks.add(new Track(3, "John doe", "Taxes blues", 345, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false));
        return tracks;
    }

    public List<Playlist> playlistWithTracks() {
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist(1, "Pop", 1));
        playlists.add(new Playlist(2, "Rock", 2));

        for (Playlist play : playlists) {
            play.setOwner(play.getOwnerId() == 1);
            play.setTracks(tracksOfPlaylist());
        }
        return playlists;
    }

    public List<Playlist> shortPlaylist() {
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist(1, "Pop", 1));
        playlists.add(new Playlist(2, "Rock", 2));
        return playlists;
    }

    public Track track() {
        return new Track(1, "John doe", "Taxes blues", 345, 4, "Billy joel", new Date(01 - 01 - 2020), "description", false);
    }
}
