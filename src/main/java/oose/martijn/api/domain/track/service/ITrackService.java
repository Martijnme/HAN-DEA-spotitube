package oose.martijn.api.domain.track.service;

import oose.martijn.api.domain.track.presentation.TrackResponse;

public interface ITrackService {
    TrackResponse getTracksOnPlaylist(int listId);

    TrackResponse getTracksNotInPlaylist(int listId);

    TrackResponse addTrackToPlaylist(int playlistId, int trackId, boolean offlineloading);

    TrackResponse deleteTrackFromPlaylist(int playlistId, int trackId);
}
