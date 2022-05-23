package oose.martijn.api.domain.impl.data;

import oose.martijn.api.config.IDatabaseConnection;
import oose.martijn.api.domain.user.User;
import oose.martijn.api.domain.user.data.IUserDAO;
import oose.martijn.api.exceptions.NullUserExeption;
import oose.martijn.api.exceptions.SpotitubeExeption;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static oose.martijn.api.config.SqlQueries.SQL_SELECT_USER_ON_USERID;
import static oose.martijn.api.config.SqlQueries.SQL_SELECT_USER_ON_USERNAME;

public class UserDAO implements IUserDAO {

    @Inject
    private IDatabaseConnection databaseConnection;

    @Override
    public User getUserByUserId(int userId) {
        User user = null;

        user = getUser(userId, user);

        if (user == null)
            throw new NullUserExeption("No user found on user-Id " + userId);

        return user;
    }

    public User getUser(int userId, User user) {
        try (Connection conn = databaseConnection.Connect();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_USER_ON_USERID);
        ) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                user = new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpotitubeExeption();
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        user = getUser(username, user);
        if (user == null)
            throw new NullUserExeption("No user found on username " + username);
        return user;
    }

    public User getUser(String username, User user) {
        try (Connection conn = databaseConnection.Connect();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_USER_ON_USERNAME);) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                user = new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpotitubeExeption();
        }
        return user;
    }
}
