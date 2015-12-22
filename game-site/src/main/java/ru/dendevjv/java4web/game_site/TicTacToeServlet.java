package ru.dendevjv.java4web.game_site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "ticTacToeServlet",
        urlPatterns = "/ticTacToe"
)
public class TicTacToeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("pendingGames", TicTacToeGame.getPendingGames());
        this.view("list", req, resp);
    }
    
    private void view(String view, HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/view/ticTacToe/" + view + ".jsp")
                .forward(req, resp);
        ;
    }
}
