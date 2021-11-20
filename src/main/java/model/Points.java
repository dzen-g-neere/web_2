package model;

import java.util.ArrayList;
import java.util.List;

public class Points {
    private List<Point> pointList;

    public Points() {
        this.pointList = new ArrayList<>();
    }

    public List<Point> getPointsList() {
        return pointList;
    }

    public Points(List<Point> pointList) {
        this.pointList = pointList;
    }

    public void setPointsList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public String parseJSON() {
        List<Point> hits = pointList;
        String res = "{" + "\"response\":[";
        for (Point h : hits) {
            res = res.concat(h.toJson() + ",");
        }
        res = res.substring(0, res.length() - 1);
        return res + "]}";
    }
}
