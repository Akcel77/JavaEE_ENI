package com.diagneaxel.javaee.servlets;

import com.diagneaxel.javaee.ListeException;
import com.diagneaxel.javaee.bll.ListeManager;
import com.diagneaxel.javaee.bo.Article;
import com.diagneaxel.javaee.bo.Liste;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/Basket")
public class ServletBasket extends HttpServlet {
    protected ListeException errorsCodes = new ListeException();
    protected ListeManager listeManager = new ListeManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToolsServlet.updateListes(request, listeManager, errorsCodes);

        int identifier = ToolsServlet.checkIdentifier("identifier", request, errorsCodes);
        Liste liste = Liste.getListe(identifier);

        //Decoche les checkBOX
        if (request.getParameter("uncheckAll") != null || request.getParameterValues("checkboxes") != null) {
            try {
                listeManager.uncheckAllArticles(identifier);
            } catch (ListeException exception) {
                ToolsServlet.setErrorsAttribute(exception, request, errorsCodes);
            }
        }

        if (!errorsCodes.hasCodes()) {
            request.setAttribute("errors", errorsCodes.getCodes());
        } else {
            try {
                listeManager.setListes();
                liste = Liste.getListe(identifier);
                request.setAttribute("liste", liste);
            } catch (ListeException exception) {
                ToolsServlet.setErrorsAttribute(exception, request, errorsCodes);
            }
        }

        if (liste != null) {
            ToolsServlet.setAttributes(request, liste);
        }
        request.getRequestDispatcher("/WEB-INF/basket.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToolsServlet.updateListes(request, listeManager, errorsCodes);

        int identifier = ToolsServlet.checkIdentifier("identifier", request, errorsCodes);
        Liste liste = Liste.getListe(identifier);
        if (request.getParameterValues("checkboxes") != null) {
            ArrayList<String> checkboxes = new ArrayList<>(Arrays.asList(request.getParameterValues("checkboxes")));
            for (Article article : liste.getArticles()) {
                int identifierArticle = article.getIdentifier();
                try {
                    if (checkboxes.contains(String.valueOf(identifierArticle))) {
                        listeManager.checkArticle(identifierArticle);
                    } else {
                        listeManager.uncheckArticle(identifierArticle);
                    }
                } catch (ListeException exception) {
                    ToolsServlet.setErrorsAttribute(exception, request, errorsCodes);
                }
            }
        }
        doGet(request, response);
    }
}
