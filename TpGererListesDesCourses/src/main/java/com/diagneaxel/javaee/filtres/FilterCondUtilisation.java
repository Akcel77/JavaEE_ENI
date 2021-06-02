package com.diagneaxel.javaee.filtres;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST}, urlPatterns = {"/*"})
public class FilterCondUtilisation implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if( httpServletRequest.getParameter("accept") != null ||
            httpServletRequest.getServletPath().startsWith("/css") ||
            httpServletRequest.getSession().getAttribute("true" ) != null ) {
                httpServletRequest.getSession().setAttribute("true", true);
                chain.doFilter(request, response);
        }else{
            httpServletRequest.setAttribute("urlTarget", httpServletRequest.getContextPath()+httpServletRequest.getServletPath());
            RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/WEB-INF/condUtilisation.jsp");
//           TODO
//            rd.forward(httpServletRequest, httpServletResponse); A FIXER
        }
        chain.doFilter(request, response);
    }
}
