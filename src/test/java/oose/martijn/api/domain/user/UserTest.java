package oose.martijn.api.domain.user;

import oose.martijn.api.Test_Util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private User sut;
    private MockData mockData = new MockData();

    @BeforeEach
    void setup() {
        sut = mockData.user();
    }

    @Test
    void getUsername() {
        assertEquals("bob", sut.getUsername());
    }

    @Test
    void setUsername() {
        sut.setUsername("otherBob");
        assertEquals("otherBob", sut.getUsername());
    }

    @Test
    void getUserId() {
        assertEquals(1, sut.getUserId());
    }

    @Test
    void setUserId() {
        sut.setUserId(1001);
        assertEquals(1001, sut.getUserId());
    }

    @Test
    void getFirstname() {
        assertEquals("admin", sut.getFirstname());
    }

    @Test
    void setFirstname() {
        sut.setFirstname("bob");
        assertEquals("bob", sut.getFirstname());
    }

    @Test
    void getLastname() {
        assertEquals("admin", sut.getLastname());
    }

    @Test
    void setLastname() {
        sut.setLastname("another");
        assertEquals("another", sut.getLastname());
    }
}