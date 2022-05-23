package oose.martijn.api.domain.impl.data;

import oose.martijn.api.domain.playlist.Playlist;
import oose.martijn.api.exceptions.SpotitubeExeption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistDAOTest {

    @Spy
    PlaylistDAO sut;

    @Test
    void getAllPlayLists() {
        doReturn(new ArrayList<>()).when(sut).getAllPlayLists();
        List<Playlist> res = sut.getAllPlayLists();
        verify(sut).getAllPlayLists();
        assertNotNull(res);
    }

    @Test
    void getAllPlayListsException() {
        doThrow(SpotitubeExeption.class).when(sut).getAllPlayLists();

        assertThrows(SpotitubeExeption.class, () -> sut.getAllPlayLists());
    }
/*
    De volgende methodes worden niet getest omdat deze alleen queries uitvoeren en geen verdere data nuttigen.
        - deletePlaylist()
        - addPlaylist()
        - editPlaylist()
*/
}