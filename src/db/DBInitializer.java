package db;

import common.Constants;
import common.QueryText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {
    public static String DATABASE_URL = "jdbc:mysql://localhost:3306/";

    public static void init() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, Constants.USERNAME, Constants.PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE DATABASE IF NOT EXISTS bank");
            stmt.execute("use bank");
            DATABASE_URL = "jdbc:mysql://localhost:3306/bank";
            stmt.executeUpdate(QueryText.CREATE_USER_QUERY);
            stmt.executeUpdate(QueryText.CREATE_BANK_QUERY);
            stmt.executeUpdate(QueryText.CREATE_EMPLOYEE_QUERY);
            stmt.executeUpdate(QueryText.CREATE_ACCOUNT_QUERY);
            stmt.executeUpdate(QueryText.CRATE_TRANSACTION_HISTORY_QUERY);
            stmt.executeUpdate(QueryText.CRATE_RESERVATION_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initDummy() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, Constants.USERNAME, Constants.PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("use bank");
            stmt.executeUpdate(QueryText.INSERT_USER_DUMMY);
            stmt.executeUpdate(QueryText.INSERT_BANK_DUMMY);
            stmt.executeUpdate(QueryText.INSERT_EMPLOYEE_DUMMY);
            stmt.executeUpdate(QueryText.INSERT_ACCOUNT_DUMMY);
            stmt.executeUpdate(QueryText.INSERT_RESERVATION_DUMMY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}