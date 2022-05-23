package oose.martijn.api.exceptions;

public class InvalidLoginUser extends RuntimeException{
    public InvalidLoginUser(String s){
        super(s);
    }
}
