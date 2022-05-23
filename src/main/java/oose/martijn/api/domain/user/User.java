package oose.martijn.api.domain.user;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
    private int userId;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    public User() {

    }

    public User(int userId, String username, String password, String firstname, String lastname) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String passwordEncrypt(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean passwordDecrypt(String password) {
        return DigestUtils.sha256Hex(password).equals(this.password);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullName() {
        return (this.firstname + " " + this.lastname);
    }
}
