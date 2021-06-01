package com.diagneaxel.javaee.messages;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Permet la lecture des messages d'erreur
 */
public class Reader {
    private static ResourceBundle resourceBundle;
    private static final String FILENAME = "com.diagneaxel.javaee.messages.messages";

    //Recuperation du Fichier d'erreurs
    static {
        try {
            resourceBundle = ResourceBundle.getBundle(FILENAME, Locale.FRANCE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Message error pour la recuperation et lecture du ficher "messages.properties
     * @param code code erreur
     * @return message erreur
     */
    public static String getMessageError(int code){
        String message = "";
        try {
            if(resourceBundle != null){
                message = resourceBundle.getString(String.valueOf(code));
            }else{
                message = "Probleme de lecture fichier";
            }
        }catch (Exception e){
            e.printStackTrace();
            message = "Une erreur est survenue | READER";
        }
        return message;
    }
}
