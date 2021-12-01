package model;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class AreaCheckServlet extends HttpServlet {
    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long startTime = System.nanoTime();
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter output = resp.getWriter()) {

            double x = (double) req.getAttribute("par_x");
            double y = (double) req.getAttribute("par_y");
            double r = (double) req.getAttribute("par_r");
            reentrantLock.lock();
            ServletContext servletContext = this.getServletContext();
            List<Point> points;
            if (servletContext.getAttribute("points") == null) {
                servletContext.setAttribute("points", Collections.synchronizedList(new ArrayList<>()));
            }
            points = (List<Point>) servletContext.getAttribute("points");
            JSONResponseParser jsonResponseParser = new JSONResponseParser();
            Point point = initPoint(x, y, r, startTime);
            points.add(point);
            output.println(jsonResponseParser.parseJSON(points));
            reentrantLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                getServletContext().getRequestDispatcher("/controllerServlet").forward(req, resp);
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/controllerServlet").forward(req, resp);
    }

    private Point initPoint(double x, double y, double r, long startTime) {
        BigDecimal computationTime = BigDecimal.valueOf((System.nanoTime() - startTime) / 1000000000d);
        String temp = String.valueOf(computationTime);
        if (temp.length() > 8)
            computationTime = BigDecimal.valueOf(Double.parseDouble(String.valueOf(computationTime).substring(0, 8)));
        return new Point(x, y, r,
                isHit(x, y, r) ? "Да" : "Нет",
                computationTime);
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