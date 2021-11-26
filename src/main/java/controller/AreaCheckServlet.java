package controller;

import model.JSONResponseParser;
import model.Point;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long startTime = System.nanoTime();
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter output = resp.getWriter()) {
            JSONResponseParser jsonResponseParser = new JSONResponseParser();
            double x = Double.parseDouble("" + req.getAttribute("par_x"));
            double y = Double.parseDouble("" +  req.getAttribute("par_y"));
            double r = Double.parseDouble("" +  req.getAttribute("par_r"));
            ServletContext servletContext = this.getServletContext();
            ArrayList<Point> points = null;
            if (servletContext.getAttribute("points") != null) {
                points = (ArrayList<Point>) servletContext.getAttribute("points");
            }
            if (points == null) points = new ArrayList<>();
            if (isValid(x, y, r)) {
                Point point = initPoint(x, y, r, startTime);
                points.add(point);
            }
            output.println(jsonResponseParser.parseJSON(points));
            servletContext.setAttribute("points", points);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //getServletContext().getRequestDispatcher("/controllerServlet").forward(req, resp);
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/controllerServlet").forward(req, resp);
    }

    private Point initPoint(double x, double y, double r, long startTime) {
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
        return x >= 0 && y >= 0 && y <= r && x <= r / 2;
    }

    private boolean checkTriangle(double x, double y, double r) {
        return x <= (r / 2 + y / 2) && x >= 0 && y <= 0;
    }

    private boolean checkCircle(double x, double y, double r) {
        return (x * x + y * y) <= r * r && x <= 0 && y >= 0;
    }

}