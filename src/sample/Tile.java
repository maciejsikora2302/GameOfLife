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
        if(isAlive()){
            killCell();
        }else{
            resurrectCell();
        }
    }

    public void killCell(){
        border.setFill(Color.BLACK);
        alive = false;
    }

    public void resurrectCell(){
        border.setFill(Color.GREEN);
        alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "alive=" + alive +
                '}';
    }
}
