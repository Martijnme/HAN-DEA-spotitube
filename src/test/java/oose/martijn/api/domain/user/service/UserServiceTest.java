package oose.martijn.api.domain.user.service;

import oose.martijn.api.Test_Util.MockData;
import oose.martijn.api.domain.impl.service.UserService;
import oose.martijn.api.domain.user.User;
import oose.martijn.api.domain.user.data.IUserDAO;
import oose.martijn.api.domain.user.data.IUserTokenDAO;
import oose.martijn.api.domain.user.presentation.UserResponse;
import oose.martijn.api.exceptions.InvalidLoginUser;
import oose.martijn.api.exceptions.NullUserExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService sut;
    @Mock
    private IUserDAO mockedUserDAO;
    @Mock
    private IUserTokenDAO mockedUserTokenDAO;
    @Mock
    private IUserTokenService mockedUserTokenService;
    private MockData mockData = new MockData();

    @BeforeEach
    public void setup() {
        sut = new UserService();
        sut.tokenService = mockedUserTokenService;
        sut.userDAO = mockedUserDAO;
        sut.userTokenDAO = mockedUserTokenDAO;
    }

    @Test
    void getUserOnToken() {
        when(mockedUserTokenDAO.getUserIdOnTokenId("token")).thenReturn(1);
        when(mockedUserDAO.getUserByUserId(1)).thenReturn(mockData.user());

        User acUser = sut.getUserOnToken("token");

        verify(mockedUserDAO).getUserByUserId(1);
        verify(mockedUserTokenDAO).getUserIdOnTokenId("token");
        assertEquals(mockData.user().getFirstname(), acUser.getFirstname());
    }

    @Test
    void loginUser() {
        when(mockedUserDAO.getUserByUsername("bob")).thenReturn(mockData.user());
        when(mockedUserTokenService.setToken(mockData.user().getUserId())).thenReturn("token");
        UserResponse acUserResponse = sut.loginUser("bob","secret");

        assertEquals(mockData.userResponse().getUser(),acUserResponse.getUser());
    }

    @Test
    void loginUserThrowExceptionOnNoUser(){
        when(mockedUserDAO.getUserByUsername("notBob")).thenThrow(NullUserExeption.class);

        assertThrows(NullUserExeption.class, () -> {
            sut.loginUser("notBob","password");
        });
    }

    @Test
    void loginUserThrowExceptionNoPasswordMatch(){
        when(mockedUserDAO.getUserByUsername("bob")).thenThrow(InvalidLoginUser.class);

        assertThrows(InvalidLoginUser.class, () -> {
            sut.loginUser("bob","wrongpassword");
        });
    }

}