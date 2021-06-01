package com.diagneaxel.javaee.servlets;

import com.diagneaxel.javaee.ListeException;
import com.diagneaxel.javaee.bll.ListeManager;
import com.diagneaxel.javaee.bo.Liste;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {
        "/display",
        "/addArticle",
        "/deleteArticle"
})
public class ServletAddListe extends HttpServlet {
    protected ListeException errorsCodes = new ListeException();
    protected ListeManager listeManager = new ListeManager();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToolsServlet.updateListes(request, listeManager, errorsCodes);

        //Affiche les liste existantes
        if (request.getServletPath().equals("/display")){
            int identifier = ToolsServlet.checkIdentifier("identifier", request, errorsCodes);
            Liste liste = Liste.getListe(identifier);
            if (liste != null){
                ToolsServlet.setAttributes(request, liste);
            }
        }
        //Suppression d'article
        else if(request.getServletPath().equals("/deleteArticle")){
            int identifier = ToolsServlet.checkIdentifier("identifier", request, errorsCodes);
            int identifierArticle = ToolsServlet.checkIdentifier("identifierArticle", request, errorsCodes);
            if (errorsCodes.hasCodes()){
                request.setAttribute("errors", errorsCodes);
            }else{
                try {
                    listeManager.deleteArticle(identifierArticle);
                    listeManager.setListes();
                    Liste liste = Liste.getListe(identifier);
                    ToolsServlet.setAttributes(request, liste);
                }catch (ListeException listeException){
                    ToolsServlet.setErrorsAttribute(listeException, request, errorsCodes);
                }
            }
        }
        request.getRequestDispatcher("/WEB_INF/newList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToolsServlet.updateListes(request, listeManager, errorsCodes);

        // Creation et affichage d'une nouvelle liste ou article
        if (request.getServletPath().equals("/addArticle")) {
            String name = ToolsServlet.checkName("name", request, errorsCodes);
            String nameArticle = ToolsServlet.checkName("nameArticle", request, errorsCodes);
            if (errorsCodes.hasCodes()) { request.setAttribute("errors", errorsCodes.getCodes()); }
            else {
                try {
                    int identifier = ToolsServlet.checkIdentifier("identifier", request, errorsCodes);
                    Liste liste = Liste.getListe(identifier); // null if new list.
                    if (liste == null) { liste = listeManager.addListe(identifier, name); }
                    identifier = liste.getIdentifier();
                    listeManager.addArticle(identifier, nameArticle);
                    ToolsServlet.setAttributes(request, liste);
                } catch (ListeException exception) {
                    ToolsServlet.setErrorsAttribute(exception, request, errorsCodes);
                }
            }
        }
        doGet(request, response);
    }
}
