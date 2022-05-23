package oose.martijn.api.domain.impl.service;

import oose.martijn.api.domain.user.data.IUserTokenDAO;
import oose.martijn.api.domain.user.service.IUserTokenService;

import javax.inject.Inject;

public class UserTokenService implements IUserTokenService {

    @Inject
    public IUserTokenDAO userTokenDAO;

    @Override
    public String setToken(int userId) {
        return userTokenDAO.setUserToken(userId);
    }
}
