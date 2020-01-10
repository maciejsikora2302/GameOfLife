package sample;

import javafx.scene.text.Text;

public class StatisticsManager {
    Text aliveOnMap;
    Text running;
    TileManager tileManager;

    public StatisticsManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public void updateStatistics(){
        aliveOnMap.setText("Alive on map: " + tileManager.getNumberOfCellsAlive());
        running.setText("Animation running: " + tileManager.isTimerRunning());
    }

    public void setAliveOnMapToUpdate(Text aliveOnMap){
        this.aliveOnMap = aliveOnMap;
    }

    public void setRunningToUpdate(Text running){
        this.running = running;
    }

}
