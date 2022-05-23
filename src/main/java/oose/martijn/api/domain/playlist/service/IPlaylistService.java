package oose.martijn.api.domain.playlist.service;

import oose.martijn.api.domain.playlist.Playlist;
import oose.martijn.api.domain.playlist.presentation.PlaylistResponse;

import java.util.List;

public interface IPlaylistService {

    List<Playlist> userPlaylists(int userId);

    PlaylistResponse playlists(int userId);

    PlaylistResponse deletePlaylist(int playlistId, int userId);

    PlaylistResponse insertPlaylist(String name, int userId);

    PlaylistResponse updatePlaylist(int id, String name, int userId);

}
