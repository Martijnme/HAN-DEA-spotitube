package oose.martijn.api.domain.impl.service;

import oose.martijn.api.domain.user.User;
import oose.martijn.api.domain.user.data.IUserDAO;
import oose.martijn.api.domain.user.data.IUserTokenDAO;
import oose.martijn.api.domain.user.presentation.UserResponse;
import oose.martijn.api.domain.user.service.IUserService;
import oose.martijn.api.domain.user.service.IUserTokenService;
import oose.martijn.api.exceptions.InvalidLoginUser;

import javax.inject.Inject;

public class UserService implements IUserService {
    @Inject
    public IUserTokenDAO userTokenDAO;
    @Inject
    public IUserDAO userDAO;
    @Inject
    public IUserTokenService tokenService;

    @Override
    public User getUserOnToken(String tokenId) {
        return userDAO.getUserByUserId(userTokenDAO.getUserIdOnTokenId(tokenId));
    }

    @Override
    public UserResponse loginUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (!user.passwordDecrypt(password)) throw new InvalidLoginUser("Invalid Login for this user");
        return new UserResponse(user.getFullName(), tokenService.setToken(user.getUserId()));
    }
}

