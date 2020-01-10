package sample;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

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
        Scene root = new Scene(createGamePane());

        root.setOnKeyPressed(key ->{
            if(key.getCode() == KeyCode.SPACE){
                if(tileManager.isTimerRunning()){
                    tileManager.stopTimer();
                }else{
                    tileManager.startTimer();
                }
            }
            if(key.getCode() == KeyCode.R){
                tileManager.killAllCells();
            }

        });

        return root;
    }

    public Pane createGamePane() {
        Pane gamePane = new Pane();
        gamePane.setPrefSize(gameWidth, gameHeight);
        Tile[][] cells = new Tile[tileCountX][tileCountY];
        for(int y=0; y<tileCountY;y++){
            for(int x=0;x<tileCountX;x++){
                Tile tile = new Tile(x,y);
                cells[x][y] = tile;
                gamePane.getChildren().add(tile);
            }
        }
        tileManager.setCells(cells);

        return gamePane;
    }
}
