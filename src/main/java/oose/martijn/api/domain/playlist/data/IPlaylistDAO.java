package oose.martijn.api.domain.playlist.data;

import oose.martijn.api.domain.playlist.Playlist;

import java.util.List;

public interface IPlaylistDAO {
    List<Playlist> getAllPlayLists();

    void deletePlaylist(int playlistId);

    void addPlaylist(String name, int userId) ;

    void editPlaylist(String name, int listId) ;
}
