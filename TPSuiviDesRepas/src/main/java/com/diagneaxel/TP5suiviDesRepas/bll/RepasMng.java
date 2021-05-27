package com.diagneaxel.TP5suiviDesRepas.bll;

import com.diagneaxel.TP5suiviDesRepas.bo.Repas;
import com.diagneaxel.TP5suiviDesRepas.dao.AlimentDAO;
import com.diagneaxel.TP5suiviDesRepas.dao.DAOFactory;
import com.diagneaxel.TP5suiviDesRepas.dao.RepasDAO;

import java.sql.SQLException;
import java.util.List;

public class RepasMng {

    //Atributs
     private RepasDAO repasDAO;
     private AlimentDAO alimentDAO;

     //Constructor

    public RepasMng() {
        this.repasDAO = DAOFactory.getRepasDAO();
        this.alimentDAO = DAOFactory.getAlimentsDAO();
    }

    /**
     * Recupere la liste des repas
     * Recupe l'aliments lie
     * Retourne liste repas + aliment
     * @return repas liste
     * @throws SQLException
     */
    public List<Repas> getRepas() throws  SQLException {
        List<Repas> repasList = repasDAO.selectAll();

        for(Repas r : repasList){
            r.setAliments(alimentDAO.selectAliments(r.getId()));
        }
        return repasList;
    }

    public int insert(Repas repas)throws SQLException{
        return repasDAO.insert(repas);
    }



}
