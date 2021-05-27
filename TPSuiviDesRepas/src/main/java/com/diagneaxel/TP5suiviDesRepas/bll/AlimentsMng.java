package com.diagneaxel.TP5suiviDesRepas.bll;

import com.diagneaxel.TP5suiviDesRepas.bo.Aliments;
import com.diagneaxel.TP5suiviDesRepas.dao.AlimentDAO;
import com.diagneaxel.TP5suiviDesRepas.dao.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class AlimentsMng {
    private AlimentDAO alimentsDAO;

    public AlimentsMng(){
        this.alimentsDAO = DAOFactory.getAlimentsDAO();
    }

    public List<Aliments> getAliments(int id) throws SQLException {
        return alimentsDAO.selectAliments(id);
    }

    public void setAliments(int idRepas, String[] aliments) throws SQLException {
        for ( String aliment : aliments ) {
            alimentsDAO.insert(new Aliments(idRepas,aliment));
        }

    }
}
