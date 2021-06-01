package com.diagneaxel.javaee.bll;

import com.diagneaxel.javaee.ListeException;
import com.diagneaxel.javaee.bo.Article;
import com.diagneaxel.javaee.bo.Liste;
import com.diagneaxel.javaee.dal.DAO;
import com.diagneaxel.javaee.dal.DAOFactory;

import java.util.List;

public class ListeManager {
    private final com.diagneaxel.javaee.dal.DAO<Liste> DAO;

    // CONSTRUCTOR
    public ListeManager() {
        this.DAO = DAOFactory.getListeDAO();
    }

    /**
     * Selectionne toutes les listes
     * @return les listes selectionnees
     * @throws ListeException erreur ListeException
     */
    public List<Liste> selectAllListes() throws ListeException { return this.DAO.selectAll(); }


    /**
     * Set une liste
     * @throws ListeException erreur ListeException
     */
    public void setListes() throws ListeException {
        for (Liste liste : selectAllListes()) {
            liste = selectListe(liste.getIdentifier());
            liste.setIdentifier(liste.getIdentifier());
        }
    }

    /**
     * Selectionne une liste par son id
     * @param identifier id de la liste
     * @return la liste en question
     * @throws ListeException erreur ListeException
     */
    public Liste selectListe(int identifier) throws ListeException {
        return this.DAO.selectById(identifier);
    }

    /**
     * Ajoute une nouvelle liste
     * @param identifier identifiant de la liste
     * @param name nom de la liste
     * @return retourne la liste cree
     * @throws ListeException erreur ListeException
     */
    public Liste addListe(int identifier, String name) throws ListeException {
        checkName(name, CodesExceptionBLL.REGLE_LISTE_NOM_ERREUR);
        Liste liste = new Liste(identifier, name);
        this.DAO.insert(liste);
        Liste.getListes().remove(0); // The key 0 is removed. It was used to create new list.
        return liste;
    }

    /**
     * Ajoute un article a une liste deja cree
     * @param identifier id de la liste
     * @param name Article
     * @throws ListeException erreur ListeException
     */
    public void addArticle(int identifier, String name) throws ListeException {
        checkName(name, CodesExceptionBLL.REGLE_ARTICLE_NOM_ERREUR);
        Liste liste = Liste.getListe(identifier);
        if (liste != null) {
            Article article = new Article(name.trim());
            liste.addArticle(article);
            this.DAO.insert(liste);
        }
    }


    // APPELLE A LA DAL

    public void deleteArticle(int identifier) throws ListeException {
        this.DAO.deleteContent(identifier);
    }

    public void deleteListe(int identifier) throws ListeException {
        this.DAO.delete(identifier);
    }

    public void checkArticle(int identifier) throws ListeException {
        this.DAO.checkContent(identifier);
    }

    public void uncheckArticle(int identifier) throws ListeException {
        this.DAO.uncheckContent(identifier);
    }

    public void uncheckAllArticles(int identifier) throws ListeException {
        this.DAO.uncheckAllContent(identifier);
    }

    private void checkName(String name, int codeExceptionBLL) throws ListeException {
        if (name == null || name.trim().length() > 50) {
            throw new ListeException(codeExceptionBLL);
        }
    }
}
