package oose.martijn.api.domain.user.service;

import oose.martijn.api.domain.user.User;
import oose.martijn.api.domain.user.presentation.UserResponse;

public interface IUserService {

    User getUserOnToken(String tokenId);
    UserResponse loginUser(String username, String password) ;
}

