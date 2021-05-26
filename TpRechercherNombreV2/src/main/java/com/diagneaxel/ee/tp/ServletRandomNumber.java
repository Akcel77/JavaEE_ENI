package com.diagneaxel.ee.tp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
       name = "ServletRandomNumber",
        urlPatterns = "/ServletRandomNumber",
        initParams = {
               @WebInitParam(description = "Get Param MAX", name = "MAX", value = "10")
        }
)
public class ServletRandomNumber extends HttpServlet {
    private int MAX;


    @Override
    public void init() throws ServletException{
        this.MAX = Integer.parseInt(this.getInitParameter("MAX"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int randNumber = (int) (Math.random() * MAX);
        System.out.println(randNumber);
        String userEntry = request.getParameter("userEntry");
        System.out.println(userEntry);
        if (randNumber == Integer.parseInt(userEntry)){
            response.sendRedirect("/TpRechercherNombreV2_war_exploded/winner.html");
        }else{
            response.sendRedirect("/TpRechercherNombreV2_war_exploded/looser.html");
        }
        out.close();
    }
}
