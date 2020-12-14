package sample.models;

import javafx.scene.paint.Color;

public class Wall extends GameObject {

    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height, Color.WHITE);
    }

    @Override
    public String toString() {
        return String.format("X: %d, Y: %d, Width: %d, Height: %d", getX(), getY(), getWidth(), getHeight());
    }
}