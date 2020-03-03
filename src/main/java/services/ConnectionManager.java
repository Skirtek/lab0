package services;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

class ConnectionManager {
    private static JdbcConnectionPool pool;

    static {
        pool = JdbcConnectionPool.create("jdbc:h2:~/test", "admin", "admin");
    }

    static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }
}