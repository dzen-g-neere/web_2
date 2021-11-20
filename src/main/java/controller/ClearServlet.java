package controller;

import model.Points;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletContext servletContext = req.getServletContext();
            Points points = new Points();
            servletContext.setAttribute("points", points);
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println(e.getClass());

        }
    }
}
