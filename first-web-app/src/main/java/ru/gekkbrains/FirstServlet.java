package ru.gekkbrains;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<h1> Hello from servlet</h1>");
        res.getWriter().println("<p> attr = " + req.getAttribute("attr") + "</p>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    private transient ServletConfig config;


}
