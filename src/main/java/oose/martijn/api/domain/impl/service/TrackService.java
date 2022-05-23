package oose.martijn.api.domain.impl.service;

import oose.martijn.api.domain.track.data.ITrackDAO;
import oose.martijn.api.domain.track.presentation.TrackResponse;
import oose.martijn.api.domain.track.service.ITrackService;

import javax.inject.Inject;

public class TrackService implements ITrackService {
    @Inject
    public ITrackDAO trackDAO;

    @Override
    public TrackResponse getTracksOnPlaylist(int listId) {
        return new TrackResponse(trackDAO.getAllTracksOfPlaylist(listId));
    }

    @Override
    public TrackResponse getTracksNotInPlaylist(int listId) {
        return new TrackResponse(trackDAO.getTrackNotInPlaylist(listId));
    }

    @Override
    public TrackResponse addTrackToPlaylist(int playlistId, int trackId, boolean offlineloading) {
        return new TrackResponse(trackDAO.addTrackToPlaylist(playlistId, trackId, offlineloading));
    }

    @Override
    public TrackResponse deleteTrackFromPlaylist(int playlistId, int trackId) {
        return new TrackResponse(trackDAO.deleteTrackInPlaylist(playlistId, trackId));
    }
}
