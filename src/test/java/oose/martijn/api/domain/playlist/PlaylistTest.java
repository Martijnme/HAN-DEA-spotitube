package oose.martijn.api.domain.playlist;

import oose.martijn.api.domain.track.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PlaylistTest {


    private Playlist sut;

    @BeforeEach
    private void setup() {
        sut = new Playlist(1, "Bob", 3);
    }


    @Test
    void getId() {
        assertEquals(1, sut.getId());
    }

    @Test
    void setId() {
        sut.setId(2);
        assertEquals(2, sut.getId());
    }

    @Test
    void getName() {
        assertEquals("Bob", sut.getName());
    }

    @Test
    void setName() {
        sut.setName("John");
        assertEquals("John", sut.getName());
    }

    @Test
    void getTracks() {
        assertEquals(new ArrayList<>(), sut.getTracks());
    }

    @Test
    void setTracks() {
        List<Track> track = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            track.add(new Track(1, "performer", "title", 10, 0, "album", new Date(12 - 12 - 1980), "description", false));
        }
        sut.setTracks(track);
        assertEquals(5,sut.getTracks().size());
    }

    @Test
    void isOwner() {
        assertEquals(false,sut.isOwner());
    }

    @Test
    void setOwner() {
        sut.setOwner(true);
        assertEquals(true,sut.isOwner());
    }

    @Test
    void getOwnerId() {
        assertEquals(3,sut.getOwnerId());
    }

    @Test
    void setOwnerId() {
        sut.setOwnerId(15);
        assertEquals(15, sut.getOwnerId());
    }
}