package sample;

import javafx.animation.AnimationTimer;

public class TileManager {
    private Tile[][] tiles;

    private boolean timerRunning = false;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            
        }
    };
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public void startTimer(){
        timer.start();
        timerRunning = true;
    }

    public void stopTimer(){
        timer.stop();
        timerRunning = false;
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

}
