package oose.martijn.api.domain.playlist.presentation;


import oose.martijn.api.domain.playlist.Playlist;

import java.util.List;

public class PlaylistResponse {
    private List<Playlist> playlists;
    private int length;

    public PlaylistResponse(List<Playlist> playlists, int length){
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}
