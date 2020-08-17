package ru.gekkbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet (name = "FirstHttpServlet", urlPatterns = "/first-http-servlet/*")
public class FirstHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.getWriter().println("<h1> привет от HTTP сервлета </h1>");
        resp.getWriter().println("<p>contextPath = " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p>servletPath = " + req.getServletPath() + "</p>");
        resp.getWriter().println("<p>PathInfo = " + req.getPathInfo() + "</p>");
        resp.getWriter().println("<p>queryString = " + req.getQueryString() + "</p>");

//        localhost:8080/first-web-app/first-http-servlet/aaa/bbb/ddd/sss?param1=val1&param2=val2&param2=value3
        resp.getWriter().println("<h2> Paramters </h2>");
        req.getParameterMap().forEach(
                (param, value) -> {
                    try{
                        resp.getWriter().println("<p>" + param + " = " + Arrays.toString(value) + "</p>");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        req.setAttribute("attr", "value");
        getServletContext().getRequestDispatcher("/first-servlet").include(req,resp);


    }
}
