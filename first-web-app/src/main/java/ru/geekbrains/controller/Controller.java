package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


@WebServlet(name = "Controller", urlPatterns = {"", "/main", "/catalog"})
public class Controller extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) {
            throw new ServletException("Product repository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Controller starting");
        resp.setContentType("text/html;charset=UTF-8");
        String url = "/WEB-INF/";
        if (req.getServletPath().equals("/") || req.getServletPath().equals("/main")) {
            url += "main.jsp";
            try {
                req.setAttribute("products", productRepository.findAll());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (req.getServletPath().equals("/catalog")) {
            url += "catalog.jsp";
        } else if (req.getServletPath().equals("/cart")) {
            url += "cart.jsp";
        } else if (req.getServletPath().equals("/about")) {
            url += "about.jsp";
        } else if (req.getServletPath().equals("/new")) {
            req.setAttribute("product", new Product());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        } else if (req.getServletPath().equals("/edit")) {
            String id = req.getParameter("id");
            try {
                Optional<Product> opt = null;
                try {
                    opt = productRepository.findById(Long.parseLong(id));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (opt.isPresent()) {
                    req.setAttribute("product", opt.get());
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher(url).forward(req, resp);
    }
}
