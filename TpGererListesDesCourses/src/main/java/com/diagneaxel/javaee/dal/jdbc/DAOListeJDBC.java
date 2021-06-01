package com.diagneaxel.javaee.dal.jdbc;

import com.diagneaxel.javaee.ListeException;
import com.diagneaxel.javaee.bo.Article;
import com.diagneaxel.javaee.bo.Liste;
import com.diagneaxel.javaee.dal.CodesExceptionDAL;
import com.diagneaxel.javaee.dal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOListeJDBC implements DAO<Liste> {
    //REQUETE SQL
    private static final String SELECT_ALL =
            "SELECT id, nom " +
                    "FROM LISTES";
    private static final String SELECT_BY_ID =
            "SELECT " +
                    "LISTES.id, " +
                    "LISTES.nom, " +
                    "ARTICLES.id AS id_article, " +
                    "ARTICLES.nom AS nom_article, " +
                    "coche " +
                    "FROM LISTES " +
                    "LEFT JOIN ARTICLES ON LISTES.id = ARTICLES.id_liste " +
                    "WHERE LISTES.id = ?";
    private static final String INSERT_LISTE =
            "INSERT INTO LISTES(nom) VALUES (?)";
    private static final String INSERT_ARTICLE =
            "INSERT INTO ARTICLES(nom, id_liste) VALUES (?, ?)";
    private static final String DELETE_ARTICLE =
            "DELETE FROM ARTICLES WHERE id = ?";
    private static final String DELETE_LISTE =
            "DELETE FROM LISTES where id = ?";
    private static final String CHECK_ARTICLE =
            "UPDATE ARTICLES SET coche = 1 WHERE id = ?";
    private static final String UNCHECK_ARTICLE =
            "UPDATE ARTICLES SET coche = 0 WHERE id = ?";
    private static final String UNCHECK_ALL_ARTICLES =
            "UPDATE ARTICLES SET coche = 0 WHERE id_liste = ?";


    /**
     * Insert liste avec Article
     * @param liste d'article
     * @throws ListeException erreur from messages.properties
     */
    @Override
    public void insert(Liste liste) throws ListeException {
        if (liste == null) {
            throw new ListeException(CodesExceptionDAL.INSERT_OBJET_NULL);
        }
        try (Connection connection = JDBC.getConnection()){
                try {
                    connection.setAutoCommit(false);
                    PreparedStatement statement;
                    //Set identifier
                    if (liste.getIdentifier() == 0) {
                        statement = connection.prepareStatement(INSERT_LISTE, PreparedStatement.RETURN_GENERATED_KEYS);
                        statement.setString(1, liste.getName());
                        statement.executeUpdate();
                        ResultSet resultSet = statement.getGeneratedKeys();
                        if (resultSet.next()) {
                            liste.setIdentifier(resultSet.getInt(1));
                        }
                        resultSet.close();
                        statement.close();
                    }
                    //Add Article
                    if (!liste.getArticles().isEmpty()) {
                        deleteContentFrom(liste);
                        for (Article article : liste.getArticles()) {
                            statement = connection.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);                            ;
                            statement.setString(1, article.getName());
                            statement.setInt(2, liste.getIdentifier());
                            statement.executeUpdate();
                            ResultSet resultSet = statement.getGeneratedKeys();
                            if (resultSet.next()) {
                                article.setIdentifier(resultSet.getInt(1));
                            }
                            resultSet.close();
                            statement.close();
                        }
                    }
                    connection.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    ListeException listeException = new ListeException(CodesExceptionDAL.INSERT_OBJET_FAIL);
                    try {
                        connection.rollback();
                    } catch (SQLException sqlException) {
                        e.printStackTrace();
                        listeException.addCode(CodesExceptionDAL.CONNECTION_ROLLBACK_ERROR);
                    }
                    throw listeException;
                }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new ListeException(CodesExceptionDAL.CONNECTION_ERROR);
        }
    }


    /**
     * Supprime une liste d'article en fonction de son id
     * @param identifier id de la liste
     * @throws ListeException erreur from messages.properties
     */
    @Override
    public void delete(int identifier) throws ListeException {
        try(Connection connection = JDBC.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_LISTE);
            statement.setInt(1, identifier);
            statement.executeUpdate();
        }catch (SQLException sqlException){
            throwListeSQLExceptionDAL(sqlException, CodesExceptionDAL.SUPPRESSION_LISTE_ERROR);
        }
    }

    /**
     * Supprime un article en fonction de son id
     * @param identifier id de l'article
     * @throws ListeException erreur from messages.properties
     */
    @Override
    public void deleteContent(int identifier) throws ListeException {
        try(Connection connection = JDBC.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLE);
            statement.setInt(1, identifier);
            statement.executeUpdate();
        }catch (SQLException sqlException){
            throwListeSQLExceptionDAL(sqlException, CodesExceptionDAL.SUPPRESSION_LISTE_ERROR);
        }
    }

    public void deleteContent(Article article) throws ListeException {
        deleteContent(article.getIdentifier());
    }

    /**
     * Delete tout les articles d'une liste en fonction de l'id de celle ci
     * @param identifier de la liste
     * @throws ListeException erreur from messages.properties
     */
    @Override
    public void deleteContentFrom(int identifier)throws ListeException{
        Liste liste = Liste.getListe(identifier);
        if (liste != null){
            for (Article a : liste.getArticles()){
                deleteContent(a);
            }
        }
    }

    public void deleteContentFrom(Liste liste) throws ListeException {
        deleteContentFrom(liste.getIdentifier());
    }


    /**
     * Get liste et article par l'id liste
     * @param identifier id liste
     * @return la liste des article
     * @throws ListeException erreur from messages.properties
     */
    @Override
    public Liste selectById(int identifier) throws ListeException{
        Liste liste = null;
        try(Connection connection = JDBC.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, identifier);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                if (liste == null){
                    liste = new Liste(resultSet.getInt("id"), resultSet.getString("nom"));
                }
                if (resultSet.getString("nom_article") != null ){
                    liste.addArticle(new Article(resultSet.getInt("id_article"), resultSet.getString("nom_article"), resultSet.getBoolean("coche")));
                }
            }
        }catch (SQLException sqlException){
            throwListeSQLExceptionDAL(sqlException, CodesExceptionDAL.LECTURE_LISTE_FAIL);
        }
        if (liste == null || liste.getIdentifier() == 0 ){
            throw new ListeException(CodesExceptionDAL.LECTURE_LISTE_INEXISTANTE);
        }
        return liste;
    }

    /**
     * Selectionne toutes les listes
     * @return toutes les listes
     * @throws ListeException erreur from messages.properties
     */
    @Override
    public List<Liste> selectAll() throws ListeException {
        List<Liste> listes = new ArrayList<>();
        try(Connection connection = JDBC.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                listes.add(new Liste(resultSet.getInt("id"), resultSet.getString("nom")));
            }
        }catch (SQLException sqlException){
            throwListeSQLExceptionDAL(sqlException, CodesExceptionDAL.SUPPRESSION_LISTE_ERROR);
        }
        return listes;
    }

    /**
     * Check le coche d'un article par rapport a son ID
     * @param identifierContent id de l'article
     * @throws ListeException erreur from messages.properties
     */
    @Override
    public void checkContent(int identifierContent) throws ListeException {
        try(Connection connection = JDBC.getConnection()){
            PreparedStatement statement = connection.prepareStatement(CHECK_ARTICLE);
            statement.setInt(1, identifierContent);
            statement.executeUpdate();
        }catch (SQLException sqlException){
            throwListeSQLExceptionDAL(sqlException, CodesExceptionDAL.CHECK_ARTICLE_ERROR);
        }
    }

    /**
     * Decoche les checkbox de un ou plusieurs article en fonction de son identifiant
     * @param identifier id
     * @param query sql query
     * @param codeExceptionDAL numero erreur
     * @throws ListeException erreur from messages.properties
     */
    private void uncheckContentBy(int identifier, String query, int codeExceptionDAL) throws ListeException{
        try(Connection connection = JDBC.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, identifier);
            statement.executeUpdate();
        }catch (SQLException sqlException){
            throwListeSQLExceptionDAL(sqlException, codeExceptionDAL);
        }
    }

    /**
     * Decoche un article en fonction de son id
     * @param identfierContent id article
     * @throws ListeException erreur from messages.propreties
     */
    @Override
    public void uncheckContent(int identfierContent) throws ListeException{
        uncheckContentBy(identfierContent, UNCHECK_ARTICLE, CodesExceptionDAL.UNCHECK_ARTICLE_ERROR);
    }

    /**
     * Decoche tous les articles d'une liste en fonction de l'id de celle ci
     * @param identifier id de la liste
     * @throws ListeException erreur from messages.propreties
     */
    @Override
    public void uncheckAllContent(int identifier) throws ListeException {
        uncheckContentBy(identifier, UNCHECK_ALL_ARTICLES, CodesExceptionDAL.UNCHECK_ARTICLE_ERROR);
    }

    /**
     * Function pour message erreur
     * @param exception leve exception
     * @param codeExceptionDAL recupere le code erreur
     * @throws ListeException Liste Exception
     */
    private void throwListeSQLExceptionDAL(Exception exception, int codeExceptionDAL) throws ListeException {
        exception.printStackTrace();
        ListeException listeException = new ListeException(CodesExceptionDAL.CONNECTION_ERROR);
        listeException.addCode(codeExceptionDAL);
        throw listeException;
    }
}