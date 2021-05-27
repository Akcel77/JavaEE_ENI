package com.diagneaxel.TP5suiviDesRepas.servlet;

import com.diagneaxel.TP5suiviDesRepas.bll.AlimentsMng;
import com.diagneaxel.TP5suiviDesRepas.bll.BLLException;
import com.diagneaxel.TP5suiviDesRepas.bll.RepasMng;
import com.diagneaxel.TP5suiviDesRepas.bo.Repas;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletAjouterRepas", value = "/ServletAjouterRepas")
public class ServletAjouterRepas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/addRepas.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userInputDate = request.getParameter("date");
        String userInputTime = request.getParameter("heure");

        String[] aliments = request.getParameter("repas").split(",");

        RepasMng repasMng = new RepasMng();
        AlimentsMng alimentsMng = new AlimentsMng();

        try {
            int idRepas = repasMng.insert(new Repas(userInputDate, userInputTime));
            alimentsMng.setAliments(idRepas, aliments);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = null;
        System.out.println("test");
        rd = request.getRequestDispatcher("/ServletVueRepas");
        rd.forward(request, response);
        System.out.println("test2");
    }
}
