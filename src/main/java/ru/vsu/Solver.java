package ru.vsu;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import org.apache.commons.math3.linear.*;

import java.util.Arrays;

public class Solver {

    private RealMatrix pointsMatrix;    // P
    private ArrayRealVector centerVector;   // C
    private RealMatrix mMatrix; // M
    private RealVector eigenvectorOfM;  // N
    private double D; // D
    private LineCoefficients lineCoefficients;  // (A,B,C) Ax + By + C

    public void solve(Group points) {
        convertPointsToMatrix(points);
        getCenterCoords();
        findM();
        findEigenVectorOfM();
        findD();
        this.lineCoefficients = new LineCoefficients(eigenvectorOfM.getEntry(0), eigenvectorOfM.getEntry(1), D);
    }

    private void convertPointsToMatrix(Group points) {
        double[][] pointsArray = points.getChildren().stream()
                .map(node -> new double[]{((Circle) node).getCenterX(), ((Circle) node).getCenterY()})
                .toArray(double[][]::new);
        pointsMatrix = MatrixUtils.createRealMatrix(pointsArray);
    }

    private void getCenterCoords() {
        double[] xVector = pointsMatrix.getColumn(0);
        double[] yVector = pointsMatrix.getColumn(1);
        double centerX = Arrays.stream(xVector).sum() / pointsMatrix.getRowDimension();
        double centerY = Arrays.stream(yVector).sum() / pointsMatrix.getRowDimension();
        centerVector = new ArrayRealVector(new double[]{centerX, centerY});
    }

    private void findM() {
        RealMatrix leftMatrix = centerVector.outerProduct(centerVector).scalarMultiply(pointsMatrix.getRowDimension());
        RealMatrix rightMatrix = pointsMatrix.transpose().multiply(pointsMatrix);
        this.mMatrix = leftMatrix.subtract(rightMatrix); // M
    }

    private void findEigenVectorOfM() {
        EigenDecomposition eigenDecomposition = new EigenDecomposition(mMatrix);
        double maxLambda = Arrays.stream(eigenDecomposition.getRealEigenvalues()).max().getAsDouble();
        int maxLambdaIndex = 0;
        for (int i = 0; i < eigenDecomposition.getRealEigenvalues().length; i++) {
            if (eigenDecomposition.getRealEigenvalues()[i] == maxLambda) {
                maxLambdaIndex = i;
                break;
            }
        }
        this.eigenvectorOfM = eigenDecomposition.getEigenvector(maxLambdaIndex);
    }

    private void findD() {
        this.D = eigenvectorOfM.mapMultiply(-1).dotProduct(centerVector);
    }

    public LineCoefficients getLineCoefficients() {
        return lineCoefficients;
    }
}
