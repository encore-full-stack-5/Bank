package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    private static class DatabaseHolder {
        static final DB INSTANCE = new DB();
    }

    public static DB getInstance() {
        return DatabaseHolder.INSTANCE;
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DBInitializer.DATABASE_URL,
                USERNAME,
                PASSWORD
        );
    }

    private DB() {
        initDataBase();
    }

    private void initDataBase() {
        DBInitializer.init();
        DBInitializer.initDummy();
    }
}