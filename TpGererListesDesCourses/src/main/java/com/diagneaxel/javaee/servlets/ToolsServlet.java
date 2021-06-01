package com.diagneaxel.javaee.servlets;

import com.diagneaxel.javaee.ListeException;
import com.diagneaxel.javaee.bll.ListeManager;
import com.diagneaxel.javaee.bo.Liste;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ToolsServlet {

    /**
     * Verifie que l'article et la liste on bien un nom parametree
     * @param parameter nom list / article
     * @param request requette HTTPServlet
     * @param listeException erreur listeException
     * @return nom liste / article
     */
    static String checkName(String parameter, HttpServletRequest request, ListeException listeException){
        try {
            return request.getParameter(parameter);
        }catch (Exception e){
            e.printStackTrace();
            if (parameter.equals("name")) {
                listeException.addCode(CodesExceptionServlets.NOM_LISTE_OBLIGATOIRE);
            }else if (parameter.equals("nameArticle")){
                listeException.addCode(CodesExceptionServlets.NOM_ARTICLE_OBLIGATOIRE);
            }
        }
        return null;
    }

    /**
     * Verifie que l'id liste ou article a bien un parametre
     * @param parameter de l'article ou de la liste
     * @param request requette HTTPServlet
     * @param listeException erreur ListeException
     * @return parametre en Int
     */
    static int checkIdentifier(String parameter, HttpServletRequest request, ListeException listeException){
        try {
            if (request.getParameter(parameter) != null ){
                return Integer.parseInt(request.getParameter(parameter));
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            if(parameter.equals("identifierArticle")){
                listeException.addCode(CodesExceptionServlets.FORMAT_ID_ARTICLE_ERREUR);
            }else if (parameter.equals("identifier")){
                listeException.addCode(CodesExceptionServlets.FORMAT_ID_ARTICLE_ERREUR);
            }
        }
        return 0;
    }

    /**
     * Set l'attribut de liste
     * @param request HttpServletRequest
     * @param liste parametre liste
     */
    static void setAttributes(HttpServletRequest request, Liste liste){
        request.setAttribute("liste", liste);
        request.setAttribute("articles", liste.getArticles());
    }

    /**
     * Set l'attribut error
     * @param listeException erreur ListeException
     * @param request  HttpServletRequest
     * @param error erreur ListeException
     */
    static void setErrorsAttribute(ListeException listeException, HttpServletRequest request, ListeException error){
        listeException.printStackTrace();
        error.getCodes().addAll(listeException.getCodes());
        request.setAttribute("errors", error.getCodes());
    }

    /**
     * Met a jours la liste
     * @param request HttpServletRequest
     * @param listeManager set la liste Manager
     * @param listeException erreur ListeException
     */
    static void updateListes(HttpServletRequest request, ListeManager listeManager, ListeException listeException){
        try {
            request.setCharacterEncoding("UTF-8");
            listeManager.setListes();
        }catch (ListeException e){
            setErrorsAttribute(e, request, listeException);
        }catch (UnsupportedEncodingException exception){
            exception.printStackTrace();
        }
    }

}
