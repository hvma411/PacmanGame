package sample;

import sample.models.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Graphics {

    private Model model;
    private GraphicsContext gc;

    public Graphics(Model model, GraphicsContext gc) {
        this.model = model;
        this.gc = gc;
    }

    public void draw() {

        // Draw Background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 600, 600);

        // Draw the Square

        Player player = model.getPlayer();
        gc.setFill(player.getColor());
        gc.fillOval(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        Monster monster = model.getMonster();
        gc.setFill(monster.getColor());
        gc.fillOval(monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());

        for (GameObject wall : model.getWall()) {
            gc.setFill(wall.getColor());
            gc.fillRect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        }

        for (GameObject coin : model.getCoins()) {
            gc.setFill(coin.getColor());
            gc.fillOval(coin.getX(), coin.getY(), coin.getWidth() / 2, coin.getHeight() / 2);
        }
    }

}