package com.diagneaxel.TP5suiviDesRepas.dao;

import com.diagneaxel.TP5suiviDesRepas.dao.jdbc.AlimentDAOJdbcImpl;
import com.diagneaxel.TP5suiviDesRepas.dao.jdbc.RepasDAOJdbcImpl;

public class DAOFactory {

    /**
     * @return Imp Repas DAO JDBC
     */
    public static RepasDAO getRepasDAO(){
        return new RepasDAOJdbcImpl();
    }

    /**
     * @return Imp Aliment DAO JDBC
     */
    public static AlimentDAO getAlimentsDAO() {
        return new AlimentDAOJdbcImpl();
    }
}
