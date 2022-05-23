package oose.martijn.api.exceptions;

public class SpotitubeExeption extends RuntimeException {
    public SpotitubeExeption() {
        super("Fout opgetreden tijdens uitvoeren van request.");
    }
}
