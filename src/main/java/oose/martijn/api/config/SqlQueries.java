package oose.martijn.api.config;

public class SqlQueries {
    //    R of Models
    public static final String SQL_SELECT_PLAYLISTS = "SELECT listid, name, userId FROM PLAYLIST";
    //    USER R*3
    public static final String SQL_SELECT_USER_ON_TOKENID = "SELECT userId FROM TOKEN where tokenId = ?";
    public static final String SQL_SELECT_USER_ON_USERID = "SELECT userId, username, password, firstname, lastname FROM USER WHERE userId = ?";
    public static final String SQL_SELECT_USER_ON_USERNAME = "SELECT userId, username, password, firstname, lastname FROM USER WHERE username = ?";

    //    TOKEN CRU
    public static final String SQL_INSERT_TOKEN = "INSERT INTO TOKEN (tokenId, userId) VALUES(?,?)";
    public static final String SQL_SELECT_TOKEN_ON_USERID = "SELECT tokenId FROM TOKEN where userId = ?";
    public static final String SQL_UPDATE_TOKEN_ON_USERID = "UPDATE TOKEN SET tokenId = ? where userId = ?";

    //    PLAYLIST CUD
    public static final String SQL_INSERT_PLAYLIST = "INSERT INTO PLAYLIST(name, userId) VALUES(?,?)";
    public static final String SQL_UPDATE_PLAYLIST = "UPDATE PLAYLIST SET name = ? where listId = ?";
    public static final String SQL_DELETE_PLAYLIST = "DELETE FROM PLAYLIST WHERE listId = ?";

    //    PLAYLIST_TRACK D
    public static final String SQL_DELETE_PLAYLIST_TRACK = "DELETE FROM PLAYLIST_TRACK WHERE playlistId = ?";
    //    TRACK in PLAYLIST CRD
    public static final String SQL_SELECT_TRACKS_IN_PALYLIST = "SELECT t.*, pt.offlineloading FROM TRACK as t JOIN PLAYLIST_TRACK as pt ON pt.trackId = t.trackId WHERE pt.playlistId = ? ";
    public static final String SQL_GET_TRACKS_FOR_PLAYLIST = "SELECT t.* from TRACK as t where trackId NOT IN (SELECT TRACK.trackId from TRACK JOIN PLAYLIST_TRACK ON TRACK.trackId = PLAYLIST_TRACK.trackId where PLAYLIST_TRACK.playlistId = ? )";
    public static final String SQL_INSERT_TRACK_IN_PLAYLIST = "INSERT INTO PLAYLIST_TRACK(playlistId, trackId, offlineloading) VALUES(?,?,?)";
    public static final String SQL_DELETE_TRACK_IN_PLAYLIST = "DELETE FROM PLAYLIST_TRACK WHERE playlistId = ? AND trackId = ?";
}