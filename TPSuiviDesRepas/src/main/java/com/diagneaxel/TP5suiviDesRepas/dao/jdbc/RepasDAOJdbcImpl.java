package com.diagneaxel.TP5suiviDesRepas.dao.jdbc;

import com.diagneaxel.TP5suiviDesRepas.bo.Repas;
import com.diagneaxel.TP5suiviDesRepas.dao.PoolConnection;
import com.diagneaxel.TP5suiviDesRepas.dao.RepasDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepasDAOJdbcImpl implements RepasDAO {
    private final String INSERT = "INSERT INTO repas(date_repas, heure_repas) VALUES(?,?)";
    private final String SELECT_ALL = "SELECT * FROM repas";

    @Override
    public int insert(Repas repas) throws SQLException {
        Connection connection = null;
        int idRepas = 0;
        try{
            connection = PoolConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(this.INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, repas.getDate());
            preparedStatement.setString(2,repas.getHeure());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while(rs.next()){
                idRepas = rs.getInt(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (!connection.isClosed()){
                connection.close();
            }
        }

        return idRepas;
    }

    @Override
    public Repas select(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Repas> selectAll() throws SQLException {
        Connection connection = null;
        List<Repas> repasList = new ArrayList<>();

        try{
            connection = PoolConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(this.SELECT_ALL);
            while (rs.next()){
                Repas repas = new Repas(rs.getInt("id"),rs.getString("date_repas"),rs.getString("heure_repas"));
                repasList.add(repas);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (!connection.isClosed()){
                connection.close();
            }
        }
        return repasList;

    }
}
