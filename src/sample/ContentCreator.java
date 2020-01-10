package sample;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class ContentCreator {
    private int gameWidth;
    private int gameHeight;

    private int menuWidth;
    private int menuHeight = gameHeight;

    private int tileSize = 1;
    private int tileCountX;
    private int tileCountY;

    private TileManager tileManager = new TileManager();

    public ContentCreator(int gameWidth, int gameHeight, int tileSize, int menuWidth) {
        Tile.tileSize = tileSize;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.tileSize = tileSize;
        this.menuWidth = menuWidth;
        this.tileCountX = gameWidth / tileSize;
        this.tileCountY = gameHeight / tileSize;
    }

    //stage <- scene <- rootPane <- gamePane + menuPane


    public TileManager getTileManager() {
        return tileManager;
    }

    public Scene createContent() {
        Scene root = new Scene(createGamePane());

        root.setOnKeyPressed(key ->{
            if(tileManager.isTimerRunning()){
                tileManager.stopTimer();
            }else{
                tileManager.startTimer();
            }
        });

        return root;
    }

    public Pane createGamePane() {
        Pane gamePane = new Pane();
        System.out.println(gameWidth + " " +  gameHeight);
        gamePane.setPrefSize(gameWidth, gameHeight);
        Tile[][] tiles = new Tile[tileCountX][tileCountY];
        System.out.println(tileCountX + " " +  tileCountY);
        System.out.println(tileSize);
        for(int y=0; y<tileCountY;y++){
            for(int x=0;x<tileCountX;x++){
                Tile tile = new Tile(x,y);
                tiles[x][y] = tile;
                gamePane.getChildren().add(tile);
            }
        }
        tileManager.setTiles(tiles);

        return gamePane;
    }
}
