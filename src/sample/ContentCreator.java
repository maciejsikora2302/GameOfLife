package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ContentCreator {
    private int gameWidth;
    private int gameHeight;

    private int menuWidth;
    private int menuHeight = gameHeight;

    private int tileSize = 1;
    private int tileCountX;
    private int tileCountY;

    private TileManager tileManager;

    public ContentCreator(int gameWidth, int gameHeight, int tileSize, int menuWidth) {
        Tile.tileSize = tileSize;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.tileSize = tileSize;
        this.menuWidth = menuWidth;
        this.tileCountX = gameWidth / tileSize;
        this.tileCountY = gameHeight / tileSize;
        tileManager = new TileManager(tileCountX, tileCountY);
    }

    //stage <- scene <- rootPane <- gamePane + menuPane


    public TileManager getTileManager() {
        return tileManager;
    }

    public Scene createContent() {
        HBox panesBox = new HBox(createGamePane(), createMenuPane());
        Scene root = new Scene(panesBox);

        root.setOnKeyPressed(key -> {
            if (key.getCode() == KeyCode.SPACE) {
                if (tileManager.isTimerRunning()) {
                    tileManager.stopTimer();
                } else {
                    tileManager.startTimer();
                }
            }
            if (key.getCode() == KeyCode.R) {
                tileManager.killAllCells();
            }

        });

        return root;
    }

    public Pane createGamePane() {
        Pane gamePane = new Pane();
        gamePane.setPrefSize(gameWidth, gameHeight);
        Tile[][] cells = new Tile[tileCountX][tileCountY];
        for (int y = 0; y < tileCountY; y++) {
            for (int x = 0; x < tileCountX; x++) {
                Tile tile = new Tile(x, y);
                cells[x][y] = tile;
                gamePane.getChildren().add(tile);
            }
        }
        tileManager.setCells(cells);

        return gamePane;
    }

    private Pane createMenuPane() {
        Pane menuPane = new Pane();
        menuPane.setPrefSize(menuWidth, menuHeight);

        VBox menuBox = new VBox(20);

        Text menuTitle = new Text("Menu");

        Text aliveOnMapText = new Text("Alive on map: " + tileManager.getNumberOfCellsAlive());
        tileManager.statisticsManager.setAliveOnMapToUpdate(aliveOnMapText);
        Text animationRunning = new Text("Animation running: " + tileManager.isTimerRunning());
        tileManager.statisticsManager.setRunningToUpdate(animationRunning);

        TextField speedTF = new TextField("Set initial speed");
        Button modifySpeed = new Button("Modify speed");
        modifySpeed.setOnAction(pressed -> {
            int speed = 1;
            try {
                speed = Integer.parseInt(speedTF.getText());
                if (speed < 0) throw new NumberFormatException("Negative number");
            } catch (NumberFormatException ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Input not valid");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("The number must be a positive integer greater that zero.");
                errorAlert.showAndWait();
            }
            menuPane.requestFocus();
            tileManager.setSpeed(speed);
        });


        HBox speedBox = new HBox(10);
        speedBox.getChildren().addAll(speedTF, modifySpeed);

        Text speedDescription = new Text("1 is the fastes, around 50/60 is pretty slow");
        Text usageDescription = new Text("After input of initial speed press space to start and stop simulation");


        menuBox.getChildren().addAll(menuTitle, aliveOnMapText, animationRunning, speedBox, speedDescription, usageDescription);

        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateY(menuHeight / 2.0);
        menuPane.getChildren().addAll(menuBox);

        return menuPane;
    }
}
