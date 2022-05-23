package oose.martijn.api.domain.user.data;

import oose.martijn.api.domain.user.User;

public interface IUserDAO {

    User getUserByUserId(int userId);

    User getUserByUsername(String username);
}
