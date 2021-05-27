package com.diagneaxel.TP5suiviDesRepas.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class PoolConnection {
    //Attribut DataSource cf module 4
    private static DataSource dataSource;

    static {
        Context context;
        try {
            context = new InitialContext();
            PoolConnection.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return PoolConnection.dataSource.getConnection();
    }
}
