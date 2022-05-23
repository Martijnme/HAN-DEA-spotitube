package oose.martijn.api.domain.impl.data;

import oose.martijn.api.domain.user.User;
import oose.martijn.api.exceptions.NullUserExeption;
import oose.martijn.api.exceptions.SpotitubeExeption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDAOTest {

    @Spy
    private UserDAO sut;

    @Test
    void getUserByUserId() {
        doReturn(new User()).when(sut).getUser(1,null);
        User acUser = sut.getUserByUserId(1);
        verify(sut).getUserByUserId(1);
        assertNotNull(acUser);
    }
    @Test
    void getUserByUserIdNullUser() {
        doReturn(null).when(sut).getUser(1,null);
        assertThrows(NullUserExeption.class, () -> sut.getUserByUserId(1));
    }
    @Test
    void getUserByUsername() {
        doReturn(new User()).when(sut).getUser("Bob",null);
        User acUser = sut.getUserByUsername("Bob");
        assertNotNull(acUser);
    }
    @Test
    void getUserByUsernameNullUser() {
        doReturn(null).when(sut).getUser("Bob",null);
        assertThrows(NullUserExeption.class, () -> sut.getUserByUsername("Bob"));
    }
    @Test
    void getUserWithInt() {
        doReturn(new User()).when(sut).getUser(1,null);
        assertNotNull(sut.getUser(1,null));
    }
    @Test
    void getUserWithIntGeneralException() {
        doThrow(SpotitubeExeption.class).when(sut).getUser(1,null);
        assertThrows(SpotitubeExeption.class, () -> sut.getUser(1,null));
    }
    @Test
    void getUserWithString() {
        doReturn(new User()).when(sut).getUser("bob",null);
        assertNotNull(sut.getUser("bob",null));
    }
    @Test
    void getUserWithStringGeneralException() {
        doThrow(SpotitubeExeption.class).when(sut).getUser("bob",null);
        assertThrows(SpotitubeExeption.class, () -> sut.getUser("bob",null));
    }
}