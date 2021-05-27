package com.diagneaxel.TP5suiviDesRepas.dao;

import com.diagneaxel.TP5suiviDesRepas.bo.Aliments;

import java.sql.SQLException;
import java.util.List;

public interface AlimentDAO {

    /**
     * INSERT INTO Aliments
     * @param aliments List aliment
     * @throws SQLException
     */
    public void insert (Aliments aliments) throws SQLException;

    /**
     * Select aliment en fonction de l'id
     * @param id index aliment
     * @return listes aliments en fonction id
     * @throws SQLException
     */
    List<Aliments> selectAliments(int id) throws SQLException;
}
