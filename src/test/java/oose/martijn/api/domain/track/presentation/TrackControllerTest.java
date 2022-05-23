package oose.martijn.api.domain.track.presentation;

import oose.martijn.api.Test_Util.MockData;
import oose.martijn.api.domain.track.service.ITrackService;
import oose.martijn.api.domain.user.service.IUserService;
import oose.martijn.api.exceptions.NullUserExeption;
import oose.martijn.api.exceptions.SpotitubeExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackControllerTest {

    private MockData mockData = new MockData();

    @InjectMocks
    private TrackController sut;
    @Mock
    private ITrackService mockedTrackService;
    @Mock
    private IUserService mockedUserService;
    private final String tokenId = "c519e527-634f-4838-a8ba-d0c70c76c7de";
    @BeforeEach
    public void setup() {
        sut = new TrackController();
        sut.userService = mockedUserService;
        sut.trackService = mockedTrackService;
    }

    @Test
    void getAllTracks() {
        when(mockedUserService.getUserOnToken(tokenId)).thenReturn(mockData.user());
        when(mockedTrackService.getTracksNotInPlaylist(1)).thenReturn(mockData.trackResponse());

        Response acResponse = sut.getAllTracks(tokenId,1);
        verify(mockedTrackService).getTracksNotInPlaylist(1);
        assertEquals(Response.Status.OK.getStatusCode(), acResponse.getStatus());
    }
    @Test
    void getAllTracksInvalidUser() {
        when(mockedUserService.getUserOnToken(any(String.class))).thenThrow(NullUserExeption.class);

        Response acR = sut.getAllTracks(tokenId,1);

        assertEquals(403, acR.getStatus());
    }

    @Test
    void getAllTracksGeneralExeption() {
        when(mockedUserService.getUserOnToken(any(String.class))).thenThrow(SpotitubeExeption.class);

        Response acR = sut.getAllTracks(tokenId,1);

        assertEquals(400, acR.getStatus());
    }
}