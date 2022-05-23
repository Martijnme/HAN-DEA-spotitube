package oose.martijn.api.domain.track.data;

import oose.martijn.api.domain.track.Track;

import java.util.List;


public interface ITrackDAO {

    List<Track> getAllTracksOfPlaylist(int playlistId);

    List<Track> getTrackNotInPlaylist(int playlistId);


    List<Track> addTrackToPlaylist(int playlistId, int trackId, boolean offlineAvailable);

    List<Track> deleteTrackInPlaylist(int playlistId, int trackId);


}
