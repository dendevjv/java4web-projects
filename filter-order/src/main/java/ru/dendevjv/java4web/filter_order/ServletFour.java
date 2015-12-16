package ru.dendevjv.java4web.filter_order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "servletFour",
        urlPatterns = "/servletFour"
)
public class ServletFour extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("Entering ServletFour.doGet()");
        req.getRequestDispatcher("/foo/servletThree").forward(req, resp);
        System.out.println("Leaving ServletFour.doGet()");
    }
}
