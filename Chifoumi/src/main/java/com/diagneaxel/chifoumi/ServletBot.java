package com.diagneaxel.chifoumi;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletBot", value = "/ServletBot")
public class ServletBot extends HttpServlet {

    /**
     * Get User Entry
     * Get Bot choice
     * Fight And find the winner
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEntry = request.getParameter("userEntry");
        System.out.println(userEntry);

        int random = (int)(Math.random()*3)+1;
        String botChoice = "";
        if(random == 1){
            botChoice = "chi";
        }else if (random == 2){
            botChoice = "fou";
        }else{
            botChoice = "mi";
        }
        System.out.println(botChoice);

        String resultGame = "";
        if      ((userEntry.equals("chi") && botChoice.equals("fou")) ||
                (userEntry.equals("fou") && botChoice.equals("mi")) ||
                (userEntry.equals("mi") && botChoice.equals(("chi")) ))
        {
            resultGame = "Bot";
        }
        else if ((botChoice.equals("chi") && userEntry.equals("fou")) ||
                (botChoice.equals("fou") && userEntry.equals("mi")) ||
                (botChoice.equals("mi") && userEntry.equals(("chi")) ))
        {
            resultGame = "Player";
        }else if ((botChoice.equals("chi") && userEntry.equals("chi")) ||
                (botChoice.equals("fou") && userEntry.equals("fou")) ||
                (botChoice.equals("mi") && userEntry.equals(("mi")) ) )
        {
            resultGame = "Draw";
        }else{
            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
            rd.forward(request, response);
        }

        System.out.println("The winner is" + resultGame);

        request.setAttribute("resultGame", resultGame);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/result.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
