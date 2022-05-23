package oose.martijn.api.domain.impl.service;


import oose.martijn.api.domain.playlist.Playlist;
import oose.martijn.api.domain.playlist.data.IPlaylistDAO;
import oose.martijn.api.domain.playlist.presentation.PlaylistResponse;
import oose.martijn.api.domain.playlist.service.IPlaylistService;
import oose.martijn.api.domain.track.data.ITrackDAO;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements IPlaylistService {

    @Inject
    public IPlaylistDAO playlistDAO;
    @Inject
    public ITrackDAO trackDAO;

    @Override
    public List<Playlist> userPlaylists(int userId) {
        List<Playlist> playlists = playlistDAO.getAllPlayLists();
        for (Playlist play : playlists) {
            play.setOwner(play.getOwnerId() == userId);
            play.setTracks(trackDAO.getAllTracksOfPlaylist(play.getId()));
        }
        return playlists;
    }

    @Override
    public PlaylistResponse playlists(int userId) {
        List<Playlist> playlists = userPlaylists(userId);
        int totalDuration = 0;
        for (Playlist play : playlists) {
            for (int i = 0; i < play.getTracks().size(); i++) {
                totalDuration += play.getTracks().get(i).getDuration();
            }
        }
        return new PlaylistResponse(playlists, totalDuration);
    }

    @Override
    public PlaylistResponse deletePlaylist(int playlistId, int userId) {
        playlistDAO.deletePlaylist(playlistId);
        return playlists(userId);
    }

    @Override
    public PlaylistResponse insertPlaylist(String name, int userId) {
        playlistDAO.addPlaylist(name, userId);
        return playlists(userId);
    }

    @Override
    public PlaylistResponse updatePlaylist(int playlistId, String name, int userId) {
        playlistDAO.editPlaylist(name, playlistId);
        return playlists(userId);
    }

}
