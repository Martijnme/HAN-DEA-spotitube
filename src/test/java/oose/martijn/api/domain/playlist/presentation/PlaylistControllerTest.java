package oose.martijn.api.domain.playlist.presentation;

import oose.martijn.api.Test_Util.MockData;
import oose.martijn.api.domain.playlist.service.IPlaylistService;
import oose.martijn.api.domain.track.service.ITrackService;
import oose.martijn.api.domain.user.service.IUserService;
import oose.martijn.api.exceptions.NullUserExeption;
import oose.martijn.api.exceptions.SpotitubeExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistControllerTest {
    private final String tokenId = "token";

    private MockData mockData = new MockData();
    @Spy
    @InjectMocks
    private PlaylistController sut;
    @Mock
    private IUserService mockedUserService;
    @Mock
    private IPlaylistService mockedPlaylistService;
    @Mock
    private ITrackService mockedTrackService;

    @BeforeEach
    void setup() {
        sut.userService = mockedUserService;
        sut.playlistService = mockedPlaylistService;
        sut.trackService = mockedTrackService;
    }

    @Test
    void getAllPlaylists() {
        when(mockedUserService.getUserOnToken(tokenId)).thenReturn(mockData.user());
        when(mockedPlaylistService.playlists(mockedUserService.getUserOnToken(tokenId).getUserId())).thenReturn(mockData.playlistResponse());

        Response acResponse = sut.getAllPlaylists(tokenId);
        verify(mockedPlaylistService).playlists(mockedUserService.getUserOnToken(tokenId).getUserId());
        assertEquals(Response.Status.OK.getStatusCode(), acResponse.getStatus());
    }

    @Test
    void getAllPlaylistsInvalidUser() {
        doThrow(NullUserExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.getAllPlaylists(tokenId);

        assertEquals(403, acR.getStatus());
    }

    @Test
    void getAllPlaylistsGeneralExeption() {
        doThrow(SpotitubeExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.getAllPlaylists(tokenId);

        assertEquals(400, acR.getStatus());
    }

    @Test
    void insertPlaylist() {
        when(mockedUserService.getUserOnToken(tokenId)).thenReturn(mockData.user());
        when(mockedPlaylistService.insertPlaylist("Pop", mockedUserService.getUserOnToken(tokenId).getUserId())).thenReturn(mockData.playlistResponse());

        var acResponse = sut.insertPlaylist(tokenId, mockData.playlistRequest());
        verify(mockedPlaylistService).insertPlaylist(mockData.playlistRequest().getName(), mockData.playlistRequest().getId());
        assertEquals(Response.Status.OK.getStatusCode(), acResponse.getStatus());
    }

    @Test
    void insertPlaylistInvalidUser() {
        doThrow(NullUserExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.insertPlaylist(tokenId, mockData.playlistRequest());

        assertEquals(403, acR.getStatus());
    }

    @Test
    void insertPlaylistGeneralExeption() {
        doThrow(SpotitubeExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.insertPlaylist(tokenId, mockData.playlistRequest());

        assertEquals(400, acR.getStatus());
    }

    @Test
    void updatePlaylist() {
        when(mockedUserService.getUserOnToken(tokenId)).thenReturn(mockData.user());
        when(mockedPlaylistService.updatePlaylist(1, "Pop", mockedUserService.getUserOnToken(tokenId).getUserId())).thenReturn(mockData.playlistResponse());

        var acResponse = sut.updatePlaylist(tokenId, mockData.playlistRequest());
        verify(mockedPlaylistService).updatePlaylist(mockData.playlistRequest().getId(), mockData.playlistRequest().getName(), mockedUserService.getUserOnToken(tokenId).getUserId());
        assertEquals(Response.Status.OK.getStatusCode(), acResponse.getStatus());
    }

    @Test
    void updatePlaylistInvalidUser() {
        doThrow(NullUserExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.updatePlaylist(tokenId, mockData.playlistRequest());

        assertEquals(403, acR.getStatus());
    }

    @Test
    void updatePlaylistGeneralExeption() {
        doThrow(SpotitubeExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.updatePlaylist(tokenId, mockData.playlistRequest());

        assertEquals(400, acR.getStatus());
    }

    @Test
    void getAllTracksFromPlaylist() {
        when(mockedUserService.getUserOnToken(tokenId)).thenReturn(mockData.user());
        when(mockedTrackService.getTracksOnPlaylist(1)).thenReturn(mockData.trackResponse());

        var acResponse = sut.getAllTracksFromPlaylist(1, tokenId);
        verify(mockedTrackService).getTracksOnPlaylist(mockData.trackRequest().getId());
        assertEquals(Response.Status.OK.getStatusCode(), acResponse.getStatus());
    }

    @Test
    void getAllTracksFromPlaylistInvalidUser() {
        doThrow(NullUserExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.getAllTracksFromPlaylist(1, tokenId);

        assertEquals(403, acR.getStatus());
    }

    @Test
    void getAllTracksFromPlaylistGeneralExeption() {
        doThrow(SpotitubeExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.getAllTracksFromPlaylist(1, tokenId);

        assertEquals(400, acR.getStatus());
    }

    @Test
    void addTrackToPlaylist() {
        when(mockedUserService.getUserOnToken(tokenId)).thenReturn(mockData.user());
        when(mockedTrackService.addTrackToPlaylist(1, 1, false)).thenReturn(mockData.trackResponse());

        var acResponse = sut.addTrackToPlaylist(1, tokenId, mockData.trackRequest());
        verify(mockedTrackService).addTrackToPlaylist(1, 1, false);
        assertEquals(Response.Status.OK.getStatusCode(), acResponse.getStatus());
    }

    @Test
    void addTrackToPlaylistInvalidUser() {
        doThrow(NullUserExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.addTrackToPlaylist(1, tokenId, mockData.trackRequest());

        assertEquals(403, acR.getStatus());
    }

    @Test
    void addTrackToPlaylistGeneralExeption() {
        doThrow(SpotitubeExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.addTrackToPlaylist(1, tokenId, mockData.trackRequest());

        assertEquals(400, acR.getStatus());
    }

    @Test
    void deletePlaylist() {
        when(mockedUserService.getUserOnToken(tokenId)).thenReturn(mockData.user());
        when(mockedPlaylistService.deletePlaylist(1, mockedUserService.getUserOnToken(tokenId).getUserId())).thenReturn(mockData.playlistResponse());

        var acResponse = sut.deletePlaylist(1, tokenId);
        verify(mockedPlaylistService).deletePlaylist(1, mockedUserService.getUserOnToken(tokenId).getUserId());
        assertEquals(Response.Status.OK.getStatusCode(), acResponse.getStatus());
    }

    @Test
    void deletePlaylistInvalidUser() {
        doThrow(NullUserExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.deletePlaylist(1, tokenId);

        assertEquals(403, acR.getStatus());
    }

    @Test
    void deletePlaylistGeneralExeption() {
        doThrow(SpotitubeExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.deletePlaylist(1, tokenId);

        assertEquals(400, acR.getStatus());
    }

    @Test
    void deleteTrackFromPlaylist() {
        when(mockedUserService.getUserOnToken(tokenId)).thenReturn(mockData.user());
        when(mockedTrackService.deleteTrackFromPlaylist(1, mockedUserService.getUserOnToken(tokenId).getUserId())).thenReturn(mockData.trackResponse());

        var acResponse = sut.deleteTrackFromPlaylist(1, 1, tokenId);
        verify(mockedTrackService).deleteTrackFromPlaylist(1, 1);
        assertEquals(Response.Status.OK.getStatusCode(), acResponse.getStatus());
    }

    @Test
    void deleteTrackFromPlaylistInvalidUser() {
        doThrow(NullUserExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.deleteTrackFromPlaylist(1, 1, tokenId);

        assertEquals(403, acR.getStatus());
    }

    @Test
    void deleteTrackFromPlaylistGeneralExeption() {
        doThrow(SpotitubeExeption.class).when(sut).validateUser(any(String.class));

        Response acR = sut.deleteTrackFromPlaylist(1, 1, tokenId);

        assertEquals(400, acR.getStatus());
    }
}