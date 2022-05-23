package oose.martijn.api.domain.user.service;

import oose.martijn.api.domain.impl.service.UserTokenService;
import oose.martijn.api.domain.user.data.IUserTokenDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTokenServiceTest {

    @InjectMocks
    private UserTokenService sut;
    @Mock
    private IUserTokenDAO mockedUserTokenDAO;

    @BeforeEach
    void setup(){
        sut = new UserTokenService();
        sut.userTokenDAO = mockedUserTokenDAO;
    }
    @Test
    void setToken() {
        when(mockedUserTokenDAO.setUserToken(1)).thenReturn("token");

        String acToken = sut.setToken(1);

        assertEquals("token", acToken);
    }
}