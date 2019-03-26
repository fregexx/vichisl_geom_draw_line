package ru.vsu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {

    private Group points;
    private Line line;

    @FXML
    private Pane canvasPane;

    @FXML
    void handleMouseClicked(MouseEvent e) {
        Circle point = new Circle(e.getX(), e.getY(), 3, Color.CORAL);
        points.getChildren().add(point);
    }

    @FXML
    void handleClearButton(ActionEvent e) {
        points.getChildren().clear();
        canvasPane.getChildren().remove(line);
    }

    @FXML
    void handleDrawLineButton(ActionEvent e) {
//        customInput();
        Solver solver = new Solver();
        solver.solve(points);
        LineCoefficients lineCoefficients = solver.getLineCoefficients();
        this.line = lineCoefficients.getLine(canvasPane.getWidth(), canvasPane.getHeight());

        canvasPane.getChildren().add(line);
    }

    @FXML
    private void handleLoadSquarePresetButton() {
        Circle point = new Circle(100, 100, 3, Color.CORAL);
        Circle point2 = new Circle(100, 200, 3, Color.CORAL);
        Circle point3 = new Circle(200, 200, 3, Color.CORAL);
        Circle point4 = new Circle(200, 100, 3, Color.CORAL);
        points.getChildren().add(point);
        points.getChildren().add(point2);
        points.getChildren().add(point3);
        points.getChildren().add(point4);
    }

    @FXML
    private void handleLoadVerticalPresetButton() {
        Circle point = new Circle(200, 100, 3, Color.CORAL);
        Circle point2 = new Circle(200, 300, 3, Color.CORAL);
        points.getChildren().add(point);
        points.getChildren().add(point2);
    }

    @FXML
    private void handleLoadHorizontalPresetButton() {
        Circle point = new Circle(100, 200, 3, Color.CORAL);
        Circle point2 = new Circle(300, 200, 3, Color.CORAL);
        points.getChildren().add(point);
        points.getChildren().add(point2);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        points = new Group();
        canvasPane.getChildren().add(points);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
