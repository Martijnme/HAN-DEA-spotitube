package oose.martijn.api.domain.playlist.service;

import oose.martijn.api.Test_Util.MockData;
import oose.martijn.api.domain.impl.service.PlaylistService;
import oose.martijn.api.domain.playlist.Playlist;
import oose.martijn.api.domain.playlist.data.IPlaylistDAO;
import oose.martijn.api.domain.playlist.presentation.PlaylistResponse;
import oose.martijn.api.domain.track.Track;
import oose.martijn.api.domain.track.data.ITrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceTest {


    @Spy
    @InjectMocks
    private PlaylistService sut;
    @Mock
    private IPlaylistDAO mockedPlaylistDao;
    @Mock
    private ITrackDAO mockedTrackDao;

    private MockData mockData = new MockData();

    @BeforeEach
    void setup() {
        sut.playlistDAO = mockedPlaylistDao;
        sut.trackDAO = mockedTrackDao;
    }

    @Test
    void userPlaylistsTest() {
        List<Playlist> plays = mockData.playlistWithTracks();
        List<Track> tracks = mockData.tracksOfPlaylist();
        when(mockedPlaylistDao.getAllPlayLists()).thenReturn(mockData.shortPlaylist());
        when(mockedTrackDao.getAllTracksOfPlaylist(1)).thenReturn(tracks).thenReturn(tracks);

        List<Playlist> acList = sut.userPlaylists(1);

        verify(mockedPlaylistDao).getAllPlayLists();
        verify(mockedTrackDao).getAllTracksOfPlaylist(1);
        assertEquals(acList.size(), plays.size());
    }

    @Test
    void playlists() {
        PlaylistResponse response = mockData.playlistResponse();
        doReturn(mockData.playlistList()).when(sut).userPlaylists(any(Integer.class));

        PlaylistResponse acResponse = sut.playlists(1);
        assertEquals(acResponse.getPlaylists().size(), response.getPlaylists().size());
    }

    @Test
    void deletePlaylist() {
        sut.deletePlaylist(1, 1);
        verify(mockedPlaylistDao).deletePlaylist(1);
    }

    @Test
    void insertPlaylist() {
        sut.insertPlaylist("Bob", 1);
        verify(mockedPlaylistDao).addPlaylist("Bob", 1);
    }

    @Test
    void updatePlaylist() {
        sut.updatePlaylist(1, "Bob", 1);
        verify(mockedPlaylistDao).editPlaylist("Bob", 1);
    }
}