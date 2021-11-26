package controller;

import model.Point;
import model.Points;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long startTime = System.nanoTime();
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter output = resp.getWriter();
        try {
            double x = Double.parseDouble(req.getParameter("x"));
            double y = Double.parseDouble(req.getParameter("y"));
            double r = Double.parseDouble(req.getParameter("r"));
            ServletContext servletContext = this.getServletContext();
            Points points = (Points) servletContext.getAttribute("points");
            if (points == null) points = new Points();
            if (isValid(x, y, r)) {
                Point point = initPoint(req, startTime);
                if (points.getPointsList() == null) {
                    points = new Points(Stream.of(point).collect(Collectors.toList()));
                }
                points.getPointsList().add(point);
            } else {
                output.println(points.parseJSON());
                return;
            }
            output.println(points.parseJSON());
            servletContext.setAttribute("points", points);
        } catch (NumberFormatException e) {
            output.println("Wrong input values.");
        } finally {
            output.close();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private Point initPoint(HttpServletRequest req, long startTime) {
        double x = Double.parseDouble(req.getParameter("x").replace(",", "."));
        double y = Double.parseDouble(req.getParameter("y").replace(",", "."));
        double r = Double.parseDouble(req.getParameter("r").replace(",", "."));
        BigDecimal computationTime = BigDecimal.valueOf(Double.parseDouble(String.valueOf(BigDecimal.valueOf((System.nanoTime() - startTime) / 1000000000d)).substring(0, 8)));
        return new Point(x, y, r,
                isHit(x, y, r) ? "Да" : "Нет",
                computationTime);
    }

    private boolean isValid(double x, double y, double r) {
        return (x >= -5 && x <= 5) && (y >= -3 && y <= 5) && (r >= 1 && r <= 3);
    }

    private boolean isHit(double x, double y, double r) {
        return checkRectangle(x, y, r) || checkTriangle(x, y, r) || checkCircle(x, y, r);
    }

    private boolean checkRectangle(double x, double y, double r) {
        return x >= 0 && y >= 0 && y <= r && x <= r/2;
    }

    private boolean checkTriangle(double x, double y, double r) {
        return  x <= (r/2 + y/2)  && x >= 0 && y <= 0;
    }

    private boolean checkCircle(double x, double y, double r) {
        return (x * x + y * y) <= r * r && x <= 0 && y >= 0;
    }

}