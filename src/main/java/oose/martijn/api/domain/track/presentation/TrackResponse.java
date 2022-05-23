package oose.martijn.api.domain.track.presentation;

import oose.martijn.api.domain.track.Track;

import java.util.List;

public class TrackResponse {
    private List<Track> tracks;

    public TrackResponse(List<Track> tracks){
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
