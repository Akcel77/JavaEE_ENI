package com.diagneaxel.javaee.bo;

public class Article {
    //Attributs
    private int identifier;
    private String name;
    private boolean isChecked;

    //CONSTRUCTORS
    public Article(String name) {
        this.name = name;
    }

    public Article(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public Article(int identifier, String name, boolean isChecked) {
        this.identifier = identifier;
        this.name = name;
        this.isChecked = isChecked;
    }


    //GETTERS AND SETTERS
    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getChecked(){
        return isChecked ? "checked" : null;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
