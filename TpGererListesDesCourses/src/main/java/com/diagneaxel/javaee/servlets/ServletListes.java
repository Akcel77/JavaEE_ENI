package com.diagneaxel.javaee.servlets;

import com.diagneaxel.javaee.ListeException;
import com.diagneaxel.javaee.bll.ListeManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(
        urlPatterns=  {
                "/listes",
                "/deleteListe"
        })
public class ServletListes extends HttpServlet {
    protected ListeException errorsCodes = new ListeException();
    protected ListeManager listeManager = new ListeManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToolsServlet.updateListes(request, listeManager, errorsCodes);

        int identifier = 0;

        // Suppression des listes
        if (request.getServletPath().equals("/deleteListe")) {
            identifier = ToolsServlet.checkIdentifier("identifier", request, errorsCodes);
            if (errorsCodes.hasCodes()) { request.setAttribute("errors", errorsCodes.getCodes()); }
            else {
                try {
                    listeManager.setListes();
                    listeManager.deleteListe(identifier);
                } catch (ListeException exception) {
                    ToolsServlet.setErrorsAttribute(exception, request, errorsCodes);
                }
            }
        }

        //Affichage des listes
        try { request.setAttribute("listes", listeManager.selectAllListes()); }
        catch (ListeException exception) { ToolsServlet.setErrorsAttribute(exception, request, errorsCodes); }
        request.getRequestDispatcher("/WEB-INF/listes.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
