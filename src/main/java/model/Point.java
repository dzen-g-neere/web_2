package model;

import java.math.BigDecimal;

public class Point {
    private double x;
    private double y;
    private double r;
    private String result;
    private BigDecimal processingTime;

    public Point(double x, double y, double r, String result, BigDecimal processingTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.processingTime = processingTime;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BigDecimal getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(BigDecimal processingTime) {
        this.processingTime = processingTime;
    }
}
