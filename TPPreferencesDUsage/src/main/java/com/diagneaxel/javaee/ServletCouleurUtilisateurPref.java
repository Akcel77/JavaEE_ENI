package com.diagneaxel.javaee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletCouleurUtilisateurPref", value = "/ServletCouleurUtilisateurPref")
public class ServletCouleurUtilisateurPref extends HttpServlet {

    public void init() throws ServletException{
        List<String> couleurs = new ArrayList<>();
        couleurs.add("bleu");
        couleurs.add("rouge");
        couleurs.add("green");
        couleurs.add("noir");
        this.getServletContext().setAttribute("couleurs", couleurs);
        super.init();
    }

    public ServletCouleurUtilisateurPref(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie cookieNbAcces = null;

        if (cookies != null){
            for (Cookie c : cookies) {
                if (c.getName().equals("NbAccess")) {
                    cookieNbAcces = c;
                    int value = Integer.parseInt(cookieNbAcces.getValue()) + 1;
                    cookieNbAcces.setValue(String.valueOf(value));
                    break;
                }
            }
        }

        //Si on a pas de cookies
        if (cookieNbAcces == null){
            cookieNbAcces = new Cookie("NbAccess", 1 + "");
        }
        cookieNbAcces.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookieNbAcces);
        request.setAttribute("cookieNbAcces", cookieNbAcces);


        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("couleurs", request.getParameter("getColor"));
        doGet(request, response);
    }


}
