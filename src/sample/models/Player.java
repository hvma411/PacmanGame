package sample.models;

import javafx.scene.paint.Color;

public class Player extends GameObject {

    public Player(int x, int y) {
        super(x, y, 30, 30, Color.YELLOW);
    }

    @Override
    public String toString() {
        return String.format("X: %d, Y: %d, Width: %d, Height: %d", getX(), getY(), getWidth(), getHeight());
    }
}