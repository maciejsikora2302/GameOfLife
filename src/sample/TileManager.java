package sample;

import javafx.animation.AnimationTimer;

public class TileManager {
    private Tile[][] cells;
    private boolean timerRunning = false;
    private int tileCountX;
    private int tileCountY;
    private int[] checkingPositions = new int[]{
            -1, -1,
            -1, 0,
            -1, 1,
            0, 1,
            0, -1,
            1, -1,
            1, 0,
            1, 1
    };

    public TileManager(int tileCountX, int tileCountY) {
        this.tileCountX = tileCountX;
        this.tileCountY = tileCountY;
    }

    AnimationTimer timer = new AnimationTimer() {
        int frameCount = 0;

        @Override
        public void handle(long currentNanoTime) {

            if (frameCount % 2 == 0) {
                nextCycle();
            }
            frameCount++;
        }
    };


    private boolean[][] getCurrentWorldState() {
        boolean[][] state = new boolean[tileCountX][tileCountY];
        for (int y = 0; y < tileCountY; y++) {
            for (int x = 0; x < tileCountX; x++) {
                state[x][y] = cells[x][y].isAlive();
            }
        }
        return state;
    }

    private void nextCycle() {
        boolean[][] state = getCurrentWorldState();
        for (int y = 0; y < tileCountY; y++) {
            for (int x = 0; x < tileCountX; x++) {
                int cellsAliveAround = checkHowManyCellsAreAliveAround(x, y, state);
                Tile cell = cells[x][y];
                if (state[x][y] && !(cellsAliveAround == 2 || cellsAliveAround == 3)) {
                    cell.killCell();
                }
                if (!state[x][y] && cellsAliveAround == 3) {
                    cell.resurrectCell();
                }
            }
        }
    }

    private int checkHowManyCellsAreAliveAround(int x, int y, boolean[][] state) {
        int numberOfAliveCells = 0;
        for (int i = 0; i < checkingPositions.length; i++) {
            if (state[wrapX(x + checkingPositions[i])][wrapY(y + checkingPositions[++i])]) {
                numberOfAliveCells++;
            }
        }
        return numberOfAliveCells;
    }

    private int wrapX(int x) {
        if (x >= tileCountX) x = 0;
        if (x < 0) x = tileCountX - 1;
        return x;
    }

    private int wrapY(int y) {
        if (y >= tileCountY) y = 0;
        if (y < 0) y = tileCountY - 1;
        return y;
    }

    public void setCells(Tile[][] cells) {
        this.cells = cells;
    }

    public void startTimer() {
        timer.start();
        timerRunning = true;
    }

    public void stopTimer() {
        timer.stop();
        timerRunning = false;
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

    public void killAllCells() {
        for (int y = 0; y < tileCountY; y++) {
            for (int x = 0; x < tileCountX; x++) {
                cells[x][y].killCell();
            }
        }
    }

}
