package oose.martijn.api.domain.impl.data;

import oose.martijn.api.config.IDatabaseConnection;
import oose.martijn.api.domain.playlist.Playlist;
import oose.martijn.api.domain.playlist.data.IPlaylistDAO;
import oose.martijn.api.exceptions.SpotitubeExeption;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static oose.martijn.api.config.SqlQueries.*;

public class PlaylistDAO implements IPlaylistDAO {

    @Inject
    private IDatabaseConnection databaseConnection;

    @Override
    public List<Playlist> getAllPlayLists() {
        List<Playlist> playlistArray = new ArrayList<>();
        try (
                Connection conn = databaseConnection.Connect();
        ) {
            PreparedStatement ps = conn.prepareStatement(SQL_SELECT_PLAYLISTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Playlist playlist = new Playlist(rs.getInt("listId"), rs.getString("name"), rs.getInt("userId"));
                playlistArray.add(playlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpotitubeExeption();
        }
        return playlistArray;
    }

    @Override
    public void deletePlaylist(int playlistId) {
        try (
                Connection conn = databaseConnection.Connect();
                PreparedStatement psPlaylistTrack = conn.prepareStatement(SQL_DELETE_PLAYLIST_TRACK);
                PreparedStatement psPlaylist = conn.prepareStatement(SQL_DELETE_PLAYLIST);
        ) {
            psPlaylistTrack.setInt(1, playlistId);
            psPlaylistTrack.execute();
            psPlaylist.setInt(1, playlistId);
            psPlaylist.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpotitubeExeption();
        }
    }

    @Override
    public void addPlaylist(String name, int userId) {
        this.mutatePlaylistData(name, userId, SQL_INSERT_PLAYLIST);
    }

    @Override
    public void editPlaylist(String name, int listId) {
        this.mutatePlaylistData(name, listId, SQL_UPDATE_PLAYLIST);
    }

    private void mutatePlaylistData(String name, int listId, String query) {
        try (
                Connection conn = new DatabaseConnection().Connect();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, name);
            ps.setInt(2, listId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpotitubeExeption();
        }
    }
}
