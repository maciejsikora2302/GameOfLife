package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile extends StackPane {
    private boolean alive;
    private final int x;
    private final int y;
    static int tileSize;

    private Rectangle border = new Rectangle(tileSize-2,tileSize-2);

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.alive = false;
        border.setStroke(Color.WHITE);
        border.setFill(Color.BLACK);

        getChildren().addAll(border);
        setTranslateX(x*tileSize);
        setTranslateY(y*tileSize);

        setOnMouseClicked(event ->{
            this.manageLifeStateOfCell();
        });
    }

    public void manageLifeStateOfCell(){
        if(!alive){
            border.setFill(Color.BLUE);
            alive = true;
        }else{
            border.setFill(Color.BLACK);
            alive = false;
        }
    }
}
