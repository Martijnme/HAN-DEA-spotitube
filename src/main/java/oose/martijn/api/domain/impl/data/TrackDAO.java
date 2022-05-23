package oose.martijn.api.domain.impl.data;

import oose.martijn.api.domain.track.Track;
import oose.martijn.api.domain.track.data.ITrackDAO;
import oose.martijn.api.exceptions.SpotitubeExeption;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static oose.martijn.api.config.SqlQueries.*;

public class TrackDAO implements ITrackDAO {

    @Inject
    private DatabaseConnection databaseConnection;

    @Override
    public List<Track> getAllTracksOfPlaylist(int playlistId) {
        return getTracksOfDatabase(playlistId, SQL_SELECT_TRACKS_IN_PALYLIST);
    }

    @Override
    public List<Track> getTrackNotInPlaylist(int playlistId) {
        return getTracksOfDatabase(playlistId, SQL_GET_TRACKS_FOR_PLAYLIST);
    }

    @Override
    public List<Track> addTrackToPlaylist(int playlistId, int trackId, boolean offlineAvailable) {
        return mutateTrackData(playlistId, trackId, offlineAvailable, SQL_INSERT_TRACK_IN_PLAYLIST);
    }

    @Override
    public List<Track> deleteTrackInPlaylist(int playlistId, int trackId) {
        return mutateTrackData(playlistId, trackId, false, SQL_DELETE_TRACK_IN_PLAYLIST);
    }

    public List<Track> mutateTrackData(int playlistId, int trackId, boolean offlineAvailable, String query) {
        try (
                Connection conn = databaseConnection.Connect();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, playlistId);
            ps.setInt(2, trackId);
            if (query == SQL_INSERT_TRACK_IN_PLAYLIST) ps.setBoolean(3, offlineAvailable);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getAllTracksOfPlaylist(playlistId);
    }

    public List<Track> getTracksOfDatabase(int playlistId, String query) {
        List<Track> availableTracks = new ArrayList<>();
        try (
                Connection conn = databaseConnection.Connect();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, playlistId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("performer") == null)
                    continue;
                if (query == SQL_GET_TRACKS_FOR_PLAYLIST)
                    availableTracks.add(new Track(rs.getInt("trackId"), rs.getString("performer"), rs.getString("titel"), rs.getString("album"), rs.getString("beschrijving")));
                else
                    availableTracks.add(new Track(rs.getInt("trackId"), rs.getString("performer"), rs.getString("titel"), rs.getInt("afspeelduur"), rs.getInt("playcount"), rs.getString("album"), rs.getDate("publicatiedatum"), rs.getString("beschrijving"), rs.getBoolean("offlineloading")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpotitubeExeption();
        }
        return availableTracks;
    }
}
