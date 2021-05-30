package com.diagneaxel.javaee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletCouleurUtilisateurPref", value = "/ServletCouleurUtilisateurPref")
public class ServletCouleurUtilisateurPref extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int countVisit = getCookies(request, response);

        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("colors" )== null){
            httpSession.setAttribute("colors", "noir");
        }

        ServletContext servletContext = this.getServletContext();
        List<String> colors = new ArrayList<>();
        colors.add("bleu");
        colors.add("rouge");
        colors.add("green");
        colors.add("noir");
        servletContext.setAttribute("colors", colors);

        request.setAttribute("countVisit", countVisit + "");
        System.out.println(countVisit + "doget");
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/book/accueil.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int countVisit = getCookies(request,response);
        String colors = request.getParameter("colors");

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("colors", colors);

        request.setAttribute("countVisit", countVisit + "");
        System.out.println(countVisit);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/book/accueil.jsp");
        rd.forward(request, response);
    }

    private int getCookies(HttpServletRequest request, HttpServletResponse response){
        int value = 1;
        //Get All cookies et check si le cookies existe
        Cookie[] cookies = request.getCookies();
        Cookie cookieNb = null;


        for (Cookie cookie : cookies){
            if(cookie.getName().equals("cookieNb")){
                value = Integer.parseInt(cookie.getValue())+1;
                cookie.setValue(String.valueOf(value));
                cookieNb = cookie;
                response.addCookie(cookieNb);
            }
        }


        //Si on a pas de cookies
        if (cookieNb == null){
            cookieNb = new Cookie("cookieNb", 1 + "");
            cookieNb.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookieNb);
        }
        //Age du cookies


        return value;

    }

}
