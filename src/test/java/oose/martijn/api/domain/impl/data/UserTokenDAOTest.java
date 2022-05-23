package oose.martijn.api.domain.impl.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserTokenDAOTest {

    @Spy
    private UserTokenDAO sut;

    @Test
    void getUserIdOnTokenId() {
        doReturn(1).when(sut).getUserId(any(String.class), any(Integer.class));

        int acUserId = sut.getUserIdOnTokenId("token");

        assertNotNull(acUserId);
    }

    @Test
    void setUserToken() {
        doReturn("token").when(sut).setToken(any(Integer.class), any(String.class));

        String token = sut.setUserToken(1);

        assertNotNull(token);
    }
}