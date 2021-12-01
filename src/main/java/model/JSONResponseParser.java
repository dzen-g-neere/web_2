package model;

import java.math.BigDecimal;
import java.util.List;

public class JSONResponseParser {
    public String parseJSON(List<Point> hits) {
        String res = "{" + "\"response\":[";
        for (Point h : hits) {
            res = res.concat(parseJSON(h) + ",");
        }
        res = res.substring(0, res.length() - 1);
        return res + "]}";
    }
    public String parseJSON(Point point) {
        return '{' +
                "\"xValue\":" + "\"" + point.getX() + "\"" + "," +
                "\"yValue\":" + "\"" + point.getY() + "\"" + "," +
                "\"rValue\":" + "\"" + point.getR() + "\"" + "," +
                "\"result\":" + "\"" + point.getResult() + "\"" + "," +
                "\"totalProcessingTime\":" + "\"" + String.valueOf(point.getProcessingTime().add(new BigDecimal("0.0000000001"))).substring(0, 8) + " sec" + "\"" +
                "}";
    }
}
