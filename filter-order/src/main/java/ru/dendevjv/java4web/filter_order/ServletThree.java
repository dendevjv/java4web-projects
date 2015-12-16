package ru.dendevjv.java4web.filter_order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "servletThree",
        urlPatterns = "/foo/servletThree"
)
public class ServletThree extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("Entering ServletThree.doGet()");
        resp.getWriter().write("Servlet Three");
        System.out.println("Leaving ServletThree.doGet()");
    }
}
