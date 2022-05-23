package oose.martijn.api.config;

import java.sql.Connection;

public interface IDatabaseConnection {

    Connection Connect();
}