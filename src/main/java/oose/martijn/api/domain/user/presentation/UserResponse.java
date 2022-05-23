package oose.martijn.api.domain.user.presentation;

public class UserResponse {
    private String user;
    private String token;

    public UserResponse(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }
}
