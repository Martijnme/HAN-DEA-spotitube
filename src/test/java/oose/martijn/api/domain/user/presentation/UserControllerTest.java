package oose.martijn.api.domain.user.presentation;

import oose.martijn.api.Test_Util.MockData;
import oose.martijn.api.domain.impl.service.UserService;
import oose.martijn.api.exceptions.InvalidLoginUser;
import oose.martijn.api.exceptions.NullUserExeption;
import oose.martijn.api.exceptions.SpotitubeExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockData mockData = new MockData();

    @InjectMocks
    UserController sut;

    @Mock
    UserService mockedUserService;

    @BeforeEach
    public void setup(){
        sut = new UserController();
        sut.userService = mockedUserService;
    }
    @Test
    void userLogin() {
        when(mockedUserService.loginUser(mockData.userRequest().getUser(), mockData.userRequest().getPassword())).thenReturn(mockData.userResponse());

        Response acResponse = sut.userLogin(mockData.userRequest());
        verify(mockedUserService).loginUser(mockData.userRequest().getUser(), mockData.userRequest().getPassword());
        assertEquals(Response.Status.OK.getStatusCode(), acResponse.getStatus());
    }
    @Test
    void userLoginInvalidUser() {
        when(mockedUserService.loginUser(mockData.userRequest().getUser(), mockData.userRequest().getPassword())).thenThrow(InvalidLoginUser.class);


        Response acResponse = sut.userLogin(mockData.userRequest());

        assertEquals(401, acResponse.getStatus());
    }
    @Test
    void userLoginNoUser() {
        when(mockedUserService.loginUser(mockData.userRequest().getUser(), mockData.userRequest().getPassword())).thenThrow(NullUserExeption.class);


        Response acResponse = sut.userLogin(mockData.userRequest());

        assertEquals(401, acResponse.getStatus());
    }
    @Test
    void userLoginGeneralExeption() {
        when(mockedUserService.loginUser(mockData.userRequest().getUser(), mockData.userRequest().getPassword())).thenThrow(SpotitubeExeption.class);


        Response acResponse = sut.userLogin(mockData.userRequest());

        assertEquals(400, acResponse.getStatus());
    }
}