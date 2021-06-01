package com.diagneaxel.javaee.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Liste {
    //ATTRIBUTS
    private static final Map<Integer, Liste> listes = new HashMap<>();
    private int identifier;
    private String name;
    private List<Article> articles = new ArrayList<>();

    //CONSTRUCTORS

    public Liste() {
    }

    public Liste(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public Liste(int identifier, String name, List<Article> articles) {
        this.identifier = identifier;
        this.name = name;
        this.articles = articles;
    }

    /**
     * Ajoute un article
     * @param article en cours
     */
    public void addArticle(Article article){
        this.articles.add(article);
    }

    @Override
    public String toString(){
        return "Liste de course : " + name + " (" + identifier + ")";
    }

    //GETTER AND SETTER

    public static Liste getListe(int identifier) {
        return listes.get(identifier);
    }

    public static Map getListes(){
        return listes;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
        listes.put(identifier, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
