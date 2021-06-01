package com.diagneaxel.javaee.dal;

import com.diagneaxel.javaee.bo.Liste;
import com.diagneaxel.javaee.dal.jdbc.DAOListeJDBC;

public abstract class DAOFactory {
    public static DAO<Liste> getListeDAO() { return new DAOListeJDBC(); }
}
