package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private ContentCreator contentCreator = new ContentCreator(800,800,40, 370);
    private TileManager tileManager = contentCreator.getTileManager();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(contentCreator.createContent());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
