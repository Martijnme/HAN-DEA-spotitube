//package oose.martijn.api.config;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class DatabaseConnection {
//    private final String connectionString;
//    private final String user;
//    private final String password;
//    private final String driver;
//
//
//    public DatabaseConnection() throws IOException {
//        String propsFileName = "database.properties";
//
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
//        Properties props = new Properties();
//
//        if (inputStream != null) props.load(inputStream);
//        else throw new FileNotFoundException("property file '" + propsFileName + "' not found in the given path");
//
//        this.connectionString = props.getProperty("connectionstring");
//        this.user = props.getProperty("user");
//        this.password = props.getProperty("password");
//        this.driver = props.getProperty("driver");
//        inputStream.close();
//    }
//
//    public Connection Connect() throws SQLException {
//        try {
//            Class.forName(this.driver);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return DriverManager.getConnection(this.connectionString, this.user, this.password);
//    }
//}

package oose.martijn.api.domain.impl.data;

import oose.martijn.api.config.IDatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection implements IDatabaseConnection {

    private Properties props;
    private String propsFileName = "database.properties";

    public DatabaseConnection() {
        props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream(propsFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Connection Connect() {
        try {
            Class.forName(props.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection(props.getProperty("connectionstring"), props.getProperty("user"), props.getProperty("password"));
        } catch (SQLException e) {
            throw new IllegalArgumentException("Cant connect to Database.");
        }
    }
}