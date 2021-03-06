package model;

import model.Point;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ClearServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getAttribute("clear") != null &&
                    Boolean.parseBoolean("" + req.getAttribute("clear"))) {
                ServletContext servletContext = req.getServletContext();
                ArrayList<Point> points = new ArrayList<>();
                servletContext.setAttribute("points", points);
            } else getServletContext().getRequestDispatcher("/resultPage.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getClass());
            getServletContext().getRequestDispatcher("/resultPage.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/resultPage.jsp").forward(req, resp);
    }
}
