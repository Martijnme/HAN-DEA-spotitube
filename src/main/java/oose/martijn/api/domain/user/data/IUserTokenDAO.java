package oose.martijn.api.domain.user.data;

public interface IUserTokenDAO {

     int getUserIdOnTokenId(String tokenId) ;

     String setUserToken(int userId) ;

}
