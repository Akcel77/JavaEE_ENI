package com.diagneaxel.javaee.dal.jdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * POOL DE CONNECTION A LA DB
 */
public class JDBC {
    private static DataSource dataSource;

    static {
        Context context;
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
        }catch (NamingException e){
            throw new RuntimeException("Probleme de connection a la BD");
        }
    }

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
