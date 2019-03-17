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

    public Line getLine(double screenWidth) {
        double startX = 0;
        double startY = B == 0 ? 0 : -C / B;
        double endX = screenWidth;
        double endY = B == 0 ? 0 : (-C - A * screenWidth) / B;

        return new Line(startX, startY, endX, endY);
    }
}
