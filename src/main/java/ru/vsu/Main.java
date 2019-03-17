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
        Solver solver = new Solver();
        solver.solve(points);
        LineCoefficients lineCoefficients = solver.getLineCoefficients();
        this.line = lineCoefficients.getLine(canvasPane.getWidth());

        canvasPane.getChildren().add(line);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    public void initialize(){
        points = new Group();
        canvasPane.getChildren().add(points);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
