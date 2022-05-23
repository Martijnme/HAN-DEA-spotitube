package oose.martijn.api.domain.impl.data;

import oose.martijn.api.exceptions.NullUserExeption;
import oose.martijn.api.exceptions.UserTokenException;
import oose.martijn.api.domain.user.data.IUserTokenDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static oose.martijn.api.config.SqlQueries.*;

public class UserTokenDAO implements IUserTokenDAO {

    private DatabaseConnection databaseConnection = new DatabaseConnection();
    @Override
    public int getUserIdOnTokenId(String tokenId) {
        int userId = -1;
        userId = getUserId(tokenId, userId);
        if (userId == -1)
            throw new NullUserExeption("No user found on token-Id " + tokenId);
        return userId;
    }

    public int getUserId(String tokenId, int userId) {
        try (
                Connection conn = databaseConnection.Connect();
                PreparedStatement ps = conn.prepareStatement(SQL_SELECT_USER_ON_TOKENID);
        ) {
            ps.setString(1, tokenId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                userId = rs.getInt("userId");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public String setUserToken(int userId) {
        final String token = UUID.randomUUID().toString();
        return setToken(userId, token);
    }

    public String setToken(int userId, String token) {
        try (
                Connection conn = databaseConnection.Connect();
        ) {
            PreparedStatement ps;
            if (hasUserToken(userId, conn)) ps = conn.prepareStatement(SQL_UPDATE_TOKEN_ON_USERID);
            else ps = conn.prepareStatement(SQL_INSERT_TOKEN);
            ps.setString(1, token);
            ps.setInt(2, userId);
            ps.execute();
        } catch (Exception e) {
            throw new UserTokenException("Something went wrong while creating the user token.");
        }
        return  token;
    }

    private boolean hasUserToken(int userId, Connection conn) {
        boolean hasToken = false;
        try (PreparedStatement ps = conn.prepareStatement(SQL_SELECT_TOKEN_ON_USERID)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                hasToken = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasToken;
    }

}
