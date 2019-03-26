package ru.vsu;

import javafx.scene.shape.Line;

public class LineCoefficients {
    private double A;
    private double B;
    private double C;

    public LineCoefficients(double A, double B, double C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public Line getLine(double screenWidth, double screenHeight) {
        double startX;
        double startY;
        double endX;
        double endY;
        if (A == 0) {
            startX = 0;
            endX = screenWidth;
            startY = endY = -C / B;
        } else if (B == 0) {
            startY = 0;
            endY = screenWidth;
            startX = endX = -C / A;
        } else {
            startX = 0;
            startY = -C / B;
            endX = screenWidth;
            endY = (-C - A * screenWidth) / B;
        }
        return new Line(startX, startY, endX, endY);
    }
}
