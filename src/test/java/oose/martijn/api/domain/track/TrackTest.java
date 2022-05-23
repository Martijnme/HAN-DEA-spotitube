package oose.martijn.api.domain.track;

import oose.martijn.api.Test_Util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TrackTest {

    private Track sut;

    private MockData mockData = new MockData();
    @BeforeEach
    private void setup(){
        sut = mockData.track();
    }
    @Test
    void getid() {
        assertEquals(1,sut.getid());
    }

    @Test
    void setid() {
        sut.setid(3);
        assertEquals(3,sut.getid());
    }

    @Test
    void getTitle() {
        assertEquals("Taxes blues",sut.getTitle());
    }

    @Test
    void setTitle() {
        sut.setTitle("title");
        assertEquals("title",sut.getTitle());
    }

    @Test
    void getPerformer() {
        assertEquals("John doe", sut.getPerformer());
    }

    @Test
    void setPerformer() {
        sut.setPerformer("performer");
        assertEquals("performer",sut.getPerformer());
    }

    @Test
    void getDuration() {
        assertEquals(345,sut.getDuration());
    }

    @Test
    void setDuration() {
        sut.setDuration(1001);
        assertEquals(1001,sut.getDuration());
    }

    @Test
    void getAlbum() {
        assertEquals("Billy joel",sut.getAlbum());
    }

    @Test
    void setAlbum() {
        sut.setAlbum("album");
        assertEquals("album",sut.getAlbum());
    }

    @Test
    void getPublicationDate() {
        assertEquals("01-01-1970",sut.getPublicationDate());
    }

    @Test
    void setPublicationDate() {
        sut.setPublicationDate("20-20-2020");
        assertEquals("20-20-2020",sut.getPublicationDate());
    }

    @Test
    void getDescription() {
        assertEquals("description",sut.getDescription());
    }

    @Test
    void setDescription() {
        sut.setDescription("otherDescription");
        assertEquals("otherDescription",sut.getDescription());
    }

    @Test
    void getPlaycount() {
        assertEquals(0,sut.getPlaycount());
    }

    @Test
    void setPlaycount() {
        sut.setPlaycount(1001);
        assertEquals(1001,sut.getPlaycount());
    }

    @Test
    void isOfflineAvailable() {
        assertEquals(false,sut.isOfflineAvailable());
    }

    @Test
    void setOfflineAvailable() {
        sut.setOfflineAvailable(true);
        assertEquals(true,sut.isOfflineAvailable());
    }
}