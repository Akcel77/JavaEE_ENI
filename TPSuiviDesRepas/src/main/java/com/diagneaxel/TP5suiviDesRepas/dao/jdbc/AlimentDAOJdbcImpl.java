package com.diagneaxel.TP5suiviDesRepas.dao.jdbc;

import com.diagneaxel.TP5suiviDesRepas.bo.Aliments;
import com.diagneaxel.TP5suiviDesRepas.dao.AlimentDAO;
import com.diagneaxel.TP5suiviDesRepas.dao.PoolConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlimentDAOJdbcImpl implements AlimentDAO {

    //REQUETE SQL
    private final String INSERT = "INSERT INTO aliments(nom, id_repas) VALUES(?,?)";
    private final String SELECT = "SELECT * FROM aliments WHERE id_repas = ?";

    @Override
    public void insert(Aliments aliments) throws SQLException {
        Connection connection = null;

        try{
            connection = PoolConnection.getConnection();
            connection = PoolConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT);
            stmt.setString(1,aliments.getAliment());
            stmt.setInt(2,aliments.getIdRepas());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (!connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public List<Aliments> selectAliments(int id) throws SQLException {
        Connection connection = null;
        List<Aliments> aliments = new ArrayList<>();

        try {
            connection = PoolConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                aliments.add(new Aliments(id,rs.getInt("id"), rs.getString("nom")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(!connection.isClosed()){
                connection.close();
            }
        }

        return aliments;
    }
}
