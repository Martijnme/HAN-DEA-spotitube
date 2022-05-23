package oose.martijn.api.domain.impl.data;

import oose.martijn.api.domain.track.Track;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TrackDAOTest {

    @Spy
    private TrackDAO sut;

    @Test
//  getTrackNotInPlaylist does the same different query
    void getAllTracksOfPlaylist() {
        doReturn(new ArrayList<>()).when(sut).getTracksOfDatabase(any(Integer.class), any(String.class));

        List<Track> acList = sut.getAllTracksOfPlaylist(1);

        assertNotNull(acList);
    }

    @Test
//  deleteTrackInPlaylist does the same different query
    void addTrackToPlaylist() {
        doReturn(new ArrayList<>()).when(sut).mutateTrackData(any(Integer.class),any(Integer.class),any(Boolean.class), any(String.class));

        List<Track> acList = sut.addTrackToPlaylist(1,1,false);

        assertNotNull(acList);
    }
}