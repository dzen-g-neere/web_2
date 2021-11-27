package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("x") != null &&
                    req.getParameter("y") != null &&
                    req.getParameter("r") != null) {
                String xString, yString, rString;
                xString = req.getParameter("x");
                yString = req.getParameter("y");
                rString = req.getParameter("r");
                double x, y, r;
                if (xString.length() > 6) {
                    x = Double.parseDouble(xString.substring(0, 5));
                } else {
                    x = Double.parseDouble(xString);
                }
                if (yString.length() > 6) {
                    y = Double.parseDouble(yString.substring(0, 5));
                } else {
                    y = Double.parseDouble(yString);
                }
                if (rString.length() > 6) {
                    r = Double.parseDouble(rString.substring(0, 5));
                } else {
                    r = Double.parseDouble(rString);
                }
                req.setAttribute("par_x", x);
                req.setAttribute("par_y", y);
                req.setAttribute("par_r", r);
                getServletContext().getRequestDispatcher("/areaCheckServlet").forward(req, resp);
            } else if (req.getParameter("clear") != null && req.getParameter("clear").equals("true")) {
                req.setAttribute("clear", true);
                getServletContext().getRequestDispatcher("/clearServlet").forward(req, resp);
            } else getServletContext().getRequestDispatcher("/resultPage.jsp").forward(req, resp);
        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/resultPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}