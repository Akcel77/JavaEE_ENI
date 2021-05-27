package com.diagneaxel.TP5suiviDesRepas.servlet;

import com.diagneaxel.TP5suiviDesRepas.bll.RepasMng;
import com.diagneaxel.TP5suiviDesRepas.bo.Repas;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletVueRepas", value = "/ServletVueRepas")
public class ServletVueRepas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepasMng repasMng = new RepasMng();
        List<Repas> repasList = null;
        try {
            repasList = repasMng.getRepas();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher rd = null;
        request.setAttribute("repas", repasList);
        rd = request.getRequestDispatcher("/WEB-INF/checkRepas.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
