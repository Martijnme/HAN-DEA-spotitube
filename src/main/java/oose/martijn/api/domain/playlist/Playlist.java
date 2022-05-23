package oose.martijn.api.domain.playlist;


import oose.martijn.api.domain.track.Track;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private boolean owner;
    private int ownerId;
    private List<Track> tracks;

    public Playlist(int id, String name, int ownerId){
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.tracks = new ArrayList<>();
    }

    public Playlist() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
