package oose.martijn.api.domain.track.service;

import oose.martijn.api.Test_Util.MockData;
import oose.martijn.api.domain.impl.data.TrackDAO;
import oose.martijn.api.domain.impl.service.TrackService;
import oose.martijn.api.domain.track.presentation.TrackResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackServiceTest {

    @InjectMocks
    private TrackService sut;

    @Mock
    private TrackDAO mockedTrackDAO;

    private MockData mockData = new MockData();

    @BeforeEach
    void setup(){
        sut = new TrackService();
        sut.trackDAO = mockedTrackDAO;
    }
    @Test
    void getTracksOnPlaylist() {
        TrackResponse trackResponse = mockData.trackResponse();
        when(mockedTrackDAO.getAllTracksOfPlaylist(1)).thenReturn(mockData.trackList());

        TrackResponse acReponse = sut.getTracksOnPlaylist(1);
        assertEquals(acReponse.getTracks().size(), trackResponse.getTracks().size());
    }

    @Test
    void getTracksNotInPlaylist() {
        TrackResponse trackResponse = mockData.trackResponse();
        when(mockedTrackDAO.getTrackNotInPlaylist(1)).thenReturn(mockData.trackList());

        TrackResponse acReponse = sut.getTracksNotInPlaylist(1);
        assertEquals(acReponse.getTracks().size(), trackResponse.getTracks().size());
    }

    @Test
    void addTrackToPlaylist() {
        TrackResponse trackResponse = mockData.trackResponse();
        when(mockedTrackDAO.addTrackToPlaylist(1,1,false)).thenReturn(mockData.trackList());

        TrackResponse acReponse = sut.addTrackToPlaylist(1,1,false);
        assertEquals(acReponse.getTracks().size(), trackResponse.getTracks().size());
    }

    @Test
    void deleteTrackFromPlaylist() {
        TrackResponse trackResponse = mockData.trackResponse();
        when(mockedTrackDAO.deleteTrackInPlaylist(1,1)).thenReturn(mockData.trackList());

        TrackResponse acReponse = sut.deleteTrackFromPlaylist(1,1);
        assertEquals(acReponse.getTracks().size(), trackResponse.getTracks().size());
    }
}