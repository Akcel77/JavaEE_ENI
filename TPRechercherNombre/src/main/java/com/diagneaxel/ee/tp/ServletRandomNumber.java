package com.diagneaxel.ee.tp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ServletRandomNumber")
public class ServletRandomNumber extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int randNumber = (int) (Math.random() * 10);
        System.out.println(randNumber);
        String userEntry = request.getParameter("userEntry");
        System.out.println(userEntry);
        if (randNumber == Integer.parseInt(userEntry)){
            response.sendRedirect("/TPRechercherNombre_war_exploded/winner.html");
        }else{
            response.sendRedirect("/TPRechercherNombre_war_exploded/looser.html");
        }
        out.close();
    }
}
