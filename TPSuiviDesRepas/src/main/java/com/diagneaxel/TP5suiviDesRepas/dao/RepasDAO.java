package com.diagneaxel.TP5suiviDesRepas.dao;

import com.diagneaxel.TP5suiviDesRepas.bo.Repas;

import java.sql.SQLException;
import java.util.List;

public interface RepasDAO {

    int insert(Repas repas) throws SQLException;

    Repas select(int id) throws SQLException;

    List<Repas> selectAll() throws SQLException;
}
